package com.example.onesvieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    String pDescripcion, pMagnitud, pId, pFecha_hora, pProfundidad, pLatitud, pLongitud;
    private TextView descripcion, magnitud, latitud, longitud, fechahora, profundidad;
    Date date;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        descripcion = (TextView) findViewById(R.id.Mag_y_Desc);
        magnitud = (TextView) findViewById(R.id.magnitud);
        latitud = (TextView) findViewById(R.id.latitud);
        longitud = (TextView) findViewById(R.id.longitud);
        fechahora = (TextView) findViewById(R.id.fechahora);
        profundidad = (TextView) findViewById(R.id.profundidad);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pId = bundle.getString("pId");
            pDescripcion = bundle.getString("pDescripcion");
            pMagnitud = bundle.getString("pMagnitud");
            pLatitud = bundle.getString("pLatitud");
            pLongitud = bundle.getString("pLongitud");
            pFecha_hora = bundle.getString("pFecha_hora");
            pProfundidad = bundle.getString("pProfundidad");
            magnitud.setText(pMagnitud);
            descripcion.setText(pDescripcion);
            longitud.setText(String.valueOf(pLongitud));
            latitud.setText(String.valueOf(pLatitud));
            fechahora.setText(pFecha_hora);
            profundidad.setText(pProfundidad);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        LatLng coordenadas = new LatLng(Double.parseDouble(pLatitud),Double.parseDouble(pLongitud));
        if(Double.parseDouble(pMagnitud) >= 1.0 &&  Double.parseDouble(pMagnitud) <= 2.9){
            map.addMarker(new MarkerOptions().position(coordenadas).title(pDescripcion).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
        else if(Double.parseDouble(pMagnitud) >= 3.0 &&  Double.parseDouble(pMagnitud) <= 3.9){
            map.addMarker(new MarkerOptions().position(coordenadas).title(pDescripcion).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        }
        else if(Double.parseDouble(pMagnitud) >= 4.0 &&  Double.parseDouble(pMagnitud) <= 4.9){
            map.addMarker(new MarkerOptions().position(coordenadas).title(pDescripcion).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }
        else{
            map.addMarker(new MarkerOptions().position(coordenadas).title(pDescripcion).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas,7f));
        //map.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));
    }


}