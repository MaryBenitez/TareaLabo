package com.example.maris.tarealabo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maris.tarealabo.Clases.Alumno;
import com.example.maris.tarealabo.R;

import java.util.ArrayList;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolder> {

    private View v;
    private static Context context;
    ArrayList<Alumno> alumnos;

    public AlumnoAdapter(ArrayList<Alumno> alumnos, Context context) {
        this.alumnos = alumnos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlumnoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        v = inflater.inflate(R.layout.informacion_alumno,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoAdapter.ViewHolder holder, int position) {
        holder.carnet.setText(alumnos.get(position).getCarnet());
        holder.nota.setText(alumnos.get(position).getNota()+"");
        holder.nombre.setText(alumnos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

     public static class ViewHolder extends RecyclerView.ViewHolder{

         EditText nombre;
         EditText carnet;
         EditText nota;

         public ViewHolder(final View itemView) {
             super(itemView);
             carnet = itemView.findViewById(R.id.txtidI);
             nota = itemView.findViewById(R.id.txtnotaI);
             nombre = itemView.findViewById(R.id.txtnombreI);
         }
     }
}

