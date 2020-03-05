package com.example.onesvieapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConectadoFragment extends Fragment {
    private TextView ingresar, registrar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_conectado_fragment, container, false);
        ingresar = (TextView) view.findViewById(R.id.linkingresar);
        registrar = (TextView) view.findViewById(R.id.linkregistrarse);
        final BottomNavigationView bottomNav = view.findViewById(R.id.bottom_principal_navigation);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bottomNav.setSelectedItemId(R.id.nav_usuario);
                Intent intent = new Intent(getContext(), PrincipalActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistrarFragment.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
