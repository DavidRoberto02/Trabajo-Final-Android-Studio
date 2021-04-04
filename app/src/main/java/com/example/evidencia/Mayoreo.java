package com.example.evidencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Mayoreo extends AppCompatActivity  {

    private TextView RESULTADO, PDEP;
    private Button CERRAR;
    private RadioGroup radioGroup2, radioGroup3;
    int pagototal, precio;
    int cantidad, np;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayoreo);
        //AQUI SE DECLARA TODOS LOS BOTONES Y TEXTOS QUE SE LES ASIGNARA UNA FUNCION

        //RADIOGROUPS DONDE SE ENCUENTRAN LOS RADIOBUTTONS*
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);

        //TEXT VIEW
        RESULTADO = findViewById(R.id.RESULTADO);
        PDEP = findViewById(R.id.PDEP);

        //BUTTON
        CERRAR = findViewById(R.id.CERRAR);


        //AQUI SE AGREGARAN LOS METODOS AL DARLE CLICK.
        //Resultado
        RESULTADO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RESULTADO.setText( "Total a pagar:$" + pagototal);

            }

        });
        //BOTON CERRAR SESION
        CERRAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mayoreo.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            }
        });

        //RADIO GROUP 2 PRECIOS AL DAR CLICK
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup2, int i) {

                switch (i) {
                    case R.id.tecate:
                        precio = 235;

                        break;
                    case R.id.Carton:
                        precio = 315;
                    default:
                        break;

                }
                PRECIOPRODUCTO();
            }


        });

        //RADIO GROUP 3
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i1 ) {
                switch (i1) {
                    case R.id.tapamin:
                        precio = 230;
                        cantidad = 5;
                        break;
                    case R.id.tapamax:
                        precio = 225;
                        cantidad = 50;
                    default:
                        break;
                    case R.id.cartonmin:
                        precio = 305;
                        cantidad = 5;
                        break;
                    case R.id.cartonmax:
                        precio = 295;
                        cantidad = 66;
                        break;

                }
                Cantidadcalcular();
                
            }

            // METODO PÀRA CALCULAR LA DISMINUCION DE LAS TAPAS Y/O CARTONES EN MAYOREO
            private void Cantidadcalcular() {
                pagototal = cantidad * precio;
                String envio = "";
                if (np <= 2) {
                    envio = "GRATIS";
                }else{
                    envio = "GRATIS";
                }
                RESULTADO.setText( "total a pagar:" + pagototal + "\n" + "Gracias por su compra." + "\n" +"Los precios de los cartones y tapas son:" + precio  );

            }
        });
    }

    private void PRECIOPRODUCTO() {
        PDEP.setText("Precio:" + precio);
    }

}