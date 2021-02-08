package net.socorrosamano.actividadesenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.socorrosamano.actividadesenandroid.data.Usuario;
import net.socorrosamano.actividadesenandroid.data.daoUsuarios;
import net.socorrosamano.actividadesenandroid.data.database;

public class MainActivity extends AppCompatActivity {
    TextView lblBienvenida;
    daoUsuarios daoUsuarios;
    database db;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoUsuarios = new daoUsuarios(this);
        //recuperar datos enviados con getIntent
        Intent intent = getIntent();
        Usuario usuarioAutenticado = (Usuario) intent.getSerializableExtra("usuario");
        lblBienvenida = findViewById(R.id.lblBienvenida);

        //mostrar bienvenida al user
        lblBienvenida.setText(lblBienvenida.getText() + " " + usuarioAutenticado.getNombre());
        Toast.makeText(this, "Acceso concedido",
                Toast.LENGTH_LONG).show();

        //mostrar un cursor en un xml
        //una clase que ayuda a construir un adaptador
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this, //contexto
                android.R.layout.simple_list_item_2, //layout con una lista de 2 item
                daoUsuarios.getAllCursor(), //cursor que tiene los datos
                new String[]{db.COLUMNS_TABLE_USUARIOS[0], db.COLUMNS_TABLE_USUARIOS[2]}, //columnas que se quieren recuperar del cursor
                new int[]{android.R.id.text1, android.R.id.text2}, //ids de controles a donde se van a poner los elementos
                SimpleCursorAdapter.NO_SELECTION //comportamiento para los controles¿?
        );

        spinner = findViewById(R.id.spinnerP);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  //el view representa el item
                                                  String cad = ((TextView) view.findViewById(android.R.id.text1)).getText().toString();
                                                  Toast.makeText(getApplicationContext(), cad, Toast.LENGTH_LONG).show();
                                              }


                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {

                                              }
                                          }
        );
        spinner.setAdapter(simpleCursorAdapter);
    }
}