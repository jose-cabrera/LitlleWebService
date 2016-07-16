package com.das.jospablo.littewebservice.webservice;

import com.das.jospablo.littewebservice.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by JoséPablo on 24/06/16.
 * GitHub Api Interface
 */
public interface GitHubApi {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
