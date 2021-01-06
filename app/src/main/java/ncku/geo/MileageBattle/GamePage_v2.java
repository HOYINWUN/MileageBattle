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
    String[][] country = new String[15][7];
    String[][] country_pair = new String[8][2];


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
        country[0] = new String[]{"台灣", "f22", "f12", "f24", "f30", "23.7269838389111", "120.817809452627"};
        country[1] = new String[]{"日本", "f13", "f18", "f35", "f32", "36.0440762670068", "138.67977098775"};
        country[2] = new String[]{"韓國", "f11", "f10", "f34", "f41", "39.5341946238494", "126.483851575326"};
        country[3] = new String[]{"俄羅斯", "f28", "f02", "f20", "f38", "51.5864962071506", "116.020982656694"};
        country[4] = new String[]{"大陸", "f26", "f21", "f37", "f39", "31.6118306658811", "110.663539674805"};
        country[5] = new String[]{"菲律賓", "f01", "f25", "f42", "f43", "12.0748037723459", "122.77930438463"};
        country[6] = new String[]{"泰國", "f42", "f14", "f03", "f23", "17.2410516259493", "102.370176114122"};
        country[7] = new String[]{"孟加拉", "f00", "f36", "f33", "f06", "23.5694106442576", "90.1561184829716"};
        country[8] = new String[]{"印尼", "f33", "f00", "f42", "f43", "-1.87756262289149", "120.552776614628"};
        country[9] = new String[]{"馬來西亞", "f15", "f42", "f16", "f08", "4.47354331900812", "101.706514897057"};
        country[10] = new String[]{"越南", "f42", "f14", "f37", "f43", "13.1558692515745", "107.988602136247"};
        country[11] = new String[]{"蒙古", "f17", "f29", "f27", "f03", "46.222093115052", "104.950649266283"};
        country[12] = new String[]{"緬甸", "f03", "f07", "f42", "f40", "22.5691826871558", "96.0895898361343"};
        country[13] = new String[]{"新加坡", "f15", "f19", "f05", "f42", "1.31484423606705", "103.873186930365"};
        country[14] = new String[]{"香港", "f44", "f09", "f37", "f31", "22.3927056941769", "114.050231584071"};

        country_pair[0]	=	new	String[]{"	23.727,120.818	", "	4.474,101.707	"};
        country_pair[1]	=	new	String[]{"	36.044,138.68	", "	31.612,110.664	"};
        country_pair[2]	=	new	String[]{"	39.534,126.484	", "	13.156,107.989	"};
        country_pair[3]	=	new	String[]{"	51.586,116.021	", "	17.241,102.37	"};
        country_pair[4]	=	new	String[]{"	-1.878,120.553	", "	22.393,114.05	"};
        country_pair[5]	=	new	String[]{"	12.075,122.779	", "	23.569,90.156	"};
        country_pair[6]	=	new	String[]{"	46.222,104.951	", "	1.315,103.873	"};
        country_pair[7]	=	new	String[]{"	22.569,96.09	", "	-1.878,120.553	"};

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