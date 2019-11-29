package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.helloworld.model.Dosen;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DataDosenService dataDosenService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataDosenService = RetrofitClient.getRetrofiyInstace().create(DataDosenService.class);
        getAllDataDosen();
        insertDosen();
    }


    private void getAllDataDosen(){
        Call<List<Dosen>> call = dataDosenService.getDosenAll("72170110");
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                for(Dosen dosen : response.body())
                {
                    System.out.println("Nama : "+dosen.getNama());
                    System.out.println("NIDN : "+dosen.getNidn());
                }

            }
            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something wrong dude...",
                        Toast.LENGTH_LONG) .show();

            }
        });
    }

    private void insertDosen(){
        Call<DefaultResult> call = dataDosenService.insertDosen("aaa","11111111",
                "aws","aaa@si.ukdw.ac.id","S Kom","aaa.jpg","72170110");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                //System.out.println(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                System.out.println("massage :"+t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong dude...",
                        Toast.LENGTH_LONG) .show();

            }
        });
    }
}