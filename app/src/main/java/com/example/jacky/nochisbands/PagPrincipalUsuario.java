package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PagPrincipalUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_principal);
    }
    public void btnBanda(View view){
        Intent intent=new Intent(this, ListaBandas.class);
        startActivity(intent);
    }
    public void btnMariachi(View view){
        Intent intent=new Intent(this, ListaMariachi.class);
        startActivity(intent);
    }
    public void btnNorteño(View view){
        Intent intent=new Intent(this, ListaNortenos.class);
        startActivity(intent);
    }
    public void btnSierreño(View view){
        Intent intent=new Intent(this, ListaSierrenos.class);
        startActivity(intent);
    }

}
