package com.example.parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

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

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    private Button boton_Registro;
    private EditText editTextNombre, editTextPassword, editTextPassword2, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Initialize views
        editTextNombre = findViewById(R.id.txtNombre2);
        editTextPassword = findViewById(R.id.txtId2);
        editTextPassword2 = findViewById(R.id.txtPassword3);
        editTextEmail = findViewById(R.id.txtCorreo2);
        boton_Registro = findViewById(R.id.txtboton2);

        // Set click listener for the button
        boton_Registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == boton_Registro) {
            String password1 = editTextPassword.getText().toString();
            String password2 = editTextPassword2.getText().toString();

            if (password1.equals(password2)) {
                // Las contraseñas son iguales, procede a crear el usuario
                createUsuarios();
            } else {
                // Las contraseñas no son iguales, muestra un mensaje de error al usuario
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createUsuarios() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.230.76:8801/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudUsuariosInterface crudUsuariosInterface = retrofit.create(CrudUsuariosInterface.class);

        String Usuario = editTextNombre.getText().toString();
        String Contrasena = editTextPassword.getText().toString();
        String Correo = editTextEmail.getText().toString();

        Usuarios usuarios = new Usuarios(null, Usuario, Contrasena, Correo);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(usuarios));

        Call<Usuarios> call = crudUsuariosInterface.createUsuario(requestBody);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) {
                    Usuarios createdUsuarios = response.body();
                    if (createdUsuarios != null) {
                        // Handle successful response
                        System.out.println("Usuarios created: " + createdUsuarios.toString());
                    }
                } else {
                    // Handle unsuccessful response
                    System.out.println("Failed to create Usuarios. Error: " + response.message());
                }

                // Clear input fields after creating Usuarios
                editTextNombre.setText("");
                editTextPassword.setText("");
                editTextPassword2.setText("");
                editTextEmail.setText("");
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                // Handle failure
                System.out.println("Failed to create Usuarios. Error: " + t.getMessage());
            }
        });
    }
}
