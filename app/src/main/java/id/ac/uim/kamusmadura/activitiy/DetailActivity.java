package id.ac.uim.kamusmadura.activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import id.ac.uim.kamusmadura.R;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_WORD = "ITEM_WORD";
    public static final String ITEM_TRANSLATE = "ITEM_TRANSLATE";

    TextView tv_word;
    TextView tv_translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_word = findViewById(R.id.tv_word);
        tv_translate = findViewById(R.id.tv_translate);

        //memasukkan data yang didapat dari kamus ke detail kamus
        tv_word.setText(getIntent().getStringExtra(ITEM_WORD));
        tv_translate.setText(getIntent().getStringExtra(ITEM_TRANSLATE));
    }
}
