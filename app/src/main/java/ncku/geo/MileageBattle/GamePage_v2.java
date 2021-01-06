package ncku.geo.MileageBattle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;
import androidx.annotation.NonNull;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;

public class GamePage_v2 extends AppCompatActivity implements /*LocationListener , */OnMapReadyCallback {

    //double lat = 22.994981960446914;
    //double lng = 120.22902201915186;
    GoogleMap Map=null;
    Toast tos;
    int card_set;
    int start_with_player;
    String[][] countryy_pair = new String[7][2];
    String[][] country = new String[15][10];
    Double[][] country_pair = new Double[7][4];


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
        SupportMapFragment smf=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        smf.getMapAsync(this);

        Intent it = getIntent();
        card_set = it.getIntExtra("cardset_num",-1);
        start_with_player = it.getIntExtra("start_with",-1);
//                if(start_with_player==1){
//            ((TextView)findViewById(R.id.textView)).setText("先手:"+countryy_pair[cardset][0]+"\n後手:"+countryy_pair[cardset][1]);
//        }else {
//            ((TextView)findViewById(R.id.textView)).setText("先手:"+countryy_pair[cardset][1]+"\n後手:"+countryy_pair[cardset][0]);
//        }
        play_music(R.raw.african, 0);
        country[0] = new String[]{"台灣", "f22", "f12", "f24", "f30", "日本", "菲律賓", "香港", "23.727", "120.818"};
        country[1] = new String[]{"日本", "f13", "f18", "f35", "f32", "台灣", "韓國", "俄羅斯", "36.044", "138.68"};
        country[2] = new String[]{"韓國", "f11", "f10", "f34", "f41", "日本", "俄羅斯", "蒙古", "39.534", "126.484"};
        country[3] = new String[]{"俄羅斯", "f28", "f02", "f20", "f38", "日本", "韓國", "蒙古", "51.586", "116.021"};
        country[4] = new String[]{"大陸", "f26", "f21", "f37", "f39", "蒙古", "緬甸", "香港", "31.612", "110.664"};
        country[5] = new String[]{"菲律賓", "f01", "f25", "f42", "f43", "台灣", "印尼", "新加坡", "12.075", "122.779"};
        country[6] = new String[]{"泰國", "f42", "f14", "f03", "f23", "馬來西亞", "越南", "緬甸", "17.241", "102.37"};
        country[7] = new String[]{"印尼", "f33", "f00", "f42", "f43", "菲律賓", "越南", "新加坡", "23.569", "90.156"};
        country[8] = new String[]{"馬來西亞", "f15", "f42", "f16", "f08", "泰國", "緬甸", "新加坡", "-1.878", "120.553"};
        country[9] = new String[]{"越南", "f42", "f14", "f37", "f43", "泰國", "印尼", "香港", "4.474", "101.707"};
        country[10] = new String[]{"蒙古", "f17", "f29", "f27", "f03", "韓國", "俄羅斯", "大陸", "13.156", "107.989"};
        country[11] = new String[]{"緬甸", "f03", "f07", "f42", "f40", "大陸", "泰國", "馬來西亞", "46.222", "104.951"};
        country[12] = new String[]{"新加坡", "f15", "f19", "f05", "f42", "菲律賓", "印尼", "馬來西亞", "22.569", "96.09"};
        country[13] = new String[]{"香港", "f44", "f09", "f37", "f31", "台灣", "大陸", "越南", "1.315", "103.873"};


        country_pair[0]	=	new	Double[]{ 23.727, 120.818, 4.474 ,101.707};
        country_pair[1]	=	new	Double[]{ 36.044,138.68 , 31.612,110.664};
        country_pair[2]	=	new	Double[]{ 39.534,126.484, 13.156,107.989};
        country_pair[3]	=	new	Double[]{ 51.586,116.021, 17.241,102.37 };
        country_pair[4]	=	new	Double[]{ -1.878,120.553, 22.393,114.05 };
        country_pair[5]	=	new	Double[]{ 46.222,104.951, 1.315 ,103.873};
        country_pair[6]	=	new	Double[]{ 22.569,96.09  , 12.075,122.779};
        countryy_pair[0] = new String[]{"台灣","馬來西亞"};
        countryy_pair[1] = new String[]{"日本","大陸"};
        countryy_pair[2] = new String[]{"韓國","越南"};
        countryy_pair[3] = new String[]{"俄羅斯","泰國"};
        countryy_pair[4] = new String[]{"印尼","香港"};
        countryy_pair[5] = new String[]{"蒙古","新加坡"};
        countryy_pair[6] = new String[]{"緬甸","菲律賓"};

        t_a = 5;
        t_b = 5;
        State_a = countryy_pair[card_set][0];
        State_b = countryy_pair[card_set][1];
        ((TextView)findViewById(R.id.ticket_a)).setText("里數機票："+t_a+"張");
        ((TextView)findViewById(R.id.ticket_b)).setText("里數機票："+t_b+"張");
        button_set();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Map=googleMap;
        Map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35, 120.22)));
        //Map.moveCamera(CameraUpdateFactory.zoomTo(3));
        MarkerOptions mo = new MarkerOptions();
        MarkerOptions no = new MarkerOptions();

        mo.position(new LatLng(country_pair[card_set][0], country_pair[card_set][1])).title(countryy_pair[card_set][0]);
        Map.addMarker(mo);

        no.position(new LatLng(country_pair[card_set][2], country_pair[card_set][3])).title(countryy_pair[card_set][1]);
        no.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        Map.addMarker(no);


        //add line
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	36.044,138.68	)).add(new LatLng(	23.727,120.818)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	36.044,138.68	)).add(new LatLng(	39.534,126.484)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	36.044,138.68	)).add(new LatLng(	51.586,116.021)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	46.222,104.951)).add(new LatLng(	39.534,126.484)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	46.222,104.951)).add(new LatLng(	51.586,116.021)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	46.222,104.951)).add(new LatLng(	31.612,110.664)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	51.586,116.021)).add(new LatLng(	39.534,126.484)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	12.075,122.779)).add(new LatLng(	23.727,120.818)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	12.075,122.779)).add(new LatLng(	-1.878,120.553)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	12.075,122.779)).add(new LatLng(	1.315,103.873	)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	22.393,114.05	)).add(new LatLng(	23.727,120.818)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	22.393,114.05	)).add(new LatLng(	31.612,110.664)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	22.393,114.05	)).add(new LatLng(	13.156,107.989)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	22.569,96.09	)).add(new LatLng(	31.612,110.664)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	22.569,96.09	)).add(new LatLng(	17.241,102.37	)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	22.569,96.09	)).add(new LatLng(	4.474,101.707	)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	13.156,107.989)).add(new LatLng(	17.241,102.37	)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	13.156,107.989)).add(new LatLng(	-1.878,120.553)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	4.474,101.707	)).add(new LatLng(	17.241,102.37	)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	4.474,101.707	)).add(new LatLng(	1.315,103.873	)).width(2).color(Color.BLUE));
        Map.addPolyline(new  PolylineOptions().add(new LatLng(	1.315,103.873	)).add(new LatLng(	-1.878,120.553)).width(2).color(Color.BLUE));
        tos.setText("Ready");
        tos.show();
    }

    int t_a, t_b;
    String State_a, State_b, Des_a, Des_b;
    public void buy_Ticket(View V){
        Button t = (Button)V;
        if(V.getId() == R.id.a1 || V.getId() == R.id.a2 || V.getId() == R.id.a3){
            t_a--;
            Des_a = t.getText().toString();
            ((TextView)findViewById(R.id.ticket_a)).setText("里數機票："+t_a+"張\n從"+State_a+"到"+Des_a);
        }
        else {
            t_b--;
            Des_b = t.getText().toString();
            ((TextView)findViewById(R.id.ticket_b)).setText("里數機票："+t_b+"張\n從"+State_b+"到"+Des_b);
        }
    }

    private void button_set(){
        for(int i = 0; i<=6; i++){
            if(State_a == country[i][0]){
                ((Button)findViewById(R.id.a1)).setText(country[i][5]);
                ((Button)findViewById(R.id.a2)).setText(country[i][6]);
                ((Button)findViewById(R.id.a3)).setText(country[i][7]);
            }
            if(State_b == country[i][0]){
                ((Button)findViewById(R.id.b1)).setText(country[i][5]);
                ((Button)findViewById(R.id.b2)).setText(country[i][6]);
                ((Button)findViewById(R.id.b3)).setText(country[i][7]);
            }
        }
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