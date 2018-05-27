package com.example.maris.tarealabo.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maris.tarealabo.Clases.Alumno;
import com.example.maris.tarealabo.DB.DBHelper;
import com.example.maris.tarealabo.R;

public class ModificarActivity extends Fragment {

    Button buscar;
    Button modificar;
    Button eliminar;
    Button limpiar;

    EditText carnet;
    EditText nombre;
    EditText nota;

    public ModificarActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modificar,container,false);
        DBHelper.getInstance(getContext());
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carnet = view.findViewById(R.id.txtidM);
        nombre = view.findViewById(R.id.txtnombreM);
        nota = view.findViewById(R.id.txtnotaM);
        buscar = view.findViewById(R.id.btnmodificar);
        modificar= view.findViewById(R.id.btnmodificar);
        eliminar = view.findViewById(R.id.btneliminar);
        limpiar = view.findViewById(R.id.btnlimpiar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno alumno = DBHelper.mydb.BuscarAlumno(carnet.getText().toString());

                if (alumno == null){
                    Toast.makeText(getContext(), "Alumno no encontrado", Toast.LENGTH_SHORT).show();

                }
                else{
                    nota.setText(alumno.getNota());
                    nombre.setText(alumno.getNombre());
                }
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno alumno = DBHelper.mydb.BuscarAlumno(carnet.getText().toString());
                alumno.setNota(nota.getText().toString());
                DBHelper.mydb.ModificarAlumno(alumno);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.mydb.BorrarAlumno(carnet.getText().toString());
                Vaciar();
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vaciar();
            }
        });
    }

    private void Vaciar() {
        carnet.setText("");
        nombre.setText("");
        nota.setText("");
    }
}
