package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registrar_Evento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__evento);
        Button btnREvento = (Button) findViewById(R.id.btnRevento);

    }
    public void btnREvento (View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, Modificar_Evento.class);
        startActivity(intent);
    }
}
