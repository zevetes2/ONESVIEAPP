package com.example.onesvieapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginFragment extends Fragment {

    private TextView linkregistro,linkolvidar;
    private EditText edit_correo, edit_contra;
    private Button btn_aceptar;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseFirestore mFirestore =  FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = mFirestore.collection("Users");
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_login_fragment, container, false);
        linkregistro = (TextView) view.findViewById(R.id.linkregistrarse);
        edit_correo = (EditText) view.findViewById(R.id.edit_correo);
        edit_contra = (EditText) view.findViewById(R.id.edit_contra);
        btn_aceptar = (Button) view.findViewById(R.id.btn_aceptar);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        linkolvidar = (TextView) view.findViewById(R.id.olvidar);
        mAuth = FirebaseAuth.getInstance();
        linkolvidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert  = new AlertDialog.Builder(getContext());
                final EditText edittext = new EditText(getContext());
                alert.setMessage("Escriba el correo electronico: ");
                alert.setTitle("Recuperar contraseña");
                alert.setView(edittext);
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        final String a = edittext.getText().toString();
                        if(!TextUtils.isEmpty(a)){
                            notebookRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    boolean f = false;
                                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                        if(documentSnapshot.getString("email").equals(edittext.getText().toString())){
                                            f = true;
                                        }
                                    }
                                    if(f==false){
                                        Toast.makeText(getContext(), "Este correo no está registrado.", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        mAuth.sendPasswordResetEmail(a);
                                        Toast.makeText(getContext(), "Solicitud de recuperación enviada...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            //
                            //Toast.makeText(getContext(), "Solicitud de recuperación enviada..."+a, Toast.LENGTH_SHORT).show();
                        }
                        else{

                        }

                    }
                });


                alert.show();
            }
        });
        linkregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistrarFragment.class);
                startActivity(intent);
            }
        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email =  edit_correo.getText().toString();
                String contra = edit_contra.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(contra)){
                    mAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(container.getContext(), PrincipalActivity.class);
                                startActivity(intent);
                                Toast.makeText(container.getContext(),"Bienvenido" , Toast.LENGTH_SHORT).show();
                                getActivity().onBackPressed();
                            }
                            else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(container.getContext(),"Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        return view;
    }

}
