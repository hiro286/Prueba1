package com.mycompany.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SaludoActivity extends AppCompatActivity {

    private TextView txtSaludo;
    private Button btnAcptHi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        //Localizar los controles
        txtSaludo = (TextView)findViewById(R.id.TxtSaludo);
        btnAcptHi = (Button)findViewById(R.id.BtnAcptSaludo);

        //Recuperamos la información pasada en el intent
        //Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        //txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));
        txtSaludo.setText("Hola que hace? \n Programando o que hace?");

        btnAcptHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SaludoActivity.this, PruebaActivity.class);
                startActivity(i);
            }
        });
    }
}
