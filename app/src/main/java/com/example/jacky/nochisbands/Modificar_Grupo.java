package com.example.jacky.nochisbands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar_Grupo extends AppCompatActivity {
    EditText NGrupoM, GeneroM, Telefono1M, Telefono2M, Telefono3M, NContactoM, PHoraM, PFueraM, id_managerM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__grupo);
        Button bntGModif = (Button) findViewById(R.id.btnGCRBanda);
        Button btnREliminar = (Button) findViewById(R.id.btnBeliminado);

        NGrupoM=findViewById(R.id.edtNGrupo2);
        GeneroM=findViewById(R.id.edtGenero2);
        Telefono1M=findViewById(R.id.edtT);
        Telefono2M=findViewById(R.id.edtT4);
        Telefono3M=findViewById(R.id.edtT5);
        PHoraM=findViewById(R.id.edtPrecioH2);
        PFueraM=findViewById(R.id.edtPrecioF2);

    }
    public void btnGModif (View view) {
        String actualizacion="update from Grupo set";
        //clase que permite interaccion entre clases
        if(!NGrupoM.getText().toString().isEmpty()){

        }else{

        }



        Intent intent = new Intent(this, Grupo_Registrado.class);
        startActivity(intent);

    }

}
