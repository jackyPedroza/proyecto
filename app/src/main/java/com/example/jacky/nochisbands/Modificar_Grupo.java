package com.example.jacky.nochisbands;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class Modificar_Grupo extends AppCompatActivity implements  Response.Listener<JSONObject>, Response.ErrorListener {
    public final static String INombreGrupo = "NombreGrupoM";
    public final static String IGenernoM = "GeneroM";
    public final static String ITelefono1M = "Telefono1M";
    public final static String ITelefono2M = "Telefono2M";
    public final static String ITelefono3M = "Telefono3M";
    public final static String IPHoraM = "PHoraM";
    public final static String IPFueraM = "PFueraM";
    public final static String INGrupoM = "NGrupoM";
    public final static String Iid_managerM = "id_managerM";

    EditText  NGrupoM, GeneroM, Telefono1M, Telefono2M, Telefono3M,  PHoraM, PFueraM, NUMGrupoM, id_managerM;
    ImageView Foto;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__grupo);
        Button bntGModif = (Button) findViewById(R.id.btnGCRBanda);

        NGrupoM=findViewById(R.id.edtNGrupo2);
        GeneroM=findViewById(R.id.edtGenero2);
        Telefono1M=findViewById(R.id.edtT);
        Telefono2M=findViewById(R.id.edtT4);
        Telefono3M=findViewById(R.id.edtT5);
        PHoraM=findViewById(R.id.edtPrecioH2);
        PFueraM=findViewById(R.id.edtPrecioF2);
        NUMGrupoM=findViewById(R.id.edtNumBan2);
        id_managerM=findViewById(R.id.edtNM2);
        Foto = findViewById(R.id.subirFoto);
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        NGrupoM.setText(intent.getStringExtra(Registrar_Grupo.INombreGrupo));
        GeneroM.setText(intent.getStringExtra(Registrar_Grupo.IGenernoM));
        Telefono1M.setText(intent.getStringExtra(Registrar_Grupo.ITelefono1M));
        Telefono2M.setText(intent.getStringExtra(Registrar_Grupo.ITelefono2M));
        Telefono3M.setText(intent.getStringExtra(Registrar_Grupo.ITelefono3M));
        PHoraM.setText(intent.getStringExtra(Registrar_Grupo.IPHoraM));
        PFueraM.setText(intent.getStringExtra(Registrar_Grupo.IPFueraM));
        NUMGrupoM.setText(intent.getStringExtra(Registrar_Grupo.INGrupoM));
        id_managerM.setText(intent.getStringExtra(Registrar_Grupo.Iid_managerM));
    }
    public void btnGModif (View view) {

        if(!NGrupoM.getText().toString().isEmpty()&& !GeneroM.getText().toString().isEmpty()&& !Telefono1M.getText().toString().isEmpty()&& !Telefono2M.getText().toString().isEmpty()&& !Telefono3M.getText().toString().isEmpty()&& !PHoraM.getText().toString().isEmpty()&& !PFueraM.getText().toString().isEmpty()&&  !NUMGrupoM.getText().toString().isEmpty()&&  !id_managerM.getText().toString().isEmpty() ){
            String url = "https://bar-la-diabla.000webhostapp.com/php/modificarGrupo.php?" +
                    "id_grupo="+NUMGrupoM.getText().toString()+"&" +
                    "Nombre_Banda="+NGrupoM.getText().toString()+"&" +
                    "Precio_hora="+PHoraM.getText().toString()+"&" +
                    "Precio_fuera="+PFueraM.getText().toString()+"&" +
                    "Telefono1="+Telefono1M.getText().toString()+"&" +
                    "Telefono2="+Telefono2M.getText().toString()+"&" +
                    "Telefono3="+Telefono3M.getText().toString()+"&" +
                    "id_manger="+id_managerM.getText().toString()+"&" +
                    "Genero="+GeneroM.getText().toString()+"";
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
                Intent intent = new Intent(this, Grupo_Registrado.class);
                intent.putExtra(INombreGrupo, NGrupoM.getText().toString());
                intent.putExtra(IGenernoM, GeneroM.getText().toString());
                intent.putExtra(ITelefono1M, Telefono1M.getText().toString());
                intent.putExtra(ITelefono2M, Telefono2M.getText().toString());
                intent.putExtra(ITelefono3M, Telefono3M.getText().toString());
                intent.putExtra(IPHoraM, PHoraM.getText().toString());
                intent.putExtra(IPFueraM, PFueraM.getText().toString());
                intent.putExtra(INGrupoM, NUMGrupoM.getText().toString());
                intent.putExtra(Iid_managerM, id_managerM.getText().toString());
                startActivity(intent);
            }else{
                //no existe
            }
        }catch (Exception e){
            Toast.makeText(this, "Algo salio mal :( \n"+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
