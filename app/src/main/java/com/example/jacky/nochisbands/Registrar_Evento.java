package com.example.jacky.nochisbands;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Registrar_Evento extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    public final static String IDescripcion = "Descripcion";
    public final static String INEvento = "NEvento";
    public final static String INManager = "NMamager";

    //subir foto
    private static final int COD_SELECCIONADA = 10;
    private static final int COD_FOTO = 20;
    private static final String CARPETA_IMAGEN = "imagenes";
    private static final String CARPETA_PRINCIPAL = "misImagenesApp";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private final int MIS_PERMISOS = 100;
    private Uri miPath;
    File fileImagen;
    Bitmap bitmap;
    private String path;

    EditText Descripcion, NEvento, Nmanager;
    ImageView Foto;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__evento);
        Button btnREvento = (Button) findViewById(R.id.btnRevento);

        Descripcion=findViewById(R.id.edtDescripcion);
        NEvento=findViewById(R.id.edtIdEvento);
        Nmanager=findViewById(R.id.edtIdManager);
        Foto = findViewById(R.id.subirFoto);
        requestQueue = Volley.newRequestQueue(this);


    }
    public void btnREvento (View view) {
            String url = "https://bar-la-diabla.000webhostapp.com/php/insertarevento.php?" +
                    "id_Evento=" + NEvento.getText().toString() + "&" +
                    "Descripcion=" + Descripcion.getText().toString() + "&" +
                    "id_manger=" + Nmanager.getText().toString() + "";
            url = url.replace(" ", "%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);
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
            if(message.equals("Ingresado")){
                Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Modificar_Evento.class);
                intent.putExtra(IDescripcion,Descripcion.getText().toString());
                intent.putExtra(INEvento, NEvento.getText().toString());
                intent.putExtra(INManager, Nmanager.getText().toString());
                startActivity(intent);
            }else{
                //no existe
            }
        }catch (Exception e){
            Toast.makeText(this, "Algo salio mal :( \n"+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void abriCamara() {
        File miFile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada=miFile.exists();
        if(isCreada){
            isCreada=miFile.mkdirs();
        }
        if(isCreada){
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre=consecutivo.toString()+".jpg";
            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;//indicamos la ruta de almacenamiento
            fileImagen=new File(path);
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities=this.getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(this,authorities,fileImagen);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else
            {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
            }
            startActivityForResult(intent,COD_FOTO);
        }
    }

    public void subirFoto(View view){
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    abriCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        Intent intent=new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONADA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {

        int ancho=bitmap.getWidth();
        int alto=bitmap.getHeight();

        if(ancho>anchoNuevo || alto>altoNuevo){
            float escalaAncho=anchoNuevo/ancho;
            float escalaAlto= altoNuevo/alto;

            Matrix matrix=new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);

            return Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);

        }else{
            return bitmap;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONADA:
                miPath=data.getData();
                Foto.setImageURI(miPath);
                try {
                    bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),miPath);
                    Foto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(this, new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });

                bitmap= BitmapFactory.decodeFile(path);
                Foto.setImageBitmap(bitmap);
                break;
        }
        bitmap=redimensionarImagen(bitmap,600,800);
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    private boolean solicitaPermisosVersionesSuperiores() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){//validamos si estamos en android menor a 6 para no buscar los permisos
            return true;
        }
        if((this.checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&this.checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED){
            return true;
        }
        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)||(shouldShowRequestPermissionRationale(CAMERA)))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MIS_PERMISOS);
        }
        return false;
    }
}
