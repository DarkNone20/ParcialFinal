package com.example.parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Crear extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextTitulo;
    private EditText editTextMensaje;
    private ImageButton boton_Colores;
    private ImageButton boton_Alarma;
    private ImageButton boton_Calendario;
    private Calendar alarmDateTime;

    private Button boton_Guardar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextMensaje = findViewById(R.id.editTextMensaje);
        boton_Colores = findViewById(R.id.txtColores);
        boton_Alarma = findViewById(R.id.txtAlarmas);
        boton_Calendario = findViewById(R.id.txtCalendario);
        boton_Guardar2= findViewById(R.id.txtGuardar);

        boton_Alarma.setOnClickListener(this);
        boton_Calendario.setOnClickListener(this);
        boton_Colores.setOnClickListener(this);
        boton_Guardar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txtAlarmas) {

            // Crear un cuadro de diálogo de selección de sonidos
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Seleccionar Sonido")
                    .setItems(R.array.sounds_array, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // El usuario ha seleccionado un sonido
                            String selectedSound = getResources().getStringArray(R.array.sounds_array)[which];
                            Toast.makeText(Crear.this, "Sonido seleccionado: " + selectedSound, Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();


        } else if (v.getId() == R.id.txtCalendario) {
            // Obtener la fecha actual
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear un DatePickerDialog para seleccionar la fecha
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // Configurar la fecha seleccionada en el calendario
                    alarmDateTime = Calendar.getInstance();
                    alarmDateTime.set(Calendar.YEAR, year);
                    alarmDateTime.set(Calendar.MONTH, monthOfYear);
                    alarmDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // Mostrar la fecha seleccionada en un Toast
                    Toast.makeText(Crear.this, "Fecha seleccionada: " + alarmDateTime.getTime().toString(), Toast.LENGTH_SHORT).show();
                }
            }, year, month, day);

            // Mostrar el DatePickerDialog
            datePickerDialog.show();
        }else if (v.getId() == R.id.txtColores) {
            // Crear un cuadro de diálogo de selección de hora
            TimePickerDialog timePickerDialog = new TimePickerDialog(Crear.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // Configurar la hora seleccionada en el calendario
                    if (alarmDateTime == null) {
                        alarmDateTime = Calendar.getInstance();
                    }
                    alarmDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    alarmDateTime.set(Calendar.MINUTE, minute);

                    // Mostrar la hora seleccionada en un Toast
                    Toast.makeText(Crear.this, "Hora seleccionada: " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                }
            }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false);

            // Mostrar el cuadro de diálogo de selección de hora
            timePickerDialog.show();
        } else if (v.getId() == R.id.txtGuardar) {

            String titulo = editTextTitulo.getText().toString();
            String mensaje = editTextMensaje.getText().toString();

            // Obtener la fecha y hora actual
            Calendar fechaHoraActual = Calendar.getInstance();

            // Verificar si se seleccionó una fecha y hora de alarma
            if (alarmDateTime != null) {
                // Usar la fecha y hora de alarma seleccionada
                fechaHoraActual = alarmDateTime;
            }

            // Guardar el título, mensaje, fecha y hora en alguna estructura de datos o base de datos
            // Aquí puedes implementar la lógica para guardar los datos según tus necesidades

            Toast.makeText(Crear.this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
        }

    }
}