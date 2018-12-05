package com.example.jacky.nochisbands;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

public class Grupo_Registrado extends AppCompatActivity {

    TextView  NGrupoR, GeneroR, Telefono1R, Telefono2R, Telefono3R, PHoraR, PFueraR, NUMGrupoR, id_managerR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo__registrado);
        NGrupoR = findViewById(R.id.edtNGrupo3);
        GeneroR = findViewById(R.id.edtGenero3);
        Telefono1R = findViewById(R.id.edtTele1);
        Telefono2R = findViewById(R.id.edtTele2);
        Telefono3R = findViewById(R.id.edtTele3);
        PHoraR = findViewById(R.id.edtPrecioH3);
        PFueraR = findViewById(R.id.edtPrecioF3);
        NUMGrupoR = findViewById(R.id.edtNumBan3);
        id_managerR = findViewById(R.id.edtNM3);

        Intent intent = getIntent();
        NGrupoR.setText(intent.getStringExtra(Registrar_Grupo.INombreGrupo));
        GeneroR.setText(intent.getStringExtra(Registrar_Grupo.IGenernoM));
        Telefono1R.setText(intent.getStringExtra(Registrar_Grupo.ITelefono1M));
        Telefono2R.setText(intent.getStringExtra(Registrar_Grupo.ITelefono2M));
        Telefono3R.setText(intent.getStringExtra(Registrar_Grupo.ITelefono3M));
        PHoraR.setText(intent.getStringExtra(Registrar_Grupo.IPHoraM));
        PFueraR.setText(intent.getStringExtra(Registrar_Grupo.IPFueraM));
        NUMGrupoR.setText(intent.getStringExtra(Registrar_Grupo.INGrupoM));
        id_managerR.setText(intent.getStringExtra(Registrar_Grupo.Iid_managerM));
    }

    public void Registrado(View view) {

        if (!NGrupoR.getText().toString().isEmpty() && !GeneroR.getText().toString().isEmpty() && !Telefono1R.getText().toString().isEmpty() && !Telefono2R.getText().toString().isEmpty() && !Telefono3R.getText().toString().isEmpty() && !PHoraR.getText().toString().isEmpty() && !PFueraR.getText().toString().isEmpty() && !NUMGrupoR.getText().toString().isEmpty() && !id_managerR.getText().toString().isEmpty()) {

        } else {
            Toast.makeText(this, "No puede dejar campos vacios",
                    Toast.LENGTH_LONG).show();
        }
    }
}
