package com.example.jacky.nochisbands;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Registrar_Grupo extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    EditText NGrupo, Genero, Telefono1, Telefono2, Telefono3, PHora, PFuera, NUMGrup;
   Spinner id_manager;
    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__grupo);
        Button bntRGrupo = (Button) findViewById(R.id.bntGModif);
        requestQueue = Volley.newRequestQueue(this);

        progreso = new ProgressDialog(this);
        progreso.setMessage("Validando...");
        progreso.show();

        String url = "https://bar-la-diabla.000webhostapp.com/php/Consultas.php?Consulta=select%20id_manger%20from%20Manager";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
        NUMGrup=findViewById(R.id.edtNumBan);
        NGrupo=findViewById(R.id.edtNGrupo);
        Genero=findViewById(R.id.edtGenero);
        Telefono1=findViewById(R.id.edtTel1);
        Telefono2=findViewById(R.id.edtTel2);
        Telefono3=findViewById(R.id.edtTel3);
        PHora=findViewById(R.id.edtPrecioH);
        PFuera=findViewById(R.id.edtPrecioF);
        id_manager=findViewById(R.id.spinnerIdM);
        requestQueue = Volley.newRequestQueue(this);


    }
    public void bntRGrupo (View view) {
        //clase que permite interaccion entre clases
        String url = "https://bar-la-diabla.000webhostapp.com/php/insertargrupo.php?" +
                "id_grupo="+NUMGrup.getText().toString()+"&" +
                "Nombre_Banda="+NGrupo.getText().toString()+"&" +
                "Precio_hora="+PHora.getText().toString()+"&" +
                "Precio_fuera="+PFuera.getText().toString()+"&" +
                "Telefono1="+Telefono1.getText().toString()+"&" +
                "Telefono2="+Telefono2.getText().toString()+"&" +
                "Telefono3="+Telefono3.getText().toString()+"&" +
                "id_manger="+id_manager.getSelectedItem().toString()+"&" +
                "Genero="+Genero.getText().toString()+"";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Registrar_Grupo.this, "Registrado¡ \n", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "!Ups¡ \n" + error.toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Modificar_Grupo.class);
        startActivity(intent);

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("Datos");
        JSONObject jsonObject = null;
        String[] id_manger = new String[jsonArray.length()];
        try {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                id_manger[i] = jsonObject.optString("id_manger");
            }
            List<String> listSpinner = Arrays.asList(id_manger);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
            id_manager.setAdapter(adapter);

        }catch (Exception e){
            progreso.hide();
            Toast.makeText(this, "Algo salio mal :( \n"
                    +e.toString(), Toast.LENGTH_LONG).show();
        }
        progreso.hide();
    }

}
