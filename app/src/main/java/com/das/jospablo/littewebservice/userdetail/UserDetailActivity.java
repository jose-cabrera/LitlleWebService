package com.das.jospablo.littewebservice.userdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.database.OpenRealm;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.lib.GlideImageLoader;
import com.das.jospablo.littewebservice.lib.ImageLoader;
import com.das.jospablo.littewebservice.respositorys.RepositoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class UserDetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEY = "extra_key";

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.company)
    TextView company;
    @BindView(R.id.created_date)
    TextView createdDate;
    @BindView(R.id.go_to_repos)
    Button goToRepos;

    Realm realm;
    GitHubUser user;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);

        String user_login = getIntent().getExtras().getString(EXTRA_KEY);
        realm = OpenRealm.open(this);
        imageLoader = new GlideImageLoader(this);

        user = realm.where(GitHubUser.class).equalTo("login", user_login).findFirst();

        initViews(user);
    }

    private void initViews(GitHubUser user) {
        if (user != null) {
            login.setText(user.getLogin());
            name.setText(user.getName());
            location.setText(user.getLocation());
            company.setText(user.getCompany());
            createdDate.setText(user.getCreated_at());
            email.setText(user.getEmail());

            imageLoader.load(avatar, user.getAvatar_url());
            goToRepos.setEnabled(true);
        } else {
            Toast.makeText(this, "El usuario no pudo ser obtenido", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

    }

    @OnClick(R.id.go_to_repos)
    public void onClick() {

        Intent intent = new Intent(this, RepositoryActivity.class);
        intent.putExtra(RepositoryActivity.EXTRA_USER_ID, user.getLogin());
        startActivity(intent);

    }
}
