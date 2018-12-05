package com.example.jacky.nochisbands;

import android.content.Intent;
import android.nfc.NfcManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class Modificar_Evento extends AppCompatActivity implements  Response.Listener<JSONObject>, Response.ErrorListener {
    public final static String IDescripcion = "Descripcion";
    public final static String INEvento = "NEvento";
    public final static String INManager = "NMamager";

    EditText DescripcionM, NEventoM, NmanagerM;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__evento);
        Button btnGCEventos = (Button) findViewById(R.id.bntGModif);

        DescripcionM=findViewById(R.id.edtDescripcion2);
        NEventoM=findViewById(R.id.edtIdEvento2);
        NmanagerM=findViewById(R.id.edtIdManager2);
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        DescripcionM.setText(intent.getStringExtra(Registrar_Evento.IDescripcion));
        NEventoM.setText(intent.getStringExtra(Registrar_Evento.INEvento));
        NmanagerM.setText(intent.getStringExtra(Registrar_Evento.INManager));


    }
    public void btnGCEventos (View view) {
        if(!DescripcionM.getText().toString().isEmpty()&& !NEventoM.getText().toString().isEmpty()&& !NmanagerM.getText().toString().isEmpty() ){
        String url ="https://bar-la-diabla.000webhostapp.com/php/modificarEvento.php?" +
                "id_Evento="+NEventoM.getText().toString()+"&" +
                "Descripcion="+DescripcionM.getText().toString()+"&" +
                "id_manger="+NmanagerM.getText().toString()+"";
        //clase que permite interaccion entre clases
            url = url.replace(" ", "%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);
        }else{
            Toast.makeText(this, "No puede dejar campos vacios",
                    Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "!Ups¡ \n" + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("Datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            String message=jsonObject.optString("message");
            if(message.equals("Modificado")){
                Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Evento_registrado.class);
                intent.putExtra(IDescripcion, DescripcionM.getText().toString());
                intent.putExtra(INEvento, NEventoM.getText().toString());
                intent.putExtra(INManager, NmanagerM.getText().toString());
                startActivity(intent);
            }else{
                //no existe
            }
        }catch (Exception e){
            Toast.makeText(this, "Algo salio mal :( \n"+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
