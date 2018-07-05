package id.ac.uim.kamusmadura.activitiy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import id.ac.uim.kamusmadura.R;

public class PlayActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageView btnPlayOption;
    private ImageView btnPlayHome;
    private ImageView btnPlayBack;
    private ImageView btnPlayNext;
    private int page = 0;
    private ImageView btnOn;
    private String settinganSound;
    AudioManager audioManager;
    private int currentPage;

    ViewPagerAdapter pageradapter;


    private int[] chapter1 = {
            R.drawable.belajar_1,
            R.drawable.belajar_2,
            R.drawable.belajar_3,
            R.drawable.belajar_4,
            R.drawable.belajar_5,
            R.drawable.belajar_6,
            R.drawable.belajar_7,
            R.drawable.belajar_8
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int pilihan = getIntent().getIntExtra("posisi", 0);

        initView();
        pageradapter = new ViewPagerAdapter(this, chapter1);
        viewPager.setAdapter(pageradapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
//                Toast.makeText(PlayActivity.this, "posisi"+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        btnPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();

                if (currentPage == pageradapter.getCount() - 1) {
//                    Toast.makeText(PlayActivity.this, "Tes Halaman Terakhir", Toast.LENGTH_SHORT).show();
                    final Dialog dialog = new Dialog(PlayActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setContentView(R.layout.dialog_hebat);
                    dialog.show();

                    MediaPlayer player = MediaPlayer.create(PlayActivity.this, R.raw.suarabilalhebat);
                    player.start();
                }


                viewPager.arrowScroll(View.FOCUS_RIGHT);

            }
        });

        btnPlayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                viewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });

        btnPlayOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                final Dialog dialog = new Dialog(PlayActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_option);
                dialog.show();

                btnOn = (ImageView) dialog.findViewById(R.id.btnSoundOn);
                //ngambil data
                SharedPreferences pref = getSharedPreferences("setting", 0);
                settinganSound = pref.getString("sound", "on");
                if (settinganSound.equals("on")) {
                    btnOn.setImageResource(R.drawable.on);
                } else if (settinganSound.equals("off")) {
                    btnOn.setImageResource(R.drawable.off);
                }

                btnOn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ngambil data
                        playSound();
                        SharedPreferences pref = getSharedPreferences("setting", 0);
                        settinganSound = pref.getString("sound", "on");
                        if (settinganSound.equals("on")) {
                            btnOn.setImageResource(R.drawable.off);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("sound", "off");
                            editor.commit();
                            //simpan data

                            //setting audio on
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_SHOW_UI);

                        } else if (settinganSound.equals("off")) {
                            btnOn.setImageResource(R.drawable.on);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("sound", "on");
                            editor.commit();

                            //setting off
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 15, AudioManager.FLAG_SHOW_UI);
                        }

                    }
                });

            }
        });


        btnPlayHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayActivity.this, BelajarActivity.class));
                playSound();
                finish();
            }
        });
    }

    private void playSound() {
        MediaPlayer player = MediaPlayer.create(PlayActivity.this, R.raw.sfx_button);
        player.start();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnPlayOption = (ImageView) findViewById(R.id.btnPlayOption);
        btnPlayHome = (ImageView) findViewById(R.id.btnPlayHome);
        btnPlayBack = (ImageView) findViewById(R.id.btnPlayBack);
        btnPlayNext = (ImageView) findViewById(R.id.btnPlayNext);
    }
}
