package com.example.a01081123_a21tecfilms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CuentasActivity extends AppCompatActivity {

    private TextView mLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuentas);

        //instancias-----------------------
        mLista = findViewById(R.id.lista);
        //---------------------------------

        Intent intent = getIntent();
        String cad = intent.getStringExtra("LISTA");

        mLista.setText(cad);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void LanzarLoguin(View view) {
        Intent intent = new Intent(this, Loguin.class);
        startActivity(intent);
    }
}