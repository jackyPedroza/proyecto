package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    Button btnManager = (Button) findViewById(R.id.btnManager);
    Button btnUsuario = (Button) findViewById(R.id.btnUsuario);
}

    public void btnManager(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, LogiManager.class);
        startActivity(intent);
    }
    public void btnUsuario(View view) {
        //clase que permite interaccion entre clases
        Intent intent = new Intent(this, PagPrincipalUsuario.class);
        startActivity(intent);
    }
}

