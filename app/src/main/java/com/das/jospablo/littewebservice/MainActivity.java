package com.das.jospablo.littewebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.das.jospablo.littewebservice.entity.Repo;
import com.das.jospablo.littewebservice.webservice.RetroFitService;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<List<Repo>> repos = RetroFitService.getInstance().listRepos("jose-cabrera");

        repos.request().body();
    }
}
