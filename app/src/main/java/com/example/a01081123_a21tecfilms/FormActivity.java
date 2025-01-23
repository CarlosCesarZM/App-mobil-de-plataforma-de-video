package com.example.a01081123_a21tecfilms;

import static com.example.a01081123_a21tecfilms.AdminBDEventos.CAMPO1;
import static com.example.a01081123_a21tecfilms.AdminBDEventos.CAMPO2;
import static com.example.a01081123_a21tecfilms.AdminBDEventos.CAMPO3;
import static com.example.a01081123_a21tecfilms.AdminBDEventos.NOMBRE_TABLA;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormActivity extends AppCompatActivity {

    private EditText mUsuario, mCorreo;
    private Button mRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);

        //instancias--------------------------------
        mUsuario = findViewById(R.id.usuario);
        mCorreo = findViewById(R.id.correo);
        mRegistro = findViewById(R.id.btnRegistro);
        //------------------------------------------

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fondoRegistro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void registrar(View view) {
        //creacion del objeto de la abse de datos bDatos-----------------
        AdminBDEventos adminBDEventos = new AdminBDEventos(this);
        SQLiteDatabase bDatos = adminBDEventos.getWritableDatabase();
        //---------------------------------------------------------------

        //obtencion de los datos de las vistas----------
        String usuario = mUsuario.getText().toString();
        String correo = mCorreo.getText().toString();
        //----------------------------------------------


        if (usuario.isEmpty() && correo.isEmpty()){
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            ContentValues registro = new ContentValues();
            registro.put(CAMPO2, usuario);
            registro.put(CAMPO3, correo);

            bDatos.insert(NOMBRE_TABLA, null, registro);

            limpiarCampos();

            Toast.makeText(this, "Cuenta registrada", Toast.LENGTH_SHORT).show();
            bDatos.close();
            lanzar(view);
        }
    }

    private void limpiarCampos(){
        mUsuario.setText("");
        mCorreo.setText("");
    }

    public void lanzar(View view) {
        Intent intent = new Intent(this, Loguin.class);
        startActivity(intent);
    }
}