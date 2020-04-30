package nl.mahmood.mapcalculatearea;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener
{
    List<LatLng> mlatLngs = new ArrayList<>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        googleMap.setOnMapLongClickListener(this);
        LatLng amsterdam = new LatLng(52.3791, 4.9004);
        mMap.addMarker(new MarkerOptions().position(amsterdam).title("Marker in Amsterdam"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(amsterdam, 14));
        this.mlatLngs.add(amsterdam);
    }

    @Override
    public void onMapLongClick(LatLng latLng)
    {
        this.mlatLngs.add(latLng);
        mMap.addMarker(new MarkerOptions().position(latLng).title("New Place"));
        if (mlatLngs.size() == 4)
        {
            Toast.makeText(this, String.format("%.2f", SphericalUtil.computeArea(mlatLngs)) + " square meters", Toast.LENGTH_LONG).show();
            mlatLngs.clear();
            mMap.clear();
        }
    }
}
