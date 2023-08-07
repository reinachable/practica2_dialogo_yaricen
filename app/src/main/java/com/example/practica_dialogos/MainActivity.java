package com.example.practica_dialogos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String NOMBRE_ASISTENTE = "nombre_asistente";
    String TELEFONO_ASISTENTE = "telefono_asistente";
    String CORREO_ASISTENTE = "correo_asistente";
    String CONFERENCIA_ASISTENTE = "conferencia_asistente";
    String NOMBRE_EXPOSITOR = "nombre_expositor";
    String TELEFONO_EXPOSITOR = "telefono_expositor";
    String CORREO_EXPOSITOR = "correo_expositor";
    String PLATICA_EXPOSITOR = "platica_expositor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MostrarDialogo();

        Button registrosExpo = (Button) findViewById(R.id.btnVerRegistrosExpositor);
        registrosExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verRegistrosExpo();
            }
        });

        Button registrosAsist = (Button) findViewById(R.id.btnRegistrosAsistente);
        registrosAsist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verRegistrosAsist();
            }
        });


        Button registro = (Button) findViewById(R.id.btnSalir);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarDialogo();
            }
        });

        Button borrarDatosSalir = (Button) findViewById(R.id.btnSalirBorrar);
        borrarDatosSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BorrarDatosySalir();
            }
        });

        Button salir = (Button) findViewById(R.id.btnSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });
    }

    private void BorrarDatosySalir() {
        SharedPreferences sharedPreferencesAsist = getSharedPreferences("datosAsist", MODE_PRIVATE);
        SharedPreferences sharedPreferencesExpo = getSharedPreferences("datosExpo", MODE_PRIVATE);

        SharedPreferences.Editor editorAsist = sharedPreferencesAsist.edit();
        editorAsist.clear();
        editorAsist.apply();
        SharedPreferences.Editor editorExpo = sharedPreferencesExpo.edit();
        editorExpo.clear();
        editorExpo.apply();
        finishAffinity();
    }

    private void salir() {
        finishAffinity();
    }

    private void MostrarDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialogo, null);
        builder.setView(view);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        TextView txtBienvenida = view.findViewById(R.id.txtBienvenida);
        txtBienvenida.setText("Bienvenido");
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtDescripcion.setText("Esta es la app de registro al congreso de informatica , selecciona una opcion por favor");

        Button btnAsistente = view.findViewById(R.id.btnAsistente);
        btnAsistente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Elegiste Asistente", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                MostrarFormularioAsistente();
            }
        });

        Button btnExpositor = view.findViewById(R.id.btnExpositor);
        btnExpositor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Elegiste Expositor", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                MostrarFormularioExpositor();
            }
        });
    }


    private void MostrarFormularioExpositor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_expositor, null);
        builder.setView(view);
        builder.setCancelable(false); // Evita que el diálogo se cierre al hacer clic fuera de él
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText etNombre = view.findViewById(R.id.etNombre);
        EditText etTelefono = view.findViewById(R.id.etTelefono);
        EditText etCorreo = view.findViewById(R.id.etCorreo);
        EditText etPlatica = view.findViewById(R.id.etPlatica);

        String nombreExpositor = etNombre.getText().toString();
        String telefonoExpositor = etTelefono.getText().toString();
        String correoExpositor = etCorreo.getText().toString();
        String platicaExpositor = etPlatica.getText().toString();

        Button btnGuardarExpo = (Button) view.findViewById(R.id.btnGuardarExpo);
        btnGuardarExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("datosExpo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(NOMBRE_EXPOSITOR, nombreExpositor);
                editor.putString(TELEFONO_EXPOSITOR, telefonoExpositor);
                editor.putString(CORREO_EXPOSITOR, correoExpositor);
                editor.putString(PLATICA_EXPOSITOR, platicaExpositor);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button btnCancelarExpo = (Button) view.findViewById(R.id.btnCancelarExpo);
        btnCancelarExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void MostrarFormularioAsistente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_asistente, null);
        builder.setView(view);
        builder.setCancelable(false); // Evita que el diálogo se cierre al hacer clic fuera de él
        AlertDialog dialog = builder.create();
        dialog.show();

        // Definir las opciones del Spinner
        String[] opcionesConferencias = {"Conferencia A", "Conferencia B", "Conferencia C"};
        Spinner spinnerConferencias = view.findViewById(R.id.spinnerConferencias);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesConferencias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConferencias.setAdapter(adapter);

        EditText etNombreAsistente = view.findViewById(R.id.etNombreAsistente);
        EditText etTelefonoAsistente = view.findViewById(R.id.etTelefonoAsistente);
        EditText etCorreoAsistente = view.findViewById(R.id.etCorreoAsistente);

        String nombreAsistente = etNombreAsistente.getText().toString();
        String telefonoAsistente = etTelefonoAsistente.getText().toString();
        String correoAsistente = etCorreoAsistente.getText().toString();
        String conferenciaAsistente = spinnerConferencias.getSelectedItem().toString();

        Button btnGuardarAsist = (Button) view.findViewById(R.id.btnGuardarAsist);
        btnGuardarAsist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("datosAsist", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(NOMBRE_ASISTENTE, nombreAsistente);
                editor.putString(TELEFONO_ASISTENTE, telefonoAsistente);
                editor.putString(CORREO_ASISTENTE, correoAsistente);
                editor.putString(CONFERENCIA_ASISTENTE, conferenciaAsistente);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button btnCancelarAsist = (Button) view.findViewById(R.id.btnCancelarAsist);
        btnCancelarAsist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void verRegistrosExpo(){
        SharedPreferences sharedPreferencesExpo = getSharedPreferences("datosExpo", MODE_PRIVATE);
        String nombreExpositor = sharedPreferencesExpo.getString(NOMBRE_EXPOSITOR, "");
        String telefonoExpositor = sharedPreferencesExpo.getString(TELEFONO_EXPOSITOR, "");
        String correoExpositor = sharedPreferencesExpo.getString(CORREO_EXPOSITOR, "");
        String platicaExpositor = sharedPreferencesExpo.getString(PLATICA_EXPOSITOR, "");


        View toastView = null;
        TextView tvRegistros = toastView.findViewById(R.id.txtDescripcion);
        tvRegistros.setText("Registros:\n\n" +
                "Expositor:\n" +
                "Nombre: " + nombreExpositor + "\n" +
                "Teléfono: " + telefonoExpositor + "\n" +
                "Correo: " + correoExpositor + "\n" +
                "Plática: " + platicaExpositor);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 50);
        toast.show();
    }

    public void verRegistrosAsist(){
        SharedPreferences sharedPreferencesAsist = getSharedPreferences("datosAsist", MODE_PRIVATE);
        String nombreAsistente = sharedPreferencesAsist.getString(NOMBRE_ASISTENTE, "");
        String telefonoAsistente = sharedPreferencesAsist.getString(TELEFONO_ASISTENTE, "");
        String correoAsistente = sharedPreferencesAsist.getString(CORREO_ASISTENTE, "");
        String conferenciaAsistente = sharedPreferencesAsist.getString(CONFERENCIA_ASISTENTE, "");

        View toastView = null;
        TextView tvRegistros = toastView.findViewById(R.id.txtRegistros);
        tvRegistros.setText("Registros:\n\n" +
                "Asistente:\n" +
                "Nombre: " + nombreAsistente + "\n" +
                "Teléfono: " + telefonoAsistente + "\n" +
                "Correo: " + correoAsistente + "\n" +
                "Conferencia: " + conferenciaAsistente);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 50);
        toast.show();
    }
}