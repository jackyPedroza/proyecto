package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Modificar_Evento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__evento);
        Button btnGCEventos = (Button) findViewById(R.id.bntGModif);

    }
    public void btnGCEventos (View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, Evento_registrado.class);
        startActivity(intent);
    }
}
