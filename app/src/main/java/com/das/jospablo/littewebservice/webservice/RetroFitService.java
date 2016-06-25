package com.das.jospablo.littewebservice.webservice;

import retrofit2.Retrofit;

/**
 * Created by JoséPablo on 24/06/16.
 * Retrofit Service, initiate interface.
 */
public class RetroFitService {

    public static GitHubApi getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        return retrofit.create(GitHubApi.class);
    }

}
