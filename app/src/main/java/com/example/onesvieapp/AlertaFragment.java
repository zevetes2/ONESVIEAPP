package com.example.onesvieapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Maps;
import com.google.common.io.LineReader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlertaFragment extends Fragment {

    private TextView descripcion, longitud, latitud, magnitud, fechahora, profundidad;
    private Button btnActualizarAlerta;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore =  FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = mFirestore.collection("Alertas");
    private DocumentReference noteRef = mFirestore.document("Alertas/Alerta1");
    private String user;
    private boolean A = true,B=true;
    private int cont = 0;
    private int i=0;
    private LinearLayout alertas, alertaprueba;
    private ProgressBar progressBarA;

    @Override
    public void onResume() {
        super.onResume();
        if(B==false){
            refrescar();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_alerta_fragment, container, false);
        btnActualizarAlerta = (Button) view.findViewById(R.id.btn_ActualizarAlerta);
            progressBarA = (ProgressBar) view.findViewById(R.id.progressBarA);
            mAuth = FirebaseAuth.getInstance();
            user = mAuth.getCurrentUser().getUid();
            btnActualizarAlerta.setBackgroundColor(Color.RED);
            btnActualizarAlerta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refrescar();
                }
            });

        return view;
    }
    public void refrescar(){
        if(A==false){
            alertas.removeAllViews();

        }
        progressBarA.setVisibility(View.VISIBLE);
        btnActualizarAlerta.setEnabled(false);
        notebookRef.orderBy("fechahora", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                btnActualizarAlerta.setText("Actualizar Alertas");
                btnActualizarAlerta.setBackgroundResource(R.color.colorAccent);
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    NoteAlerta note = documentSnapshot.toObject(NoteAlerta.class);
                    alertas = (LinearLayout) getView().findViewById(R.id.alertas);
                    LayoutInflater layoutInflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View alertasView = layoutInflater2.inflate(R.layout.cuadroalerta, null);
                    alertaprueba = (LinearLayout) alertasView.findViewById(R.id.alertaprueba);
                    descripcion = (TextView) alertasView.findViewById(R.id.descripcion);
                    latitud = (TextView) alertasView.findViewById(R.id.latitud);
                    longitud = (TextView) alertasView.findViewById(R.id.longitud);
                    magnitud = (TextView) alertasView.findViewById(R.id.magnitud);
                    fechahora = (TextView) alertasView.findViewById(R.id.fecha_hora);
                    profundidad = (TextView) alertasView.findViewById(R.id.profundidad);

                    String color ="";
                    magnitud.setTextColor(Color.parseColor(colorMagnitud(color, note.getMagnitud())));
                    descripcion.setText(note.getDescripcion());
                    latitud.setText(String.valueOf(note.getLatitud()));
                    longitud.setText(String.valueOf(note.getLongitud()));
                    magnitud.setText(note.getMagnitud());
                    Date fech = note.getFechahora();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    try{
                        fechahora.setText(dateFormat.format(fech));
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    profundidad.setText(note.getProfundidad());
                    alertas.addView(alertasView);
                    A = false;
                    btnActualizarAlerta.setEnabled(true);
                    progressBarA.setVisibility(View.GONE);
                    alertasView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            B = false;
                            String id = "";
                            id = ((TextView) alertasView.findViewById(R.id.id_id)).getText().toString();
                            String descripcion  = ((TextView) alertasView.findViewById(R.id.descripcion)).getText().toString();
                            String magnitud = ((TextView) alertasView.findViewById(R.id.magnitud)).getText().toString();
                            String latitud = ((TextView) alertasView.findViewById(R.id.latitud)).getText().toString();
                            String longitud = ((TextView) alertasView.findViewById(R.id.longitud)).getText().toString();
                            String profundidad = ((TextView) alertasView.findViewById(R.id.profundidad)).getText().toString();
                            String fecha_hora = ((TextView) alertasView.findViewById(R.id.fecha_hora)).getText().toString();

                            Intent intent = new Intent(getContext(), MapsActivity.class);
                            intent.putExtra("pId",id);
                            intent.putExtra("pDescripcion",descripcion);
                            intent.putExtra("pMagnitud",magnitud);
                            intent.putExtra("pLatitud",latitud);
                            intent.putExtra("pLongitud",longitud);
                            intent.putExtra("pProfundidad", profundidad);
                            intent.putExtra("pFecha_hora",fecha_hora);
                            startActivity(intent);
                        }
                    });
                }

            }

        });
    }
    public String colorMagnitud(String color, String magnitud){
        double mag = Double.parseDouble(magnitud);
        if(mag >= 1.0 && mag <= 1.9){
            color = "#006600";
        }
        else if(mag >= 2.0 && mag <= 2.9){
            color = "#838204";
        }
        else if(mag >= 3.0 && mag <= 3.9){
            color = "#ffff07";
        }
        else if(mag >= 4.0 && mag <= 4.9){
            color = "#f8ca03";
        }
        else if(mag >= 5.0 && mag <= 5.9){
            color = "#ff9c03";
        }
        else if(mag >= 6.0 && mag <= 6.9){
            color = "#fe0000";
        }
        else if(mag >= 7.0){
            color = "#ff00fe";
        }
        return color;
    }
}
/*descripcion.setText(documentSnapshot.getString("descripcion"));
                        magnitud.setText(documentSnapshot.getString("magnitud"));
                        coordenadas.setText(documentSnapshot.getGeoPoint("coordenadas").toString());
                        fechahora.setText("Fecha: " + documentSnapshot.getDate("fecha-hora").toString());
                        profundidad.setText("Profundidad: " + documentSnapshot.getString("profundidad"));*/