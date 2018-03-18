package com.example.levie.levie_1202152324_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {
    //pembuatan array untuk nama mahasiswa
    private String[] nama = { "LEVIE", "EXA", "DAYANE", "APRIL", "SHINTHYA", "MAISHA", "DILLA", "FINA", "RATIH", "ATHAYA",
            "AULIA", "AMBAR", "NABILA", "INTAN", "FARAS", "ARIN", "IKRAM"};

    ListView listnama_mahasiswa;
    Button btn_mulai;

    private static Parcelable mListViewScrollPos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        //menginisiasi menggunakan id
        listnama_mahasiswa = findViewById(R.id.list_nama);
        btn_mulai = findViewById(R.id.button_mulai);

        //melakukan set adapter array
        listnama_mahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));

        // melakukan restore the ListView position
        if (mListViewScrollPos != null) {
            listnama_mahasiswa.onRestoreInstanceState(mListViewScrollPos);
        }


        btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //memanggil class mytask dan melakukan eksekusi
                new mytask().execute();
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // menyimpan ListView position
        mListViewScrollPos = listnama_mahasiswa.onSaveInstanceState();
    }

    class mytask extends AsyncTask<Void,String,String> {

        ArrayAdapter<String> adapter;
        ProgressDialog progressdialog;
        int count;


        @Override
        protected void onPreExecute() {
            //melakukan pengambilan adapter dari array yang dibuat
            adapter = (ArrayAdapter<String>)listnama_mahasiswa.getAdapter();

            //membuat object progress dialog
            progressdialog = new ProgressDialog(ListNama.this);

            //melakukan set judul progress dialog
            progressdialog.setTitle("Loading Data");
            progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressdialog.setMax(100);
            progressdialog.setProgress(0);
            progressdialog.setCancelable(true);

            progressdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressdialog.dismiss();
                }
            });

            //tampilkan progress dialog
            progressdialog.show();
            //memastikan bahwa hitungan sebelum di eksekusi adalah 0
            count = 0;
        }


        @Override
        protected String doInBackground(Void... voids) {
            //membuat perulangan agar nama mahasiwa dapat muncul
            for (String namamhs : nama){
                publishProgress(namamhs);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            //mengembalikan nilai dengan tulisan
            return "Seluruh Nama Sudah Muncul";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //adapter array memluai dari array 0
            adapter.add(values[0]);
            //melakukan perhitungan saat progress update bertambah
            count++;
            //melakukan set hitungan pada progress dialog
            progressdialog.setProgress(count);
        }

        @Override
        protected void onPostExecute(String result) {
            //menampilkan nilai dari return pada method doInBackground
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            // prosess dialog akan hilang otomatis setelah loading progress full
            progressdialog.hide();

        }

    }

}
