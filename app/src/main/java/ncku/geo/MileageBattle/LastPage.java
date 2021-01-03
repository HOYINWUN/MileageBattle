package ncku.geo.MileageBattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LastPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
        Button btn1 = findViewById(R.id.toFirst_btn);
        Button btn2 = findViewById(R.id.toSecond_btn);
        btn1.setBackgroundColor(0xFFC6B8B8);
        btn2.setBackgroundColor(0xFFC6B8B8);
    }
    public void backTofirst(View v){
        Intent it=new Intent();
        it.setClass(this,MainActivity.class);
        startActivity(it);
    }
    public void backTosecond(View v){
        Intent it=new Intent();
        it.setClass(this,GamePage.class);
        startActivity(it);
    }
}