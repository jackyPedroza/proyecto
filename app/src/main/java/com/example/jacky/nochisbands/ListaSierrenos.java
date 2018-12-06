package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListaSierrenos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sierrenos);
    }
    public void regresar(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, PagPrincipalUsuario.class);
        startActivity(intent);
    }
}
