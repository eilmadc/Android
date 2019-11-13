package com.example.eac3_p2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION=1;
    private LatLng current;
    private Marker currentmarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Añadir un marcador en Valls(Tarragona) y mover la camera al marcador 247364
        LatLng valls = new LatLng(41.283536, 1.247364);
        //ZOOM = 13
        float zoom=13f;
        //Asignamos el tipo de mapa=TERRAIN
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        //Asignamos el marcador a la ubicacion
        mMap.addMarker(new MarkerOptions().position(valls).title("Marker in Valls"));
        //Centramos la camara en la ubicacion y hacemos zoom(13)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(valls, zoom));
        //Habilitar controles de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Circulo en Valls
        addCircle(valls);

        //Chequeo permisos para Current Location
        enableMyLocation();

        //Current Location
        LocationManager locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission")
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        onLocationChanged(location);
    }

//Boton de coordenadas en Current location
    public void showInfoMarker(View view){
        currentmarker.showInfoWindow();
    }

//Habilitar Seguimiento de Ubicacion(requiere chequeo de otorgamiento de permisos de ubicación)
    private void enableMyLocation(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
                    {
                        mMap.setMyLocationEnabled(true);
            }else{
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_LOCATION_PERMISSION);
                }
    }


//Chequeo de Permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       //Chequeo de permisos de localizacion permitidos
        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    enableMyLocation();
                    break;
                }
        }
    }

    // Añadir circulo
    public void addCircle(LatLng latLng){
        CircleOptions circleOptions=new CircleOptions()
                .center(new LatLng(latLng.latitude,latLng.longitude))
                .radius(500)
                .fillColor(Color.parseColor("#C3DAE8"))
                .strokeColor(Color.parseColor("#C3C8E8"))
                .strokeWidth(4);

        Circle circle=mMap.addCircle(circleOptions);
    }

    //Current Location marker
   public void markerCurrentLocation(LatLng currentlocation){
        String snippet =String.format(Locale.getDefault(),"(%1$5f, %2$.5f",
                currentlocation.latitude,
                currentlocation.longitude);

        currentmarker=mMap.addMarker(new MarkerOptions()
            .position(currentlocation)
            .title("Meva localitzaciò es: ")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            .snippet(snippet));
   }



    public void onLocationChanged(@NonNull Location location) {
        MarkerOptions mp=new MarkerOptions();
        LatLng currentLtn= new LatLng(location.getLatitude(),location.getLongitude());
        mp.position(currentLtn);
        markerCurrentLocation(currentLtn);
    }



}
