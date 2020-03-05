package com.example.onesvieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RegistrarFragment extends AppCompatActivity {

    private EditText editcorreo, editnombre, editcontrasena, editconfirmar;
    private Button btnregistrar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private ProgressBar mProgressBar;
    private int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_fragment);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        editnombre = (EditText) findViewById(R.id.editnombre);
        editcorreo = (EditText) findViewById(R.id.editcorreo);
        editcontrasena = (EditText) findViewById(R.id.editcontrasena);
        editconfirmar = (EditText) findViewById(R.id.editconfirmar);
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                final String nombre = editnombre.getText().toString();
                final String correo = editcorreo.getText().toString();
                String contrasena = editcontrasena.getText().toString();
                String confirmarcontrasena = editconfirmar.getText().toString();
                if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasena) && !TextUtils.isEmpty(confirmarcontrasena) && contrasena.equals(confirmarcontrasena)){
                    mAuth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                            if(task.isSuccessful()){
                                final String user_id = mAuth.getCurrentUser().getUid();
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("name", nombre);
                                userMap.put("email",correo);
                               mFirestore.collection("Users").document(user_id).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {
                                       mProgressBar.setVisibility(View.INVISIBLE);
                                       Toast.makeText(RegistrarFragment.this, correo+" se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                       onBackPressed();
                                   }
                               });

                            }
                            else{
                                Toast.makeText(RegistrarFragment.this,"Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
                else{
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(RegistrarFragment.this,"Error: Llene los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}
