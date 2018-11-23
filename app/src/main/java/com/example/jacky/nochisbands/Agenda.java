package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Agenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
       // Button btnAgendar = (Button) findViewById(R.id.btnAgendar);
    }
    public void btnAgendar(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, Agendar.class);
        startActivity(intent);
    }
}
