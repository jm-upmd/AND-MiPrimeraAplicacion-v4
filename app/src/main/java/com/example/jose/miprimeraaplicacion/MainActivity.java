package com.example.jose.miprimeraaplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mCalculo;
    Button mBotonDuplicar;
    Button mBotonDividir;
    Button mbotonLimpiar;
    EditText mNumeroEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // Creación de referencias a los views

        mCalculo = findViewById(R.id.txvValorCalculo);
        mBotonDuplicar = findViewById(R.id.btMultiplica);
        mBotonDividir = findViewById(R.id.btDivide);
        mbotonLimpiar = findViewById(R.id.btLimpiar);
        mNumeroEntrada = findViewById(R.id.numeroEntrada);


        // Implementación de listerners para recoger eventos sobre los
        // views anteriores

        mBotonDuplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiplicar();
            }
        });
        mBotonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dividir();
            }
        });
        mbotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetear();
            }
        });
    }

    private void dividir() {
        int valor; double numeroEntrada;
        try {
            if(!mNumeroEntrada.getText().toString().equals(""))
                numeroEntrada = Integer.parseInt(mNumeroEntrada.getText().toString());
            else return;

            valor = Integer.parseInt(mCalculo.getText().toString());

            // Si el contador tiene valor 1 o si el cociente es 0 o mayor que el contador,
            // entonces no hacemos nada

            if (valor == 1 ) return;

            if(numeroEntrada == 0) {
                Toast.makeText(this,"Valor introducido no puede ser 0",Toast.LENGTH_SHORT).show();
                return;
            }
            if(numeroEntrada > valor) {
                Toast.makeText(this,"Valor introducido no puede ser mayor que contador", Toast.LENGTH_SHORT).show();
                return;
            }

            // Actualiza contador con la división
            mCalculo.setText(String.valueOf(valor/=numeroEntrada));

            // Puede que el boton multiplicar estuviera deshabilitado. Lo habilitamos.

            if(!mBotonDuplicar.isEnabled())
                mBotonDuplicar.setEnabled(true);

            // Deshabilita boton dividir si su nuevo valor es 1
            if(valor == 1)
                mBotonDividir.setEnabled(false);

        } catch (NumberFormatException e) {
            Log.e("MiAplicacion", "Error al convertir el valor en un entero", e);
        }
    }

    private void multiplicar() {
        int valor; double numeroEntrada;
        try {
            if(!mNumeroEntrada.getText().toString().equals(""))
                numeroEntrada = Double.parseDouble(mNumeroEntrada.getText().toString());
            else return;

            valor = Integer.parseInt(mCalculo.getText().toString());

            // Si valor introducido es cero no hace nada e informa
            if(numeroEntrada == 0) {
                Toast.makeText(this,"Valor introducido no puede ser 0",Toast.LENGTH_SHORT).show();
                return;
            }

            // Si el numero introducido o su producto por el contador sobrepasa
            // rango de un int no hace nada y muestra mensaje
            if( (valor * numeroEntrada   > Integer.MAX_VALUE)
                    || (numeroEntrada > Integer.MAX_VALUE)) {
                Toast.makeText(this,"Valor introducido muy grande",Toast.LENGTH_LONG).show();
                return;
            }

            // Actualiza contador
            mCalculo.setText(String.valueOf(valor*=numeroEntrada));

            // Si el contador ya tiene el máximo valor que puede representar deshabilita boton
            // multiplicar

            if(valor == Integer.MAX_VALUE)
                mBotonDuplicar.setEnabled(false);

            // Habilita boton dividir si necesario
            if(!mBotonDividir.isEnabled() && valor > 1)
                mBotonDividir.setEnabled(true);



        } catch (NumberFormatException e) {
            Log.e("MiAplicacion", "Error al convertir el valor en un entero", e);
        }
    }

    private void resetear() {
        mCalculo.setText("1");
        mNumeroEntrada.setText("");
        mBotonDuplicar.setEnabled(true);
        mBotonDividir.setEnabled(false);
    }
}
