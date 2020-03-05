package com.example.onesvieapp;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class OnesvieFragment extends Fragment {
    private RelativeLayout nav_inicio, nav_nosotros, nav_servicios, nav_contactos;
    public final static String nav = "onesvie";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_onesvie_fragment,container,false);
        nav_inicio = (RelativeLayout) view.findViewById(R.id.nav_inicio);
        nav_contactos = (RelativeLayout) view.findViewById(R.id.nav_contactos);
        nav_nosotros = (RelativeLayout) view.findViewById(R.id.nav_nosotros);
        nav_servicios = (RelativeLayout) view.findViewById(R.id.nav_servicios);
         nav_inicio.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), MainActivity.class);
                 intent.putExtra(nav,"2");
                 startActivity(intent);
             }
         });
         nav_servicios.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), MainActivity.class);
                 intent.putExtra(nav, "3");
                 startActivity(intent);
             }
         });
         nav_nosotros.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), MainActivity.class);
                 intent.putExtra(nav, "1");
                 startActivity(intent);
             }
         });
         nav_contactos.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), MainActivity.class);
                 intent.putExtra(nav, "4");
                startActivity(intent);
             }
         });
        return view;
    }
}
