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
import android.widget.ImageView;
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
    int start_A, start_B;
    String[][] country_pair = new String[7][2];
    String[][] country = new String[15][10];
    Double[][] country_coordinate = new Double[14][2];


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
        country[0] = new String[]{"台灣", "22", "12", "24", "30", "日本", "菲律賓", "香港", "23.727", "120.818"};
        country[1] = new String[]{"日本", "13", "18", "34", "32", "台灣", "韓國", "俄羅斯", "36.044", "138.68"};
        country[2] = new String[]{"韓國", "11", "10", "35", "41", "日本", "俄羅斯", "蒙古", "39.534", "126.484"};
        country[3] = new String[]{"俄羅斯", "28", "2", "20", "38", "日本", "韓國", "蒙古", "51.586", "116.021"};
        country[4] = new String[]{"大陸", "26", "21", "37", "39", "蒙古", "緬甸", "香港", "31.612", "110.664"};
        country[5] = new String[]{"菲律賓", "1", "25", "42", "43", "台灣", "印尼", "新加坡", "12.075", "122.779"};
        country[6] = new String[]{"泰國", "42", "14", "3", "23", "馬來西亞", "越南", "緬甸", "17.241", "102.37"};
        country[7] = new String[]{"印尼", "33", "0", "42", "43", "菲律賓", "越南", "新加坡", "23.569", "90.156"};
        country[8] = new String[]{"馬來西亞", "15", "42", "16", "f08", "泰國", "緬甸", "新加坡", "-1.878", "120.553"};
        country[9] = new String[]{"越南", "42", "14", "37", "43", "泰國", "印尼", "香港", "4.474", "101.707"};
        country[10] = new String[]{"蒙古", "17", "29", "27", "3", "韓國", "俄羅斯", "大陸", "13.156", "107.989"};
        country[11] = new String[]{"緬甸", "3", "7", "42", "40", "大陸", "泰國", "馬來西亞", "46.222", "104.951"};
        country[12] = new String[]{"新加坡", "15", "19", "5", "42", "菲律賓", "印尼", "馬來西亞", "22.569", "96.09"};
        country[13] = new String[]{"香港", "44", "9", "37", "31", "台灣", "大陸", "越南", "1.315", "103.873"};



        country_coordinate[0]	=	new	Double[]{	23.727,120.818	};
        country_coordinate[1]	=	new	Double[]{	36.044,138.68	};
        country_coordinate[2]	=	new	Double[]{	39.534,126.484	};
        country_coordinate[3]	=	new	Double[]{	51.586,116.021	};
        country_coordinate[4]	=	new	Double[]{	31.612,110.664	};
        country_coordinate[5]	=	new	Double[]{	12.075,122.779	};
        country_coordinate[6]	=	new	Double[]{	17.241,102.37	};
        country_coordinate[7]	=	new	Double[]{	-1.878,120.553	};
        country_coordinate[8]	=	new	Double[]{	4.474,101.707	};
        country_coordinate[9]	=	new	Double[]{	13.156,107.989	};
        country_coordinate[10]	=	new	Double[]{	46.222,104.951	};
        country_coordinate[11]	=	new	Double[]{	22.569,96.09	};
        country_coordinate[12]	=	new	Double[]{	1.315,103.873	};
        country_coordinate[13]	=	new	Double[]{	22.393,114.05	};



        country_pair[0] = new String[]{"台灣","馬來西亞"};
        country_pair[1] = new String[]{"日本","大陸"};
        country_pair[2] = new String[]{"韓國","越南"};
        country_pair[3] = new String[]{"俄羅斯","泰國"};
        country_pair[4] = new String[]{"印尼","香港"};
        country_pair[5] = new String[]{"蒙古","新加坡"};
        country_pair[6] = new String[]{"緬甸","菲律賓"};

        t_a = 5;
        t_b = 5;
        State_a = country_pair[card_set][0];
        State_b = country_pair[card_set][1];
        FindState();
        start_A = a;
        start_B = b;
        MeailgeA = 0.0;
        MeailgeB = 0.0;
        ((TextView)findViewById(R.id.ticket_a)).setText("里數機票："+t_a+"張\n累積里數"+MeailgeA);
        ((TextView)findViewById(R.id.ticket_b)).setText("里數機票："+t_b+"張\n累積里數"+MeailgeB);
        button_set();
        A1 = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        A2 = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        A3 = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        B1 = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        B2 = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        B3 = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        ((ImageView)findViewById(R.id.CardA_1)).setImageResource(A1);
        ((ImageView)findViewById(R.id.CardA_2)).setImageResource(A2);
        ((ImageView)findViewById(R.id.CardA_3)).setImageResource(A3);
        ((ImageView)findViewById(R.id.CardB_1)).setImageResource(B1);
        ((ImageView)findViewById(R.id.CardB_2)).setImageResource(B2);
        ((ImageView)findViewById(R.id.CardB_3)).setImageResource(B3);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Map=googleMap;
        Map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35, 120.22)));
        Map.clear();
        //Map.moveCamera(CameraUpdateFactory.zoomTo(3));
        MarkerOptions mo = new MarkerOptions();
        MarkerOptions no = new MarkerOptions();

        mo.position(new LatLng(country_coordinate[a][0], country_coordinate[a][1])).title(State_a);
        Map.addMarker(mo);

        no.position(new LatLng(country_coordinate[b][0], country_coordinate[b][1])).title(State_b);
        no.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        Map.addMarker(no);


        //add line
        {
            Map.addPolyline(new PolylineOptions().add(new LatLng(36.044, 138.68)).add(new LatLng(23.727, 120.818)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(36.044, 138.68)).add(new LatLng(39.534, 126.484)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(36.044, 138.68)).add(new LatLng(51.586, 116.021)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(46.222, 104.951)).add(new LatLng(39.534, 126.484)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(46.222, 104.951)).add(new LatLng(51.586, 116.021)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(46.222, 104.951)).add(new LatLng(31.612, 110.664)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(51.586, 116.021)).add(new LatLng(39.534, 126.484)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(12.075, 122.779)).add(new LatLng(23.727, 120.818)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(12.075, 122.779)).add(new LatLng(-1.878, 120.553)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(12.075, 122.779)).add(new LatLng(1.315, 103.873)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(22.393, 114.05)).add(new LatLng(23.727, 120.818)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(22.393, 114.05)).add(new LatLng(31.612, 110.664)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(22.393, 114.05)).add(new LatLng(13.156, 107.989)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(22.569, 96.09)).add(new LatLng(31.612, 110.664)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(22.569, 96.09)).add(new LatLng(17.241, 102.37)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(22.569, 96.09)).add(new LatLng(4.474, 101.707)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(13.156, 107.989)).add(new LatLng(17.241, 102.37)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(13.156, 107.989)).add(new LatLng(-1.878, 120.553)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(4.474, 101.707)).add(new LatLng(17.241, 102.37)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(4.474, 101.707)).add(new LatLng(1.315, 103.873)).width(2).color(Color.BLUE));
            Map.addPolyline(new PolylineOptions().add(new LatLng(1.315, 103.873)).add(new LatLng(-1.878, 120.553)).width(2).color(Color.BLUE));
        }
        tos.setText("Ready");
        tos.show();

    }

    int t_a, t_b;
    int a, b;
    int A1, A2, A3, B1, B2, B3, iv, Switch;
    String State_a, State_b, Des_a, Des_b;

    public void FindState(){
        for(int i = 0; i<=13; i++){
            if(State_a == country[i][0])a=i;
            if(State_b == country[i][0])b=i;
        }
        MeailgeA = Math.sqrt(Math.pow(country_coordinate[a][0]-country_coordinate[start_A][0], 2)+Math.pow(country_coordinate[a][1]-country_coordinate[start_A][1], 2));
        MeailgeB = Math.sqrt(Math.pow(country_coordinate[b][0]-country_coordinate[start_B][0], 2)+Math.pow(country_coordinate[b][1]-country_coordinate[start_B][1], 2));

    }


    public void buy_Ticket(View V){
        Button t = (Button)V;
        if(V.getId() == R.id.a1 || V.getId() == R.id.a2 || V.getId() == R.id.a3){
            t_a--;
            Des_a = t.getText().toString();
            ((TextView)findViewById(R.id.ticket_a)).setText("里數機票："+t_a+"張\n從"+State_a+"到"+Des_a+"\n累積里數："+MeailgeA);
        }
        else {
            t_b--;
            Des_b = t.getText().toString();
            ((TextView)findViewById(R.id.ticket_b)).setText("里數機票："+t_b+"張\n從"+State_b+"到"+Des_b+"\n累積里數："+MeailgeB);
        }

        if(t_a<0||t_b<0){
            winner = MeailgeA>=MeailgeB;
            endthegame();
        }
        check_flight();
    }

    private void check_flight(){
        int d_a=0,d_b=0;
        int countA=0, countB=0;
        for(int i = 0; i<=13; i++){
            if(Des_a == country[i][0])d_a=i;
            if(Des_b == country[i][0])d_b=i;}
        if(Des_a!= ""){
            for(int i=1; i<=4; i++){
                countA += Integer.parseInt(country[d_a][i]) == (A1-R.drawable.f00)?1:0;
                countA += Integer.parseInt(country[d_a][i]) == (A2-R.drawable.f00)?1:0;
                countA += Integer.parseInt(country[d_a][i]) == (A3-R.drawable.f00)?1:0;
            }
        }
        if(Des_b!= ""){
            for(int i=1; i<=4; i++){
                countB += Integer.parseInt(country[d_b][i]) == (B1-R.drawable.f00)?1:0;
                countB += Integer.parseInt(country[d_b][i]) == (B2-R.drawable.f00)?1:0;
                countB += Integer.parseInt(country[d_b][i]) == (B3-R.drawable.f00)?1:0;
            }
        }

            ((TextView)findViewById(R.id.textView)).setText((B1-R.drawable.f00)+" "+(B2-R.drawable.f00)+" "+(B3-R.drawable.f00)+"\n"+Integer.parseInt(country[d_b][1])+" "+Integer.parseInt(country[d_b][2])+" "+Integer.parseInt(country[d_b][3])+Des_b+"\n"+countB);
        if(countA==3){
            State_a = Des_a;
            Des_a = "";
            FindState();
            button_set();
            onMapReady(Map);
            ((TextView)findViewById(R.id.ticket_a)).setText("里數機票："+t_a+"張\n累積里數："+MeailgeA);
            tos.setText("GO");
            tos.show();
            }
        if(countB==3){
            State_b = Des_b;
            Des_b = "";
            FindState();
            button_set();
            onMapReady(Map);
            ((TextView)findViewById(R.id.ticket_b)).setText("里數機票："+t_b+"張\n累積里數："+MeailgeB);
            tos.setText("GO");
            tos.show();
        }
        if (a==start_B||b==start_A){
            winner = a==start_B;
        }
    }

    private void button_set(){
        ((Button)findViewById(R.id.a1)).setText(country[a][5]);
        ((Button)findViewById(R.id.a2)).setText(country[a][6]);
        ((Button)findViewById(R.id.a3)).setText(country[a][7]);
        ((Button)findViewById(R.id.b1)).setText(country[b][5]);
        ((Button)findViewById(R.id.b2)).setText(country[b][6]);
        ((Button)findViewById(R.id.b3)).setText(country[b][7]);

    }

    public void flop(View V){
        iv = (int) (R.drawable.f00 + Math.floor(Math.random()*45));
        ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
    }

    public void switchCard(View V){
        if(iv != 0){
            Switch = iv;
            switch (V.getId()){
                case R.id.CardA_1 :
                    iv = A1;
                    A1 = Switch;
                    ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
                    ((ImageView)findViewById(R.id.CardA_1)).setImageResource(A1);
                    break;
                case R.id.CardA_2 :
                    iv = A2;
                    A2 = Switch;
                    ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
                    ((ImageView)findViewById(R.id.CardA_2)).setImageResource(A2);
                    break;
                case R.id.CardA_3 :
                    iv = A3;
                    A3 = Switch;
                    ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
                    ((ImageView)findViewById(R.id.CardA_3)).setImageResource(A3);
                    break;
                case R.id.CardB_1 :
                    iv = B1;
                    B1 = Switch;
                    ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
                    ((ImageView)findViewById(R.id.CardB_1)).setImageResource(B1);
                    break;
                case R.id.CardB_2 :
                    iv = B2;
                    B2 = Switch;
                    ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
                    ((ImageView)findViewById(R.id.CardB_2)).setImageResource(B2);
                    break;
                case R.id.CardB_3 :
                    iv = B3;
                    B3 = Switch;
                    ((ImageView)findViewById(R.id.imageView)).setImageResource(iv);
                    ((ImageView)findViewById(R.id.CardB_3)).setImageResource(B3);
                    break;

            }
        }
        check_flight();
    }

    Double MeailgeA, MeailgeB;
    Boolean winner;

    public void endthegame(){
        mp.stop();
        Intent it = new Intent();
        it.setClass(this, LastPage.class);

        it.putExtra("MeailgeA", MeailgeA);
        it.putExtra("MeailgeB", MeailgeB);
        it.putExtra("Winner_is_A?", winner);
        startActivityForResult(it, 11);

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