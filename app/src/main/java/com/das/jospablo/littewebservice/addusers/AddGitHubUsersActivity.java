package com.das.jospablo.littewebservice.addusers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.addperson.AddPersonFragment;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.entity.Repo;
import com.das.jospablo.littewebservice.events.UserAdded;
import com.das.jospablo.littewebservice.lib.EventBus;
import com.das.jospablo.littewebservice.lib.GreenRobotEventBus;
import com.das.jospablo.littewebservice.webservice.GitHubApi;
import com.das.jospablo.littewebservice.webservice.RetroFitService;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class AddGitHubUsersActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    EventBus eventBus;

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
    }

    private void setRecyclerView(List<Repo> repos){
        //TODO: Aqui Agregar el LayoutManager
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //TODO: Iniciar el Adapter con la lista
        // new Adapter(repos)
        //TODO: Setearselo al reciclerView
        //reciclerview.setLayoutManager
        //reciclerview.setAdpter;
    }

    @OnClick(R.id.fab)
    public void onClick() {
        new AddPersonFragment().show(getSupportFragmentManager(), getString(R.string.addPerson_title));
    }

    @Subscribe
    public void onEvent(UserAdded event){

        //TODO: Agregar usuarios a la base de Datos

        Call<GitHubUser> userCall = RetroFitService.getInstance().getUserInfo(event.getUser());

    }

}
