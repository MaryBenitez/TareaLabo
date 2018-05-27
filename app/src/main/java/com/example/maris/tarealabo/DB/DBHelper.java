package com.example.maris.tarealabo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.maris.tarealabo.Clases.Alumno;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "alumnos";
    public static final String TABLA_ALUMNO = "Alumno";
    public static final String CAMPO_ID = "Carnet";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_NOTA = "Nota";
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA_ALUMNO +"("+CAMPO_ID+"TEXT,"+CAMPO_NOMBRE+"TEXT,"+CAMPO_NOTA+"TEXT)";

    public static DBHelper mydb = null;
    private Context context;
    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context,DB_NOMBRE,null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    public static DBHelper getInstance(Context context){
        if(mydb == null){
            mydb = new DBHelper(context.getApplicationContext());
        }
        return mydb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOMBRE);
        onCreate(db);
    }

    public boolean AgregarAlumno(Alumno alumno){
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID,alumno.getCarnet());
        values.put(CAMPO_NOMBRE,alumno.getNombre());
        values.put(CAMPO_NOTA,alumno.getNota());
        db.insert(TABLA_ALUMNO,null,values);
        Toast.makeText(context,"Alumno agregado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }

    public ArrayList<Alumno> ObtenerAlumno(){

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_ALUMNO,null);
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        while (cursor.moveToNext()){
            alumnos.add(new Alumno(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2)));
        }

        return alumnos;
    }

    public Alumno BuscarAlumno(String carnet){

        Alumno alumno;
        String [] parametros = {carnet};
        String [] campos = {CAMPO_NOMBRE,CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_ALUMNO,campos,CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            alumno=new Alumno(cursor.getString(0),carnet,cursor.getString(1));
        }
        catch (Exception e){
            Toast.makeText(context,"Alumno no encontrado", Toast.LENGTH_SHORT).show();
            alumno=null;
        }
        return alumno;
    }

    public boolean ModificarAlumno(Alumno alumno){

        String [] parametros = {alumno.getCarnet()};
        String[] campos = {CAMPO_NOMBRE,CAMPO_NOTA};

        ContentValues values = new ContentValues();
        values.put(CAMPO_NOMBRE,alumno.getNombre());
        values.put(CAMPO_NOTA,alumno.getNota());
        db.update(TABLA_ALUMNO,values,CAMPO_ID+"=?",parametros);
        Toast.makeText(context,"Alumno Modificado",Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean BorrarAlumno(String carne){
        String[] parametros = {carne};
        db.delete(TABLA_ALUMNO,CAMPO_ID+"=?",parametros);
        Toast.makeText(context,"Alumno eliminado",Toast.LENGTH_SHORT).show();
        return true;
    }

}
