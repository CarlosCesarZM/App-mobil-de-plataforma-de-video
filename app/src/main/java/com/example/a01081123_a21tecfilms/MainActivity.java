package com.example.a01081123_a21tecfilms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
    //Utilizamos el OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings){
            Toast.makeText(this,"Configuracion",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void lanzar1(View view) {
        Intent intent = new Intent(this, SeriesActivity.class);
        startActivity(intent);
    }

    public void lanzarPago(View view) {
        Intent intent = new Intent(this, PasareladePagoActivity.class);
        startActivity(intent);
    }

    public void lanzar2(View view) {
        Intent intent = new Intent(this, SeriesActivity.class);
        startActivity(intent);
    }
    public void lanzar3(View view) {
        Intent intent = new Intent(this, PeliculasActivity.class);
        startActivity(intent);
    }

    public void favoritos(View view) {
        Intent intent = new Intent(this, FavoritosActivity.class);
        startActivity(intent);
    }

}
