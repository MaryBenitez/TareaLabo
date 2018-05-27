package com.example.maris.tarealabo.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maris.tarealabo.Adapter.AlumnoAdapter;
import com.example.maris.tarealabo.Clases.Alumno;
import com.example.maris.tarealabo.DB.DBHelper;
import com.example.maris.tarealabo.R;

import java.util.ArrayList;

public class MostrarActivity extends Fragment {

    RecyclerView rv;
    LinearLayoutManager layoutManager;
    ArrayList<Alumno> alumno;
    AlumnoAdapter adapter;

    public MostrarActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modificar,container,false);
        // Inflate the layout for this fragment

        rv=v.findViewById(R.id.rv);
        alumno= DBHelper.mydb.ObtenerAlumno();
        adapter=new AlumnoAdapter(alumno,getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        return v;
    }
}
