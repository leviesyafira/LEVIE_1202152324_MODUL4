package com.example.levie.levie_1202152324_modul4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class PencariGambar extends AppCompatActivity {

    Button mulai_cari;
    ImageView tampil_gambar;
    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        //menginisiasi menggunakan id
        url = findViewById(R.id.linkgambar);
        mulai_cari = findViewById(R.id.btnmencari);
        tampil_gambar = findViewById(R.id.gambar);

        mulai_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URLGambar = url.getText().toString();
                new DownloadImage().execute(URLGambar);
            }
        });
    }

    class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... voids) {

            String imageURL = voids[0];

            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url yang diinput
                InputStream input = new java.net.URL(imageURL).openStream();
                // mengubah input dari url ke bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // melakukan set bitmap ke imageView yang sudah tersedia
            tampil_gambar.setImageBitmap(result);

        }
    }
}
