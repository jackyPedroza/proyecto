package com.example.jacky.nochisbands;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class LogiManager extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText contrasena, usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logi_manager2);
        Button btnIsesion = (Button) findViewById(R.id.btnIsesion);

        usuario=findViewById(R.id.usuario);
        contrasena=findViewById(R.id.password);
        requestQueue = Volley.newRequestQueue(this);
    }
    public void btnIsesion(View view) {
        //clase que permite interaccion entre clases
        progreso = new ProgressDialog(this);
        progreso.setMessage("Validando...");
        progreso.show();
        String url = "https://bar-la-diabla.000webhostapp.com/php/Consultas.php?Consulta=select%20*%20from%20Manager%20where%20Usuario=%27"
                +usuario.getText().toString()+"%27%20and%20Contrase%C3%B1a=%27"
                +contrasena.getText().toString()+"%27";
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "!Ups¡ \n" + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("Datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            String id_manager=jsonObject.optString("id_manger");
            if(!id_manager.equals("0")){
                //el usuario existe
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, PagPrincipalManager.class);
                startActivity(intent);
            }else{
                //no existe
            }
        }catch (Exception e){
            Toast.makeText(this, "Algo salio mal :( \n"+e.toString(), Toast.LENGTH_LONG).show();
        }
        progreso.hide();
    }
}
