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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModificarActivity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModificarActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarActivity extends Fragment {

    Button buscar;
    Button modificar;
    Button eliminar;
    Button limpiar;

    EditText carnet;
    EditText nombre;
    EditText nota;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ModificarActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificarActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarActivity newInstance(String param1, String param2) {
        ModificarActivity fragment = new ModificarActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modificar,container,false);
        DBHelper.getInstance(getContext());
        // Inflate the layout for this fragment
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
