package com.das.jospablo.littewebservice.respositorys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.addperson.AddPersonFragment;
import com.das.jospablo.littewebservice.addusers.services.AddReposService;
import com.das.jospablo.littewebservice.database.OpenRealm;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.entity.Repo;
import com.das.jospablo.littewebservice.events.UserAdded;
import com.das.jospablo.littewebservice.lib.EventBus;
import com.das.jospablo.littewebservice.lib.GlideImageLoader;
import com.das.jospablo.littewebservice.lib.GreenRobotEventBus;
import com.das.jospablo.littewebservice.webservice.RetroFitService;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static final String EXTRA_USER_ID = "KEY_EXTRA_USER_ID";

    Realm realm;
    GitHubUser user;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        //TODO: Agregar en el content_repository.xml Un RecyclerView, que este pegado al
        // tope del RelativeLayout, y que ocupe todo el espacio disponible que tiene del papa.
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String user_login = getIntent().getExtras().getString(EXTRA_USER_ID);
        realm = OpenRealm.open(this);

        user = realm.where(GitHubUser.class).equalTo("login", user_login).findFirst();

        if (user != null) {

            setRecyclerView(user.getRepositorios());
            user.addChangeListener(new RealmChangeListener<RealmModel>() {
                @Override
                public void onChange(RealmModel element) {
                    if (adapter != null)
                        adapter.notifyDataSetChanged();
                }
            });

            if (user.getRepositorios().size() == 0) {
                Intent service = new Intent(this, AddReposService.class);
                service.putExtra(AddReposService.EXTRA_USER_ID, user.getLogin());
                startService(service);
            }
        }

    }

    private void setRecyclerView(RealmList<Repo> repos) {
        //TODO: Aqui Agregar el LayoutManager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //TODO: Iniciar el Adapter con la lista
        adapter = new Adapter(repos);
        //TODO: Setearselo al reciclerView
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

}
