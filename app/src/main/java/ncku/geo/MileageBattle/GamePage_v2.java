package ncku.geo.MileageBattle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;

public class GamePage_v2 extends AppCompatActivity implements /*LocationListener , */OnMapReadyCallback {

    double lat = 22.994981960446914;
    double lng = 120.22902201915186;
    GoogleMap Map=null;
    Toast tos;
    int cardset;
    int start_with_player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page_v2);

        tos=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    200);
            return;
        }
        String pro = lm.getBestProvider(new Criteria(), true);
        //lm.requestLocationUpdates(pro, 5000, 5, this);
        //lm.requestLocationUpdates("network", 5000, 5, this);
//        ((TextView)findViewById(R.id.textView)).setText(lat+""+lng);
        SupportMapFragment smf=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        smf.getMapAsync(this);

        Intent it = getIntent();
        cardset = it.getIntExtra("cardset_num",-1);
        start_with_player = it.getIntExtra("start_with",-1);
//                if(start_with_player==1){
//            ((TextView)findViewById(R.id.textView)).setText("先手:"+countryy_pair[cardset][0]+"\n後手:"+countryy_pair[cardset][1]);
//        }else {
//            ((TextView)findViewById(R.id.textView)).setText("先手:"+countryy_pair[cardset][1]+"\n後手:"+countryy_pair[cardset][0]);
//        }
        play_music(R.raw.african, 0);

    }
/*
    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
//        ((TextView)findViewById(R.id.textView)).setText(lat+"\n"+lng);
        Map.clear();
        if(Map != null)
        {

            LatLng currLoc = new LatLng(lat,lng);
            Map.moveCamera(CameraUpdateFactory.newLatLng(currLoc));
            Map.moveCamera(CameraUpdateFactory.zoomTo(17));
            MarkerOptions mo = new MarkerOptions();
            mo.position(currLoc).title("Here");
            Map.addMarker(mo);
        }
    }

    public void flip(View V){


    }
*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Map=googleMap;
        Map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(23, 120.22)));
//        Map.moveCamera(CameraUpdateFactory.zoomTo(17));
        MarkerOptions mo = new MarkerOptions();
        mo.position(new LatLng(23, 120.22)).title("Taiwan");
        Map.addMarker(mo);
        mo.position(new LatLng(40, 140.22)).title("Japan");
        Map.addMarker(mo);
        //add line
//        Map.addPolyline(new  PolylineOptions().add(new LatLng(23, 120.22)).add(new LatLng(40, 140.22)));
//        Map.addPolyline(new  PolylineOptions().add(new LatLng(43, 120.22)).add(new LatLng(43, 100.22)));
        tos.setText("Ready");
        tos.show();
    }

    MediaPlayer mp;
    public void play_music(int source, int time){
        mp = new MediaPlayer();
        mp.reset();
        mp.setOnPreparedListener(mp2 -> {
            mp.start();
            mp.seekTo(time);
            mp.setLooping(true);
        });
        try {
            mp.setDataSource(this, Uri.parse("android.resource://"+getPackageName()+"/"+source));
            mp.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        mp.stop();
        super.onPause();
    }
}