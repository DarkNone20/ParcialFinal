

package com.example.parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialfinal.Interface.CrudUsuariosInterface;
import com.example.parcialfinal.Model.Usuarios;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button boton_Login;
    private Button boton_Registro;
    private EditText editTextUsuario;
    private EditText editTextContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = findViewById(R.id.txtUsuario);
        editTextContrasena = findViewById(R.id.txtContrasena);

        boton_Registro = findViewById(R.id.txtbotonR);
        boton_Login = findViewById(R.id.txtbotonL);

        boton_Registro.setOnClickListener(this);
        boton_Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(boton_Registro)) {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            startActivity(intent);
        } else if (v.equals(boton_Login)) {
            String Usuario = editTextUsuario.getText().toString().trim();
            String Contrasena = editTextContrasena.getText().toString().trim();

            if (Usuario.isEmpty() || Contrasena.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, ingresa el usuario y la contraseña", Toast.LENGTH_SHORT).show();
            } else {
                login(Usuario, Contrasena);
            }
        }
    }

    public void login(String Usuario, String Contrasena) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.6.81:8801/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CrudUsuariosInterface crudUsuariosInterface = retrofit.create(CrudUsuariosInterface.class);

        Usuarios loginData = new Usuarios(null, Usuario, Contrasena, null);
        RequestBody user = RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(loginData));

        Call<Usuarios> call = crudUsuariosInterface.loginUsuario(user);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) {
                    Usuarios loggedUser = response.body();
                    if (loggedUser != null) {
                        // Inicio de sesión exitoso
                        Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        // Realizar las acciones necesarias después del inicio de sesión exitoso
                    }
                } else {
                    // Inicio de sesión fallido
                    Toast.makeText(MainActivity.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                // Error en la llamada al API
                Toast.makeText(MainActivity.this, "Error en la llamada al API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
