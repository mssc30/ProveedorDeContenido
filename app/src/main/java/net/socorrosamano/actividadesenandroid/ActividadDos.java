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

import net.socorrosamano.actividadesenandroid.data.Usuario;
import net.socorrosamano.actividadesenandroid.data.daoUsuarios;

public class ActividadDos extends AppCompatActivity {
    private static final int ACTIVITY_REGISTRO = 1000;
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
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtUser = (EditText) findViewById(R.id.txtUser);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
        //btnRegistrar = findViewById(R.id.btnGuardarUser);

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
            //Log.d("click", txtUser.getText().toString());


            //Ver si el usuario está en la base de datos
            daoUsuarios dao = new daoUsuarios(getApplicationContext());

            Usuario usuarioAutenticado = dao.autenticar(new Usuario(txtUser.getText().toString(), txtPass.getText().toString()));

            //si es diferente de 0 el usuario se encontro
            if (usuarioAutenticado.getID() != 0) {

                //objeto para poder iniciar y utilizar los componentes de una app
                //permite pasar mensajes a la invocacion de otros componentes
                Intent intent = new Intent(ActividadDos.this, MainActivity.class);

                //mandar el objeto autenticado a la actividad
                intent.putExtra("usuario", usuarioAutenticado);

                //arrancar la actividad
                startActivity(intent);
            } else {
                Toast.makeText(getBaseContext(),
                        txtUser.getText().toString() + " SIN ACCESO",
                        Toast.LENGTH_LONG).show();
            }
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });


        //monitorear con Logcat
        Log.d("ciclovida", "paso por onCreate");
        //w -> warning
        //v -> verbose
        //i -> information
        //e -> error
        //d -> debug

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ACTIVITY_REGISTRO:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    Usuario usnu = (Usuario) data.getSerializableExtra("Usuario");
                    txtUser.setText(usnu.getEmail());
                } else {
                    Toast.makeText(this, "Usuario NO Registrado", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2000:
                break;
        }
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

    public void btnRegistrar_click(View view) {
        Intent intent = new Intent(getBaseContext(), ActividadRegistrarUser.class);

        startActivityForResult(intent, ACTIVITY_REGISTRO);

    }
}

