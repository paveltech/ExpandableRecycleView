package com.example.dream71.expandablerecycleviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.api.responce.ApiClient;
import com.api.responce.ApiInterface;
import com.server.pojo.Result;
import com.server.pojo.ServerResponce;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public ApiInterface apiInterface;
    public List<Result> resultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        resultList = new ArrayList<>();

        apiCall();
    }


    private void apiCall(){
        Call<ServerResponce> serverResponceCall = apiInterface.getTopRatedMovies("c31dce7dae483b752a1adfcb2a791674");

        Log.d("SERVER_URL" , ""+serverResponceCall.request().url());

        serverResponceCall.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                Log.d("SERVER" , ""+response.body().getResults());
                resultList = response.body().getResults();
                //showData(resultList);
                getList(resultList);
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Log.d("SERVER" , ""+t.getMessage());
            }
        });
    }




    /*
    private void showData(List<Result> results){

        for (int i = 0; i < results.size(); i++){
            Log.d("SERVER_GENER_ID" , ""+results.get(i).getGenreIds());

            for (int j=0 ; j<results.get(i).getGenreIds().size() ; j++){
                Log.d("SERVER_GENER_ID_DETAILS" , ""+results.get(i).getGenreIds().get(j));
            }

        }
    }

*/


    private void getList(List<Result> results){

        List<Title> list = new ArrayList<>();
        for (int i = 0; i < results.size(); i++){

            List<SubTitle> subTitles = new ArrayList<>();

            for (int j =0 ; j < results.get(i).getGenreIds().size() ; j++){

                SubTitle subTitle = new SubTitle(""+results.get(i).getGenreIds().get(j));

                subTitles.add(subTitle);
            }

            Title model = new Title(results.get(i).getTitle() , subTitles , "https://static-01.daraz.com.bd/original/75e936f5b0c10bcd0d101e1f57bd7b8b.jpg");
            list.add(model);
        }

        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this , LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

}
