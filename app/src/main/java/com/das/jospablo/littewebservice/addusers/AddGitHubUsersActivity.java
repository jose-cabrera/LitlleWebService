package com.das.jospablo.littewebservice.addusers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.addperson.AddPersonFragment;
import com.das.jospablo.littewebservice.addusers.asynctasks.DonwloadUserInfo;
import com.das.jospablo.littewebservice.addusers.services.AddReposService;
import com.das.jospablo.littewebservice.database.OpenRealm;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.events.UserAdded;
import com.das.jospablo.littewebservice.lib.EventBus;
import com.das.jospablo.littewebservice.lib.GlideImageLoader;
import com.das.jospablo.littewebservice.lib.GreenRobotEventBus;
import com.das.jospablo.littewebservice.lib.ImageLoader;
import com.das.jospablo.littewebservice.userdetail.UserDetailActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class AddGitHubUsersActivity extends AppCompatActivity implements DonwloadUserInfo.userDonwloaded, Adapter.onClick {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    EventBus eventBus;

    Realm realm;
    RealmResults<GitHubUser> users;

    Adapter adapter;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_github_user);
        //TODO: Agregar en el content_repository.xml Un RecyclerView, que este pegado al
        // tope del RelativeLayout, y que ocupe todo el espacio disponible que tiene del papa.
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventBus = GreenRobotEventBus.getInstance();
        eventBus.register(this);

        realm = OpenRealm.open(this);
        imageLoader = new GlideImageLoader(this);

        users = realm.where(GitHubUser.class).findAll();
        setRecyclerView(users);
        users.addChangeListener(new RealmChangeListener<RealmResults<GitHubUser>>() {
            @Override
            public void onChange(RealmResults<GitHubUser> element) {
                if (adapter != null)
                    adapter.notifyDataSetChanged();
            }
        });

    }

    private void setRecyclerView(RealmResults<GitHubUser> users) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new Adapter(users, imageLoader, this);
        recyclerview.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        new AddPersonFragment().show(getSupportFragmentManager(), getString(R.string.addPerson_title));
    }

    @Subscribe
    public void onEvent(UserAdded event) {

        //TODO: Agregar usuarios a la base de Datos
        new DonwloadUserInfo(this, getApplicationContext()).execute(event.getUser());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregistrer(this);
    }

    @Override
    public void userAded(GitHubUser user) {

        if (user != null) {
            if (realm == null)
                realm = Realm.getDefaultInstance();

            RealmResults<GitHubUser> users = realm.where(GitHubUser.class).findAll();

            realm.beginTransaction();
            realm.copyToRealm(user);
            realm.commitTransaction();

            Intent service = new Intent(this, AddReposService.class);
            service.putExtra(AddReposService.EXTRA_USER_ID, user.getLogin());
            startService(service);
        }

    }

    @Override
    public void onClick(String usuario) {
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra(UserDetailActivity.EXTRA_KEY, usuario);
        startActivity(intent);
    }
}