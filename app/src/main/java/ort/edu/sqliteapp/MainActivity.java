package ort.edu.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(EditText) findViewById(R.id.nombre);
        email=(EditText) findViewById(R.id.email);
    }
    public void btnSubmit(View v){
        String textName = nombre.getText().toString().trim();
        String textemail = email.getText().toString().trim();
        try{
            DBContactos db =new DBContactos(this);
            db.open();
            db.insert(textName,textemail);
            db.close();
            showMessage("Información agregada con éxito");
            nombre.setText("");
            email.setText("");
        }
        catch (Exception e){
            showMessage("Error al agregar información en la tabla: "+e.getMessage());
        }
    }
    private void showMessage(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0 );
        toast.show();
    }
    public void btnEdit(View v){
        try{
            DBContactos db =new DBContactos(this);
            db.open();
            db.updateEntry(db.getFirstRecord(),"Juan Perez","a@b.com");
            db.close();
            showMessage("Información actualizada con éxito");
        }
        catch (Exception e){
            showMessage("Error al actualizar información en la tabla: "+ e.getMessage());
        }
    }

    public void btnShow(View v){
        try{
            DBContactos db =new DBContactos(this);
            db.open();
            String[] data= db.getData();
            db.close();
            Intent intent = new Intent(this,Data.class);
            intent.putExtra("lista",  data);
            startActivity(intent);
        }
        catch (Exception e){
           showMessage("Error al mostrar "+e.getMessage());
        }
    }

    public void btnDelete(View v){
        try{
            DBContactos db =new DBContactos(this);
            db.open();
            db.deleteEntry(db.getFirstRecord());
            db.close();
            showMessage("Información eliminada con éxito");
        }
        catch (Exception e){
            showMessage("Error al eliminar información de la tabla: "+ e.getMessage());
        }
    }
}
