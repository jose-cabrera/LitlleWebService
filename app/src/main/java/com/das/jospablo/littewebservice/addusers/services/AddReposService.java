package com.das.jospablo.littewebservice.addusers.services;

import android.content.Intent;

import com.das.jospablo.littewebservice.database.OpenRealm;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.entity.Repo;
import com.das.jospablo.littewebservice.lib.IntentServiceMaster;
import com.das.jospablo.littewebservice.webservice.RetroFitService;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JoséPablo on 22/07/16.
 */
public class AddReposService extends IntentServiceMaster {

    public static final String EXTRA_USER_ID = "USER_ID";

    @Override
    public void ejecutarAccion(Intent intent) {

        final String user_login = intent.getExtras().getString(EXTRA_USER_ID);

        Call<List<Repo>> call = RetroFitService.getInstance().listRepos(user_login);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                try {

                    List<Repo> repos = response.body();

                    Realm realm = OpenRealm.open(AddReposService.this);
                    GitHubUser user = realm.where(GitHubUser.class).equalTo("login", user_login).findFirst();

                    if (user != null) {
                        realm.beginTransaction();

                        RealmList<Repo> reposList = new RealmList<>();
                        for(Repo r : repos){
                            reposList.add(Repo.newObject(r, realm));
                        }

                        user.setRepositorios(reposList);
                        realm.commitTransaction();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

    }
}
