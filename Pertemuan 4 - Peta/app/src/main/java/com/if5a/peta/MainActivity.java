package com.if5a.peta;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.if5a.peta.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private GoogleMap mMap;
    private List<Lokasi> restaurantList = new ArrayList<>();
    private List<Lokasi> hospitalList = new ArrayList<>();
    private ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
        Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
        Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
        if (fineLocationGranted != null && fineLocationGranted) {
        } else if (coarseLocationGranted != null && coarseLocationGranted) {

        } else {

        }
    });

    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        restaurantList.add(new Lokasi("Kinnicheesetea_kamboja", new LatLng(-2.9682594183643283, 104.74248872494825)));
        restaurantList.add(new Lokasi("Warung Dwi", new LatLng(-2.96774135843591, 104.74047234383686)));
        restaurantList.add(new Lokasi("PEMPEK DAN TEKWAN UDANG MIRNA", new LatLng(-2.9675419447358844, 104.74055879282491)));
        restaurantList.add(new Lokasi("R.M RANG TANJUNG Ariodillah", new LatLng(-2.9673470725734377, 104.74049481874668)));
        restaurantList.add(new Lokasi("Rumah Makan Surya", new LatLng(-2.96769713682167, 104.73984323246108)));
        restaurantList.add(new Lokasi("Ayam gepuk & lele kriuk", new LatLng(-2.9668048238885336, 104.73916921099863)));
        restaurantList.add(new Lokasi("Aditia Masakan Padang", new LatLng(-2.966097669561217, 104.74022063695662)));
        restaurantList.add(new Lokasi("Apis fried chicken", new LatLng(-2.9654504488438254, 104.74004536534841)));
        restaurantList.add(new Lokasi("Catering Qodri", new LatLng(-2.9665457402379416, 104.74135536830319)));
        restaurantList.add(new Lokasi("Saladmom trikora", new LatLng(-2.9678521614727775, 104.73993346090307)));
        hospitalList.add(new Lokasi("Rumah Sakit Umum Pusat Dr. Mohammad Hoesin Instalasi Gawat Darurat", new LatLng(-2.9672710816952863, 104.75069087570334)));
        hospitalList.add(new Lokasi("Rsmh", new LatLng(-2.965330480891752, 104.74594853799859)));
        hospitalList.add(new Lokasi("Rumah Sakit Mata Sriwijaya Eye Centre Palembang", new LatLng(-2.9705786497711784, 104.75074175735665)));
        hospitalList.add(new Lokasi("RSIA YK MADIRA", new LatLng(-2.9725225575818364, 104.752341002132)));
        hospitalList.add(new Lokasi("Rumah Sakit RK. Charitas", new LatLng(-2.9744861904008233, 104.7526238227884)));


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longtitud = location.getLongitude();
                    LatLng latLngLast = new LatLng(latitude, longtitud);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions()
                                    .position(latLngLast)
                                    .title("I'm Here")
                                    .snippet("help me"))
                            .showInfoWindow();
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngLast, 17));
                    mMap.addCircle(new CircleOptions()
                            .center(latLngLast)
                            .radius(100)
                            .strokeColor(Color.TRANSPARENT)
                            .fillColor(R.color.purple_500));
                }
                else {
                    Toast.makeText(MainActivity.this, "NULL COK!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.fabRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                for (int i = 0; i<restaurantList.size(); i++){
                    mMap.addMarker(new MarkerOptions()
                                    .position(restaurantList.get(i).getLatLng())
                                    .title(restaurantList.get(i).getNama())
                                    .snippet("Enak, Murah, Banyak"))
                            .showInfoWindow();
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurantList.get(4).getLatLng(),17));

            }
        });
        binding.fabHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                for (int i = 0; i<hospitalList.size(); i++){
                    mMap.addMarker(new MarkerOptions()
                                    .position(hospitalList.get(i).getLatLng())
                                    .title(hospitalList.get(i).getNama()))
                            .showInfoWindow();
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospitalList.get(4).getLatLng(),17));
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);
        LatLng latLngUser = new LatLng(-2.946406960854266, 104.77176868240942);
        mMap.addMarker(new MarkerOptions().position(latLngUser).title("My Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUser, 13));
//        mMap.addCircle(new CircleOptions()
//                .center(latLngUser)
//                .radius(100)
//                .strokeColor(Color.TRANSPARENT)
//                .fillColor(R.color.purple_500));
//
    }
}