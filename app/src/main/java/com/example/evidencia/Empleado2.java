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

public class Empleado2 extends AppCompatActivity {

    private Button navegar1, igual1;
    private EditText diaL, diaM, diaJ, diaV, diaS, diaD, Fecha1;
    private TextView resultado1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado2);

        // DIAS DE TRABAJO DEL EMPLEADO 2 Y SU RESULTADO
        diaL = findViewById ( R.id.diaL );
        diaM = findViewById ( R.id.diaM );
        diaJ = findViewById ( R.id.diaJ );
        diaV = findViewById ( R.id.diaV );
        diaS = findViewById ( R.id.diaS );
        diaD = findViewById ( R.id.diaD );
        Fecha1 = findViewById(R.id.Fecha1);
        resultado1 = findViewById ( R.id.resultado1 );

        //FUNCIONES PARA EL BOTON DE NAVEGAR A LA INTERFAZ DE MAYOREO.
        navegar1 = findViewById(R.id.navegar1);
        //Le damos la funcion de navegar a la interfaz al darle click del mayoreo con el siguiente metodo:
        navegar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cuarta = new Intent (Empleado2.this, Mayoreo.class);
                startActivity(cuarta);
                overridePendingTransition ( R.anim.fade_in, R.anim.fade_out );
            }
        });
        //FUNCIONES PARA EL BOTON IGUAL QUE DARA EL RESULTADO DE TODAS LAS VENTAS DEL EMPLEADO 2
        igual1 = findViewById (R.id.igual1 );
        igual1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //METODO PARA SUMAR TODOS LOS DIAS DEL EMPLEADO 2
                resultado1.setText ( semana(Integer.parseInt(diaL.getText().toString()),Integer.parseInt(diaM.getText().toString()),
                        Integer.parseInt(diaJ.getText().toString()),Integer.parseInt(diaV.getText().toString()),
                        Integer.parseInt(diaS.getText().toString()), Integer.parseInt(diaD.getText().toString()) )+" "+ Fecha1.getText().toString()  );
            }

            public int semana(int parseInt, int parseInt1, int parseInt2, int parseInt3, int parseInt4, int parseInt5) {
                return parseInt+parseInt1+parseInt2+parseInt3+parseInt4+parseInt5;
            }
        } );

    }

    public void guardado (View view) {
        AdminSQLite admin1 = new AdminSQLite(this, "registroapplicacion", null, 1);
        SQLiteDatabase db = admin1.getWritableDatabase();
        String dayL = diaL.getText().toString();
        String dayM = diaM.getText().toString();
        String dayJ = diaJ.getText().toString();
        String dayV = diaV.getText().toString();
        String dayS = diaS.getText().toString();
        String dayD = diaD.getText().toString();
        String FECHA1 = Fecha1.getText().toString();


        //Condicion para declarar que todos los dias y datos deben estar llenos
        if (!dayL.isEmpty() && !dayM.isEmpty() && !dayJ.isEmpty() && !dayV.isEmpty()
                && !dayS.isEmpty() && !dayD.isEmpty()) {
            ContentValues agregar = new ContentValues();
            agregar.put("dayL", dayL );
            agregar.put("dayM", dayM );
            agregar.put("dayJ", dayJ );
            agregar.put("dayV", dayV );
            agregar.put("dayS", dayS );
            agregar.put("dayD", dayD );
            agregar.put("FECHA1", FECHA1 );
            db.insert("registro2", null, agregar);
            db.close();
            diaL.setText("");
            diaM.setText("");
            diaJ.setText("");
            diaV.setText("");
            diaS.setText("");
            diaD.setText("");
            Fecha1.setText("");
            resultado1.setText("");
            Toast.makeText(getApplicationContext(), "Se ha registrado las ventas de esta semana", Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(getApplicationContext(), "No se registro las ventas, Por favor llene todos los datos de la semana", Toast.LENGTH_SHORT).show();

        }

    }

    public void buscar (View view) {
        AdminSQLite admin1 = new AdminSQLite(this, "registroapplicacion", null, 1);
        SQLiteDatabase db = admin1.getWritableDatabase();

        String FECHA1 = Fecha1.getText().toString();

        if (!FECHA1.isEmpty()){
            Cursor fila = db.rawQuery("SELECT dayL, dayM , dayJ , dayV , dayS , dayD FROM registro2 WHERE FECHA1= "+FECHA1, null);


            if (fila.moveToFirst()){
                diaL.setText(fila.getString(0));
                diaM.setText(fila.getString(1));
                diaJ.setText(fila.getString(2));
                diaV.setText(fila.getString(3));
                diaS.setText(fila.getString(4));
                diaD.setText(fila.getString(5));
                db.close();
            }else{
                Toast.makeText(this, "No existe la fecha registrada", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "No existe la fecha registrada", Toast.LENGTH_SHORT).show();

        }

    }
}