package com.example.evidencia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Empleado1 extends AppCompatActivity {

    //Aqui declaro los botones y textview que tendran funciones.
    private Button igual, navegar;
    private EditText dia0, dia1, dia2, dia3, dia4, dia5, fecha;
    private TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado1);
        //Enlazamos los textos donde escribira el usuario en su interfaz con el codigo.
        dia0 = findViewById(R.id.dia0 );
        dia1 = findViewById(R.id.dia1 );
        dia2 = findViewById(R.id.dia2 );
        dia3 = findViewById(R.id.dia3 );
        dia4 = findViewById(R.id.dia4 );
        dia5 = findViewById(R.id.dia5 );
        fecha = findViewById(R.id.fecha);

        //Se le dara la funcion de al ingresar todos los numeros este boton los sumara y dara el resultado.
        resultado = findViewById(R.id.resultado );
        igual = findViewById(R.id.igual);
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SUMA DE TODOS LOS DIAS
                resultado.setText(suma(Integer.parseInt(dia0.getText().toString()),Integer.parseInt(dia1.getText().toString()),
                        Integer.parseInt(dia2.getText().toString()),Integer.parseInt(dia3.getText().toString()),
                        Integer.parseInt(dia4.getText().toString()), Integer.parseInt(dia4.getText().toString()) ) +" "+ fecha.getText().toString());
            }
        });
        //Enlazamos el boton navegar de nuestra interfaz con nuestro codigo
        navegar = findViewById(R.id.navegar);
        //Le damos la funcion de navegar a la interfaz al darle click del segundo empleado con el siguiente metodo:
        navegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo para navegar con el boton de una interfaz a otra.
                Intent tercera = new Intent(Empleado1.this, Empleado2.class);
                startActivity(tercera);
                overridePendingTransition ( R.anim.zoom_forward_in, R.anim.zoom_forward_out );

            }
        });


    }

    //METODO DE LAS VENTAS INT SUMADAS
    public int suma (int a, int b, int c, int d, int e, int f) {
        return a+b+c+d+e+f;
    }

//Guardara todas las ventas
    public void guardar (View view) {
        AdminSQLite admin = new AdminSQLite(this, "registroapp", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String day0 = dia0.getText().toString();
        String day1 = dia1.getText().toString();
        String day2 = dia2.getText().toString();
        String day3 = dia3.getText().toString();
        String day4 = dia4.getText().toString();
        String day5 = dia5.getText().toString();
        String FECHA = fecha.getText().toString();

        //Condicion para declarar que todos los dias y datos deben estar llenos
        if (!day0.isEmpty() && !day1.isEmpty() && !day2.isEmpty() && !day3.isEmpty()
        && !day4.isEmpty() && !day5.isEmpty() && !FECHA.isEmpty()){
            ContentValues agregado = new ContentValues();
            agregado.put("day0",day0 );
            agregado.put("day1",day1 );
            agregado.put("day2",day2 );
            agregado.put("day3",day3 );
            agregado.put("day4",day4 );
            agregado.put("day5",day5 );
            agregado.put("FECHA",FECHA );
            db.insert("registro1", null, agregado);
            db.close();
            dia0.setText("");
            dia1.setText("");
            dia2.setText("");
            dia3.setText("");
            dia4.setText("");
            dia5.setText("");
            fecha.setText("");
            resultado.setText("");
            Toast.makeText(this, "Se ha registrado las ventas de esta semana", Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(this, "No se registro las ventas, Por favor llene todos los datos de la semana", Toast.LENGTH_SHORT).show();

        }
    }
    public void registro (View view) {
        AdminSQLite admin = new AdminSQLite(this, "registroapp", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String FECHA = fecha.getText().toString();

        if (!FECHA.isEmpty()) {
            Cursor cursor
                    = db.rawQuery("select day0, day1, day2, day3, day4, day5 from registro1 where FECHA =" + FECHA, null);

            if (cursor.moveToFirst()) {
                dia0.setText(cursor.getString(0));
                dia1.setText(cursor.getString(1));
                dia2.setText(cursor.getString(2));
                dia3.setText(cursor.getString(3));
                dia4.setText(cursor.getString(4));
                dia5.setText(cursor.getString(5));
                db.close();

            } else {
                Toast.makeText(this, "No existe la fecha registrada", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "No existe la fecha registrada", Toast.LENGTH_SHORT).show();

        }
    }
}