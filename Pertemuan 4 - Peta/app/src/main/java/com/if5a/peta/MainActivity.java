package com.if5a.peta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.if5a.peta.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityMainBinding binding;
    private GoogleMap map;
    private List<Lokasi> restaurantList = new ArrayList<>();
    private List<Lokasi> hospitalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());

        restaurantList.add(new Lokasi("Warung Pindang Pegagan BLPT (Langganan Lamo)", new LatLng(-2.9612041626228516, 104.73878681648175)));
        restaurantList.add(new Lokasi("Masakan Mamah Resto", new LatLng(-2.9615470268969375, 104.73800361150224)));
        restaurantList.add(new Lokasi("Pondok Makan Oma Simpang Polda", new LatLng(-2.9602184272589134, 104.73921596992011)));
        restaurantList.add(new Lokasi("Warung Makan Semarang", new LatLng(-2.959736273770436, 104.73943054663124)));

        hospitalList.add(new Lokasi("Rumah Sakit Bhayangkara (Moh. Hasan) Palembang",new LatLng(-2.958445842442478, 104.73737298113141)));
        hospitalList.add(new Lokasi("Rumah Sakit Umum Sriwijaya Palembang",new LatLng(-2.9593351486377633, 104.73698674305139)));
        hospitalList.add(new Lokasi("Rsmh",new LatLng(-2.965174043248643, 104.74596073162665)));
        hospitalList.add(new Lokasi("Cath Lab BHC RSMH Palembang",new LatLng(-2.965849054793201, 104.74864294065765)));

        SupportMapFragment mapFragment =  (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.fabRestaurant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View v){
                map.clear();

                for (int i = 0; i < restaurantList.size(); i++) {
                    map.addMarker(new MarkerOptions()
                            .position(restaurantList.get(i).getLatLng())
                            .title(restaurantList.get(i).getNama())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    ).showInfoWindow();
                }
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurantList.get(4).getLatLng(),17));
            }
        });

        binding.fabHospital.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View v){
                map.clear();

                for (int i = 0; i < hospitalList.size(); i++) {
                    map.addMarker(new MarkerOptions()
                            .position(hospitalList.get(i).getLatLng())
                            .title(hospitalList.get(i).getNama())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    ).showInfoWindow();
                }
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(hospitalList.get(4).getLatLng(),17));
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng latLngUSer = new LatLng(-2.962752506769098, 104.74000029930386);
        map.addMarker(new MarkerOptions()
                .position(latLngUSer)
                .title("Lokasi Saya"))
                .showInfoWindow();
        map.addCircle(new CircleOptions()
                .center(latLngUSer)
                .radius(100)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(R.color.teal_700));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUSer,17));
    }
}
