package ncku.geo.MileageBattle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseTurn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_turn);
        c1 = findViewById(R.id.choose_c1);
        c2 = findViewById(R.id.choose_c2);
        t1 = findViewById(R.id.text_c1);
        t2 = findViewById(R.id.text_c2);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseit(c1, t1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseit(c2, t2);
            }
        });
        countryy_pair[0] = new String[]{"台灣","馬來西亞"};
        countryy_pair[1] = new String[]{"日本","大陸"};
        countryy_pair[2] = new String[]{"韓國","越南"};
        countryy_pair[3] = new String[]{"俄羅斯","泰國"};
        countryy_pair[4] = new String[]{"印尼","香港"};
        countryy_pair[5] = new String[]{"菲律賓","孟加拉"};
        countryy_pair[6] = new String[]{"蒙古","新加坡"};
        countryy_pair[7] = new String[]{"緬甸","印尼"};

    }

    ImageView c1, c2;
    TextView t1, t2;
    boolean first_to_choose = true;
    int cardset = -1;
    String string_tmp1;
    String[][] countryy_pair = new String[8][2];

    private void chooseit(ImageView pp, TextView tt){
        if(first_to_choose) {
            cardset = (int) (Math.random() * 7);
            if (Math.random() > 0.5) {
                tt.setText("先手\n起點為: ");
                string_tmp1 = "後手\n起點為: ";
            }else{
                tt.setText("後手\n起點為: ");
                string_tmp1 = "先手\n起點為: ";
            }
            first_to_choose = false;
            tt.append("\n"+countryy_pair[cardset][0]);
        }else{
            tt.setText(string_tmp1);
            tt.append("\n"+countryy_pair[cardset][1]);
            findViewById(R.id.button_intogamepage).setVisibility(View.VISIBLE);
        }

        tt.setVisibility(View.VISIBLE);
    }

    public void startthegame(View v){
        onPause();
        Intent it = new Intent();
        it.setClass(this, GamePage.class);
        it.putExtra("cardset_num", cardset);
        startActivityForResult(it, 11);
    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 11 && resultCode == RESULT_OK){
            ///
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}