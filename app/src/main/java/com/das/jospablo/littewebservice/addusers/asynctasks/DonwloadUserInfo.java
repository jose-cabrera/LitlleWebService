package com.das.jospablo.littewebservice.addusers.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.webservice.RetroFitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JoséPablo on 16/07/16.
 */
public class DonwloadUserInfo extends AsyncTask<String, Void, GitHubUser> {

    userDonwloaded listener;
    Context context;

    public DonwloadUserInfo(userDonwloaded listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, R.string.descargando_usuario, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected GitHubUser doInBackground(String... params) {

        try{
            Response<GitHubUser> user = RetroFitService.getInstance().getUserInfo(params[0]).execute();
            return user.body();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(GitHubUser user) {
        listener.userAded(user);
    }

    public interface userDonwloaded{
        void userAded(GitHubUser user);
    }

}
