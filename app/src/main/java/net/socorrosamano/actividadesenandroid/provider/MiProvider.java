package net.socorrosamano.actividadesenandroid.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.socorrosamano.actividadesenandroid.data.daoUsuarios;
import net.socorrosamano.actividadesenandroid.data.database;

public class MiProvider extends ContentProvider {

    daoUsuarios dao;

    //CREA UN OBJETO URI MATCHER TIENE CAPACIDAD DE IDENTIFICAR CON CUAL DE LOS PATRONES COINCIDE LA URI
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //AUTORITY ES EL NOMBRE DE LA APLICACION

        //uriMatcher.addURI CONTIENE LOS PATRONES QUE EL PROVIDER DEBERIA RECONOCER

        //"content://net.socorrosamano.actividadesenandroid.provider/usuarios/"
        // SIRVE PARA ACCEDER A TODOS LOS USUARIOS, PARA UN INSERT
        uriMatcher.addURI("net.socorrosamano.actividadesenandroid.provider", "usuarios", 1);

        //ESTOS SE PUEDEN USAR PARA EDITAR O ELIMINAR, SON COMO IDENTIFICADORES

        //EL # SIGNIFICA QUE PUEDE RECIBIR UN NUMERO
        //"content://net.socorrosamano.actividadesenandroid.provider/usuarios/3"
        uriMatcher.addURI("net.socorrosamano.actividadesenandroid.provider", "usuarios/#", 2);

        //EL * SIGNIFICA QUE PUEDE RECIBIR UNA PALABRA
        //"content://net.socorrosamano.actividadesenandroid.provider/usuarios/koko"
        uriMatcher.addURI("net.socorrosamano.actividadesenandroid.provider", "usuarios/*", 3);
    }

    @Override
    public boolean onCreate() {
        dao = new daoUsuarios(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] strings, //COLUMNAS A DEVOLVER
                        @Nullable String s,
                        @Nullable String[] strings1,
                        @Nullable String s1) {

        Cursor result = null;
        SQLiteDatabase sqLiteDatabase = new database(getContext()).getWritableDatabase();

        //MATCHEA LA URI RECIBIDA CON LOS QUE SE AÑADIERON AL OBJETO URIMATCHER
        //SI LO ENCUENTRA DEVUELVE EL CODIGO DEL URI
        switch (uriMatcher.match(uri)) {
            case 1:
                result = dao.getAllCursor();
                /*sqLiteDatabase.query("Usuarios", strings, s, strings1, null, null, null);*/
                break;

            case 2:
                String id = uri.getLastPathSegment();
                result = dao.getOneByIDCursor(Long.parseLong(id));
                break;
        }

        return result;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String tipo = "";

        switch (uriMatcher.match(uri)){
            case 1:
                tipo = "vnd.android.cursor.dir/vnd.net.socorrosamano.actividadesenandroid.provider.usuarios";
                break;

            case 2:
                tipo = "vnd.android.cursor.item/vnd.net.socorrosamano.actividadesenandroid.provider.usuarios";
                break;

            case 3:
                tipo = "vnd.android.cursor.dir/vnd.net.socorrosamano.actividadesenandroid.provider.usuarios";
                break;
        }

        return tipo;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri result = null;

        switch (uriMatcher.match(uri)){
            case 1:
                long idnewrow = dao.insert(contentValues);
                result = Uri.withAppendedPath(uri, idnewrow+"");
                break;
        }

        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int result=0;
        switch (uriMatcher.match(uri)){
            case 2:
                result=(dao.delete(Long.parseLong(uri.getLastPathSegment())));
            break;
        }

        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int result = 0;
        switch (uriMatcher.match(uri)){
            case 2:
                result = dao.update(contentValues, Long.parseLong(uri.getLastPathSegment()));
                break;
        }

        return result;
    }
}