package com.example.parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class ActualizarDatos extends AppCompatActivity implements View.OnClickListener {

    private Button boton_Actualizar;
    private EditText  editTextID2,editTextNombre2, editTextPassword3, editTextEmail3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);

        // Initialize views
        editTextID2 = findViewById(R.id.txtId2);
        editTextNombre2 = findViewById(R.id.txtNombre2);
        editTextPassword3 = findViewById(R.id.txtPassword3);
        editTextEmail3 = findViewById(R.id.txtCorreo2);
        boton_Actualizar = findViewById(R.id.txtboton2);

        // Set click listener for the button
        boton_Actualizar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        createUsuarios();

    }

    public void createUsuarios() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.230.76:8801/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrudUsuariosInterface crudUsuariosInterface = retrofit.create(CrudUsuariosInterface.class);

        // Obtén el ID del usuario a actualizar
        Long id = Long.parseLong(editTextID2.getText().toString());

        String Usuario = editTextNombre2.getText().toString();
        String Contrasena = editTextPassword3.getText().toString();
        String Correo = editTextEmail3.getText().toString();

        Usuarios usuarios = new Usuarios(id, Usuario, Contrasena, Correo);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(usuarios));

        Call<Usuarios> call = crudUsuariosInterface.updateUsuarioById(id, requestBody);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) {
                    Usuarios updatedUsuario = response.body();
                    if (updatedUsuario != null) {
                        // Handle successful response
                        System.out.println("Usuario updated: " + updatedUsuario.toString());
                    }
                } else {
                    // Handle unsuccessful response
                    System.out.println("Failed to update Usuario. Error: " + response.message());
                }

                // Clear input fields after updating Usuario
                editTextID2.setText("");
                editTextNombre2.setText("");
                editTextPassword3.setText("");
                editTextEmail3.setText("");
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                // Handle failure
                System.out.println("Failed to update Usuario. Error: " + t.getMessage());
            }
        });
    }




}