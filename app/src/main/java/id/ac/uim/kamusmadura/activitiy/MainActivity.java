package id.ac.uim.kamusmadura.activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.uim.kamusmadura.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBelajar, btnKamus, btnTentang, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //memanggil id dari layout agar terhubung ke file java
        btnBelajar = findViewById(R.id.btn_belajar);
        btnKamus = findViewById(R.id.btn_kamus);
        btnTentang = findViewById(R.id.btn_tentang);
        btnKeluar = findViewById(R.id.btn_keluar);

        ///memberikan aksi klik ke button
        btnBelajar.setOnClickListener(this);
        btnKamus.setOnClickListener(this);
        btnTentang.setOnClickListener(this);
        btnKeluar.setOnClickListener(this);
    }

    //method yang dijalankan ketika button di klik, (sesuai dengan id yang dipilih)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_belajar:
                startActivity(new Intent(getApplicationContext(), BelajarActivity.class));
                break;
            case R.id.btn_kamus:
                startActivity(new Intent(getApplicationContext(), KamusActivity.class));
                break;
            case R.id.btn_tentang:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                break;
            case R.id.btn_keluar:
                finish();
                break;
        }
    }
}
