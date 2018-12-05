package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class Evento_registrado extends AppCompatActivity {
    TextView DescripcionR, NEventoR, NmanagerR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_registrado);

        DescripcionR = findViewById(R.id.edtDescripcion3);
        NEventoR = findViewById(R.id.edtIdEvento3);
        NmanagerR = findViewById(R.id.edtIdManager3);


        Intent intent = getIntent();
        DescripcionR.setText(intent.getStringExtra(Registrar_Evento.IDescripcion));
        NEventoR.setText(intent.getStringExtra(Registrar_Evento.INEvento));
        NmanagerR.setText(intent.getStringExtra(Registrar_Evento.INManager));
    }

    public void Registrado(View view) {

        if (!DescripcionR.getText().toString().isEmpty() && !NEventoR.getText().toString().isEmpty() && !NmanagerR.getText().toString().isEmpty()) {

        } else {
            Toast.makeText(this, "No puede dejar campos vacios",
                    Toast.LENGTH_LONG).show();
        }
    }
}
