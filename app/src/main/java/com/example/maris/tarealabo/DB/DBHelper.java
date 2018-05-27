package com.example.maris.tarealabo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.maris.tarealabo.Clases.Alumno;

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

}
