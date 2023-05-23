package com.example.parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity4 extends AppCompatActivity implements View.OnClickListener{



    private Button boton_Diarios;
    private Button boton_Semanal;
    private Button boton_Todos;
    private Button boton_Mensual;
    private ImageButton boton_Crear;
    private ImageButton boton_Ajustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        boton_Diarios = findViewById(R.id.boton_diarios);
        boton_Semanal = findViewById(R.id.boton_semanal);
        boton_Mensual = findViewById(R.id.boton_mensual);
        boton_Todos = findViewById(R.id.boton_todos);

        boton_Diarios.setOnClickListener(this);
        boton_Semanal.setOnClickListener(this);
        boton_Mensual.setOnClickListener(this);
        boton_Todos.setOnClickListener(this);

        boton_Crear = findViewById(R.id.txtbotonC4);
        boton_Ajustes = findViewById(R.id.txtbotonC5);
        boton_Ajustes.setOnClickListener(this);
        boton_Crear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.equals(boton_Crear)) {
            Intent intent = new Intent(Activity4.this, Crear.class);
            startActivity(intent);
        }else if(v.equals(boton_Ajustes)){
            Intent intent = new Intent(Activity4.this, ActualizarDatos.class);
            startActivity(intent);

        }else{

        }

    }
}