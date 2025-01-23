package com.example.a01081123_a21tecfilms;

import static com.example.a01081123_a21tecfilms.AdminBDEventos.NOMBRE_TABLA;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Loguin extends AppCompatActivity {
    private EditText editTextUsuario, editTextContraseña;
    private TextView mRegistro, mCuentas;
    private Button mVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loguin);

        //instancias----------------------------------------
        editTextUsuario = findViewById(R.id.editTextText2);
        editTextContraseña = findViewById(R.id.contraseña);
        mRegistro = findViewById(R.id.registro);
        mCuentas = findViewById(R.id.cuetasRegistradas);
        mVer = findViewById(R.id.btnVer);
        //--------------------------------------------------

        //subrayado del textview registro----------------------------------------------
        String text = mRegistro.getText().toString();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        mRegistro.setText(spannableString);

        String text2 = mCuentas.getText().toString();
        SpannableString spannableString2 = new SpannableString(text2);
        spannableString2.setSpan(new UnderlineSpan(), 0, text2.length(), 0);
        mCuentas.setText(spannableString2);
        //------------------------------------------------------------------------------

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void lanzar(View view) {

        //creacion del objeto de la base de datos bDatos-----------------
        AdminBDEventos adminBDEventos = new AdminBDEventos(this);
        SQLiteDatabase bDatos = adminBDEventos.getWritableDatabase();
        //---------------------------------------------------------------

        //obtencion de los datos-------------------------------------
        String usuario = editTextUsuario.getText().toString();
        String contraseña = editTextContraseña.getText().toString();
        //-----------------------------------------------------------

        // Consulta a la base de datos------------------------------------------------
        String query = "SELECT * FROM " + AdminBDEventos.NOMBRE_TABLA + " WHERE " +
                AdminBDEventos.CAMPO2 + " = ? AND " + AdminBDEventos.CAMPO3 + " = ?";
        Cursor cursor = bDatos.rawQuery(query, new String[]{usuario, contraseña});
        //----------------------------------------------------------------------------

        //inicio de la sesion---------------------------------------------------------------------------------
        if (cursor.moveToFirst()) {

            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            //Mensaje de error
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
        //----------------------------------------------------------------------------------------------------

        // Cerrar el cursor y la base de datos
        cursor.close();
        bDatos.close();


    }

    public void registro(View view){
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }


    public void ircuentas(View view) {

        String cad = "";

        //creacion del objeto de la abse de datos bDatos-----------------
        AdminBDEventos adminBDEventos = new AdminBDEventos(this);
        SQLiteDatabase bDatos = adminBDEventos.getWritableDatabase();
        //---------------------------------------------------------------


        String query="SELECT * FROM "+NOMBRE_TABLA;
        Cursor cursor = bDatos.rawQuery(query, null);

        if (cursor.getCount()>0){

            while (cursor.moveToNext()){
                int clave = cursor.getInt(0);
                String usuario = cursor.getString(1);
                String correo  = cursor.getString(2);

                cad += (clave + "    " + usuario + "    " + correo + "\n");
            }

            Intent intent = new Intent(this, CuentasActivity.class);
            intent.putExtra("LISTA", cad);
            startActivity(intent);

        }else{
            Toast.makeText(this, "No hay cuentas registrados", Toast.LENGTH_SHORT).show();
        }

    }
    public void verOcultar(View view) {

        if(editTextContraseña.getInputType()==129){//129 oculto
            editTextContraseña.setInputType(145);//visible
            mVer.setBackgroundResource(R.drawable.ocultar);
        }else{
            editTextContraseña.setInputType(129);// se oculta
            mVer.setBackgroundResource(R.drawable.ver);
        }
    }

}