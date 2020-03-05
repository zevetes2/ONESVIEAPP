package com.example.onesvieapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class UsuarioFragment extends Fragment {
    private Button mLogoutBtn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private TextView nombre,correo;
    private String user;
    private ProgressBar progressBar;
    private int cont = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.activity_usuario_fragment, container, false);
        nombre = (TextView) view.findViewById(R.id.nombre);
        correo = (TextView) view.findViewById(R.id.correo);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser().getUid();
        mFirestore.collection("Users").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                String user_name = documentSnapshot.getString("name");
                String user_email = documentSnapshot.getString("email");
                nombre.setText(user_name);
                correo.setText(user_email);
            }
        });
        mLogoutBtn = (Button) view.findViewById(R.id.logout);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent loginIntent = new Intent(container.getContext(), PrincipalActivity.class);
                startActivity(loginIntent);
                getActivity().onBackPressed();
            }
        });
        return view;
    }


}
