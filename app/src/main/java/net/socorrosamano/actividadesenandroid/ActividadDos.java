package net.socorrosamano.actividadesenandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadDos extends AppCompatActivity{
    //Relacionar elementos del XML con java
    Button btnOk, btnCancel, btnRegistrar;
    EditText txtUser, txtPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Establecer layout que se va a mostrar
        setContentView(R.layout.layout_second_activity);

        //buscar elementos del layout con su ID
        //regresa un view= boton, edit text
        //puede o no especificarse el cast
        txtPass=(EditText) findViewById(R.id.txtPass);
        txtUser= (EditText) findViewById(R.id.txtUser);
        btnCancel=findViewById(R.id.btnCancel);
        btnOk=findViewById(R.id.btnOk);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        //implementar al boton el metodo click
        //btnOk.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //         Log.d("click", txtUser.getText().toString());

                //tipo mensaje
        //        Toast.makeText(getBaseContext(), txtUser.getText().toString(),
        //                Toast.LENGTH_LONG).show();
        //    }
        //});

        //con programacion funcional
        btnOk.setOnClickListener(v -> {
            Log.d("click", txtUser.getText().toString());

            //Toast.makeText(getBaseContext(),
              //  txtUser.getText().toString(), Toast.LENGTH_LONG).show();

            //objeto para poder iniciar y utilizar los componentes de una app
            //permite pasar mensajes a la invocacion de otros componentes
            Intent intent = new Intent(ActividadDos.this,
                    MainActivity.class);

            //mandar datos a otra actividad con metodo put
            intent.putExtra("usuario", txtUser.getText().toString());
            intent.putExtra("id", 789);

            //arrancar la actividad
            startActivity(intent);
        });

        btnRegistrar.setOnClickListener(v->{
            Intent intent = new Intent(ActividadDos.this,
                    ActividadRegistrarUser.class);
            startActivity(intent);
        });

        btnCancel.setOnClickListener(v->{finish();});

        //monitorear con Logcat
        Log.d("ciclovida", "paso por onCreate");
         //w -> warning
        //v -> verbose
        //i -> information
        //e -> error
        //d -> debug

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ciclovida", "paso por onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ciclovida", "paso por onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ciclovida", "paso por onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ciclovida", "paso por onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ciclovida", "paso por onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ciclovida", "paso por onDestroy");
    }
}

