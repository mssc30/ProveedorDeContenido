package net.socorrosamano.actividadesenandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ActividadRegistrarUser extends AppCompatActivity implements Serializable {
    EditText txtNombre;
    EditText txtPass;
    EditText txtEmail;
    EditText txtTelefono;
    Button btnRegistrar;
    Button btnCancelar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Establecer layout que se va a mostrar
        setContentView(R.layout.layout_registrar);

        txtNombre = findViewById(R.id.txtNombreUser);
        txtPass = findViewById(R.id.txtPassUser);
        txtEmail = findViewById(R.id.txtEmailUser);
        txtTelefono = findViewById(R.id.txtTelefonoUser);
        btnRegistrar = findViewById(R.id.btnGuardarUser);
        btnCancelar = findViewById(R.id.btnCancelUser);

        btnRegistrar.setOnClickListener(
               v -> {
                   clsUsuario nuevoUsuario = new clsUsuario(txtNombre.getText().toString(),
                           txtTelefono.getText().toString(),
                           txtPass.getText().toString(), txtEmail.getText().toString());

                   //INSERTAR EL OBJETO clsUsuario AL OBJETO Intent
                   Intent intent = new Intent(this, MainActivity.class);
                   intent.putExtra("Usuario", nuevoUsuario);

                   Toast.makeText(this, "Objeto insertado al intent", Toast.LENGTH_LONG).show();
                }
        );
    }
}
