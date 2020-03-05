package com.example.onesvieapp;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private int a = 0;
    private String b = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
        Intent intent = getIntent();
        b = intent.getStringExtra(OnesvieFragment.nav);
        Fragment selected = null;
        if(b.equals("2")){
            a = 1;
            bottomNav.setSelectedItemId(R.id.nav_inicio);
            selected = new InicioFragment();
        }
        else if(b.equals("1")){
            a = 2;
            bottomNav.setSelectedItemId(R.id.nav_nosotros);
            selected  = new NosotrosFragment();
        }
        else if(b.equals("3")){
            a = 3;
            bottomNav.setSelectedItemId(R.id.nav_servicios);
            selected = new ServiciosFragment();
        }
        else if(b.equals("4")){
            a = 4;
            bottomNav.setSelectedItemId(R.id.nav_contactos);
            selected = new ContactosFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected).commit();
        //else if(b=="1"){

        //}





    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.nav_inicio:
                    selectedFragment = new InicioFragment();
                    a = 1;
                    break;
                case R.id.nav_nosotros:
                    selectedFragment = new NosotrosFragment();
                    a = 2;
                    break;
                case R.id.nav_servicios:
                    selectedFragment = new ServiciosFragment();
                    a = 3;
                    break;
                case R.id.nav_contactos:
                    selectedFragment = new ContactosFragment();
                    a = 4;
                    break;
                case R.id.nav_atras:
                    if(a == 1){
                        selectedFragment = new InicioFragment();
                    }
                    else if(a == 2){
                        selectedFragment = new NosotrosFragment();
                    }
                    else if(a == 3){
                        selectedFragment = new ServiciosFragment();
                    }
                    else if(a == 4){
                        selectedFragment = new ContactosFragment();
                    }
                    onBackPressed();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}
