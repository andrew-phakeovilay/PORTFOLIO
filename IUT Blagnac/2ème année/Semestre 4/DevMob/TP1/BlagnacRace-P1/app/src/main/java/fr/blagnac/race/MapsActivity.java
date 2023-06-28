package fr.blagnac.race;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.blagnac.race.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private ActivityMapsBinding binding;
    private TextView cibleTv;
    private Location cibleLoc;
    private Marker myMarker;
    private Marker cibleMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && this.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED )
        {
            requestPermissions(
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

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

        mMap.getUiSettings().setZoomControlsEnabled(true); // affiche les boutons de zoom
        mMap.getUiSettings().setZoomGesturesEnabled(true); // autorise le zoom tactile
        mMap.getUiSettings().setCompassEnabled(false); // n'affiche pas le compas
        mMap.getUiSettings().setMyLocationButtonEnabled(true); // affiche le bouton de localisation

        String cibleDesc = "IUT de Blagnac"; // description de la cible
        double cibleLat = 43.6489983; // coordonnées (latitude, longitude)
        double cibleLon = 1.3749359; // de l’IUT de Blagnac
        cibleLoc = new Location("Cible") ;
        cibleLoc.setLatitude(cibleLat);
        cibleLoc.setLongitude(cibleLon);
        cibleTv = (TextView)findViewById(R.id.tv);
        String cibleTexte = " Cible : " + cibleDesc;
        cibleTv.setText(cibleTexte);

        myMarker = mMap.addMarker(new MarkerOptions()
                .title("Moi").position(new LatLng(0, 0))
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.green_droid))); // Icone Droid vert
        cibleMarker = mMap.addMarker( new MarkerOptions()
                .title("Cible").snippet(cibleDesc)
                .position(new LatLng(cibleLat,cibleLon))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)) ); // Marqueur bleu
    }

    @Override // méthode appelée quand une source de localisation est activée
    public void onProviderEnabled(final String provider) {
        if ("gps".equals(provider)) { // Si le GPS est activé on s'abonne
            abonnementGPS();
        }
    }
    @Override // méthode appelée quand une source de localisation est désactivée
    public void onProviderDisabled(final String provider) {
        if ("gps".equals(provider)) { // Si le GPS est désactivé on se désabonne
            desabonnementGPS();
        }
    }
    @Override // méthode appelée quand les coordonnées GPS du smartphone changent
    public void onLocationChanged(final Location myLoc) {
        // Récupération des cordonnées (latitude, longitude) et création d’un objet
        // myPos de la classe LatLng représentant cette position
        final LatLng myPos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
        // Centrage de la carte sur la position GPS obtenue
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 15));
        myMarker.setPosition(myPos);
        double distance=myLoc.distanceTo(cibleLoc);
        myMarker.setSnippet("à "+ Math.round(distance) + " m de la cible");
        myMarker.showInfoWindow();
        if (distance < 30.0) {
            this.showAlert("Bravo !!", "Vous avez atteint la cible");
            this.desabonnementGPS();
        }
    }
    @Override // méthode appelée quand le status d’une source de localisation change
    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
    }

    public void abonnementGPS() throws SecurityException {
        // demande de réception de localisations du GPS au minimum toutes les 5000 ms et
        // lorsque la distance entre la dernière localisation reçue est supérieure à 10 m
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
    }

    public void desabonnementGPS() throws SecurityException {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // On appelle la méthode pour se désabonner
        desabonnementGPS();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Obtention de la référence du service
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        // Si le GPS est disponible, on s'y abonne
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            abonnementGPS();
        }
    }

    public void showAlert(String titre, String message) {
        AlertDialog.Builder ADBuilder = new AlertDialog.Builder(this);
        ADBuilder.setTitle(titre);
        ADBuilder.setMessage(message);
        ADBuilder.setCancelable(true);
        ADBuilder.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog AD = ADBuilder.create();
        AD.show();
    }
}