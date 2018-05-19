package id.ac.uim.kamusmadura.activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.uim.kamusmadura.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBelajar, btnKamus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBelajar = findViewById(R.id.btn_belajar);
        btnKamus = findViewById(R.id.btn_kamus);
        btnBelajar.setOnClickListener(this);
        btnKamus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_belajar:
                startActivity(new Intent(getApplicationContext(), BelajarActivity.class));
                break;
            case R.id.btn_kamus:
                startActivity(new Intent(getApplicationContext(), KamusActivity.class));
                break;
        }
    }
}
