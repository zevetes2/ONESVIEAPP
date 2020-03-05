package com.example.onesvieapp;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PrincipalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    public boolean A = true, B = false;
    public String b = "";
    final Fragment usuarioF = new UsuarioFragment();
    final Fragment onesvieF = new OnesvieFragment();
    final Fragment alertaF = new AlertaFragment();
    final Fragment loginF = new LoginFragment();
    final Fragment conectadoF = new ConectadoFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active  = usuarioF;
    /*
    @Override
    protected void onRestart() {
        super.onRestart();
        BottomNavigationView bottomPNav = findViewById(R.id.bottom_principal_navigation);
        bottomPNav.setOnNavigationItemSelectedListener(navListener);
        bottomPNav.setSelectedItemId(R.id.nav_usuario);
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        revisar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //fm.beginTransaction().add(R.id.fragment_container_principal, usuarioF, "1").hide(usuarioF).commit();
        //fm.beginTransaction().add(R.id.fragment_container_principal, onesvieF, "2").hide(onesvieF).commit();
        //fm.beginTransaction().add(R.id.fragment_container_principal, alertaF, "3").hide(alertaF).commit();
        //fm.beginTransaction().add(R.id.fragment_container_principal, loginF, "4").hide(loginF).commit();
        //fm.beginTransaction().add(R.id.fragment_container_principal, conectadoF, "5").hide(conectadoF).commit();*/
        MyFirebaseMessagingService a = new MyFirebaseMessagingService();


        BottomNavigationView bottomPNav = findViewById(R.id.bottom_principal_navigation);
        bottomPNav.setOnNavigationItemSelectedListener(navListener);
        revisar();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_principal, new AlertaFragment()).commit();
        }
        else{
            if(A==true){
                //fm.beginTransaction().hide(active).show(loginF).commit();
                //active = loginF;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_principal, new LoginFragment()).commit();
            }
            else if(A==false){
                //fm.beginTransaction().hide(active).show(usuarioF).commit();
                //active = usuarioF;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_principal, new UsuarioFragment()).commit();
            }
        }
        bottomPNav.setSelectedItemId(R.id.nav_inicio);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_usuario:
                            if(A==true){
                    //            fm.beginTransaction().hide(active).show(loginF).commit();
                      //          active = loginF;
                        //        return true;
                                selectedFragment = new LoginFragment();
                            }
                            else{
                          //    fm.beginTransaction().hide(active).show(usuarioF).commit();
                            //  active = usuarioF;
                              //return true;
                                selectedFragment = new UsuarioFragment();
                            }
                            break;
                            case R.id.nav_onesvie:
                                //fm.beginTransaction().hide(active).show(onesvieF).commit();
                                //active = onesvieF;
                                //return true;
                                selectedFragment = new OnesvieFragment();
                            break;
                                case R.id.nav_alerta:
                            if(A==true){
                                //fm.beginTransaction().hide(active).show(conectadoF).commit();
                                //active = conectadoF;
                                //return true;
                                selectedFragment = new ConectadoFragment();
                            }
                            else{
                                //fm.beginTransaction().hide(active).show(alertaF).commit();
                                //active = alertaF;
                                //return true;
                                selectedFragment = new AlertaFragment();
                            }
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_principal,selectedFragment).commit();
                    return true;
                }
            };
    public void revisar(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            A = true;
            //
        }
        else{
            A = false;
        }
    }
    }

