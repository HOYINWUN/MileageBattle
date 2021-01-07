package ncku.geo.MileageBattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class LastPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
        Button btn1 = findViewById(R.id.toFirst_btn);
        Button btn2 = findViewById(R.id.toSecond_btn);
        btn1.setBackgroundColor(0xFFC6B8B8);
        btn2.setBackgroundColor(0xFFC6B8B8);
        play_music(R.raw.testmusic, 0);
        Intent game = getIntent();
        int MeailgeA = game.getIntExtra("MeailgeA", -1);
        int MeailgeB = game.getIntExtra("MeailgeB", -1);
        Boolean winner = game.getBooleanExtra("Winner_is_A?", true);
        ((TextView)findViewById(R.id.textView2)).setText(winner?"HA HA！YOU WIN！":"OH NO！YOU LOOSE！\n里程數："+MeailgeA);
        ((TextView)findViewById(R.id.textView3)).setText(winner?"OH NO！YOU LOOSE！":"HA HA！YOU WIN！\n里程數："+MeailgeB);

    }
    public void backTofirst(View v){
        Intent it = new Intent();
        it.setClass(this,MainActivity.class);
        startActivity(it);
    }
    public void backTosecond(View v){
        Intent it = new Intent();
        it.setClass(this,ChooseTurn.class);
        startActivity(it);
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
}