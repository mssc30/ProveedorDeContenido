package net.socorrosamano.actividadesenandroid;

import java.io.Serializable;

public class clsUsuario implements Serializable {
    private String nombreUser;
    private String telefono;
    private String contrasenia;
    private String email;

    public clsUsuario(String nombreUser, String telefono, String contrasenia, String email) {
        this.nombreUser = nombreUser;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
