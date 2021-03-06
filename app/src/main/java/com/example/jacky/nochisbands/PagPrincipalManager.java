package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PagPrincipalManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_principal_manager);
        Button btnRGrupo = (Button) findViewById(R.id.btnRGrupo);
        Button btnREvento = (Button) findViewById(R.id.btnRevento);
       // Button btnagenda = (Button) findViewById(R.id.btnagenda);
        Button btnEliminar = (Button) findViewById(R.id.btneliminar);
        Button btnEE = (Button) findViewById(R.id.btnEE);
    }
    public void btnRGrupo(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, Registrar_Grupo.class);
        startActivity(intent);
    }
    public void btnRevento(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, Registrar_Evento.class);
        startActivity(intent);
    }
    public void btnagenda(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, Agenda.class);
        startActivity(intent);
    }
    public void btnEliminar(View view){
        Intent intent=new Intent(this, Eliminar_Grupo.class);
        startActivity(intent);
    }
    public void btnEE(View view){
        Intent intent=new Intent(this, Eliminar_Evento.class);
        startActivity(intent);
    }
    public void regresar(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

