package com.example.maris.tarealabo.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.maris.tarealabo.Clases.Alumno;
import com.example.maris.tarealabo.DB.DBHelper;
import com.example.maris.tarealabo.R;


public class AgregarActivity extends Fragment {

    EditText nombre;
    EditText carnet;
    Button agregar;

    public AgregarActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DBHelper.getInstance(getContext());
        View v = inflater.inflate(R.layout.fragment_agregar,container,false);

        nombre=v.findViewById(R.id.txtnombre);
        carnet=v.findViewById(R.id.txtid);
        agregar=v.findViewById(R.id.btnregistrar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.mydb.AgregarAlumno(new Alumno(nombre.getText().toString(),carnet.getText().toString(),""));
                Vaciar();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void Vaciar() {
        carnet.setText("");
        nombre.setText("");
    }
}
