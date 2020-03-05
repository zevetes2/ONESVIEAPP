package com.example.onesvieapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ContactosFragment extends Fragment {
    private RelativeLayout redfacebook, redtwitter, redinstagram, redyoutube, redpagina;
    private ImageView mapa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_contactos, container, false);
        redfacebook = (RelativeLayout) view.findViewById(R.id.redfacebook);
        redtwitter = (RelativeLayout) view.findViewById(R.id.redtwitter);
        redinstagram = (RelativeLayout) view.findViewById(R.id.redinstagram);
        redyoutube = (RelativeLayout) view.findViewById(R.id.redyoutube);
        redpagina = (RelativeLayout) view.findViewById(R.id.redpagina);
        mapa = (ImageView) view.findViewById(R.id.mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=ONESVIE"));
                intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        redfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                Intent like;
                uri = Uri.parse("fb://page/"); //page id de facebook de Onesvie
                like = new Intent(Intent.ACTION_VIEW,uri);
                like.setPackage("com.facebook.katana");
                try{
                    startActivity(like);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ONESVIE.OFICIALRD")));
                }
            }
        });
        redtwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ONESVIERD")));
            }
        });
        redinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ONESVIERD/")));
            }
        });
        redyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCv_8ercTZdX0tMn7tAWm7CA")));
            }
        });
        redpagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://onesvie.gob.do/")));
            }
        });
        return view;
    }
}
