package com.example.juliana.calculadoraporvoz;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //reconocimiento de voz
    private static final int REQUEST_CODE = 1234;
    Button buttonMicrofono;
    ArrayList<String> matchesText;


    //calculadora
    EditText editTextCalculadora;
    private double operando1;
    private double operando2;
    private double resultado;
    private int accion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMicrofono = (Button) findViewById(R.id.button);
        editTextCalculadora = (EditText)findViewById(R.id.editText);


        buttonMicrofono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()){
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    //   intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE , "es-ES");
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        matchesText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); //Get data of data
        String palabra = matchesText.get(0);
        System.out.println("Palabra: " + palabra);
        verificar(palabra);
    }
    public void escribirEnPantalla(String palabra){
        editTextCalculadora.setText(editTextCalculadora.getText() + palabra);
    }

    public void verificar(String palabra){
        try {
            if (palabra.equals("1")) {
                escribirEnPantalla("1");
            }
            else if (palabra.equals("2")) {
                escribirEnPantalla("2");
            }
            else if (palabra.equals("3")) {
                escribirEnPantalla("3");
            }
            else if (palabra.equals("4")) {
                escribirEnPantalla("4");
            }
            else if (palabra.equals("5")) {
                escribirEnPantalla("5");
            }
            else if (palabra.equals("6")) {
                escribirEnPantalla("6");
            }
            else if (palabra.equals("7")) {
                escribirEnPantalla("7");
            }
            if (palabra.equals("8")) {
                escribirEnPantalla("8");
            }
            if (palabra.equals("9")) {
                escribirEnPantalla("9");
            }
            if (palabra.equals("0")) {
                escribirEnPantalla("0");
            }
            if (palabra.equals("más")) {
                undirBoton(1);
            }
            if (palabra.equals("menos")) {
                undirBoton(2);
            }
            if (palabra.equals("/")) {
                undirBoton(4);
            }
            if (palabra.equals("por"  )) {
                undirBoton(3);
            }
            if (palabra.equals("igual")) {
                undirIgual();
            }
            if (palabra.equals("borrar")) {
                borrarTodo();
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public void undirIgual(){
        operando2 = Double.parseDouble(editTextCalculadora.getText().toString());
        mostrarResultado();
    }
    public void undirBoton(int num){
        operando1 = Double.parseDouble(editTextCalculadora.getText().toString());
        editTextCalculadora.setText("");
        accion = num;
    }
    public void borrarTodo(){
        editTextCalculadora.setText("");
    }

    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        if (net!= null && net.isAvailable() && net.isConnected()){
            return true;
        }   else {
            return false;
        }
    }


    public void mostrarResultado() {
        System.out.println("operador1: " + operando1);
        System.out.println("operador2: " + operando2);
        System.out.println("Resultado: " + resultado);
        switch (accion) {
            case 1:
                resultado = operando1 + operando2;
                break;
            case 2:
                resultado = operando1 - operando2;
                break;
            case 3:
                resultado = operando1 * operando2;
                break;
            case 4:
                resultado = operando1 / operando2;
                break;
        }
        editTextCalculadora.setText(Double.toString(resultado));
    }

}
