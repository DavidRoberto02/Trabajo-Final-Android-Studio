package com.example.evidencia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Correo, contraseña, NombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Se establecen los textos y botones que tendran funciones
        Correo = findViewById(R.id.Correo);
        contraseña = findViewById(R.id.contraseña);
        NombreUsuario = findViewById(R.id.NombreUsuario);

    }
    public void ingreso(View view) {
        // Aqui se realizara la función del boton para detectar si el usuario y contraseña son correctos
        AdminSQLite registro = new AdminSQLite(this, "registroapp", null, 1);
        SQLiteDatabase db = registro.getWritableDatabase();

        String Username = NombreUsuario.getText().toString();
        String Email = Correo.getText().toString();
        String password = contraseña.getText().toString();

        Email.equals("admin");
        password.equals("12345");
        Username.equals("2011");

        ContentValues agregar = new ContentValues();
        agregar.put("Username", Username );
        agregar.put("Email", Email );
        agregar.put("password", password);
        db.insert("login", null, agregar);
        db.close();
        NombreUsuario.setText("");
        Correo.setText("");
        contraseña.setText("");

        if (!Email.isEmpty() && !password.isEmpty() && !Username.isEmpty())
        {
            Intent segunda = new Intent(MainActivity.this, Empleado1.class);
            startActivity(segunda);
            overridePendingTransition ( R.anim.zoom_back_in, R.anim.zoom_back_out );
        }else{
            Toast.makeText(getApplicationContext(),"Su username, correo o contraseña son incorrectos, por favor ingreselos de nuevo ", Toast.LENGTH_SHORT).show();
        }


    }
}