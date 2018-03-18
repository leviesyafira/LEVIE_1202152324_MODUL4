package com.example.levie.levie_1202152324_modul4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button list_nama,pencarigambar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menginisiasi menggunakan id
        list_nama = findViewById(R.id.list_namamahasiswa);
        pencarigambar = findViewById(R.id.cari_gambar);

        //melakukan set button
        list_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //melakukan set intent, pindah dari mainactivity ke layout list nama mahasiswa
                Intent a = new Intent(MainActivity.this,ListNama.class);
                startActivity(a);
            }
        });
        //melakukan set button
        pencarigambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //melakukan set intent, pindah dari mainactivity ke layout pencari gambar
                Intent b = new Intent(MainActivity.this,PencariGambar.class);
                startActivity(b);
            }
        });
    }
}
