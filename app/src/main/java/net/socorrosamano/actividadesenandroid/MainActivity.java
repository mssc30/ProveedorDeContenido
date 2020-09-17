package net.socorrosamano.actividadesenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView lblBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperar datos enviados con getIntent
        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        lblBienvenida = findViewById(R.id.lblBienvenida);

        //Toast.makeText(this, "Bienvenido "+usuario,
          //      Toast.LENGTH_LONG).show();

        lblBienvenida.setText(lblBienvenida.getText()+" "+ usuario);
        Toast.makeText(this, "Acceso concedido",
                Toast.LENGTH_LONG).show();

    }
}