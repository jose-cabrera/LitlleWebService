package com.das.jospablo.littewebservice.respositorys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.addperson.AddPersonFragment;
import com.das.jospablo.littewebservice.entity.Repo;
import com.das.jospablo.littewebservice.events.UserAdded;
import com.das.jospablo.littewebservice.lib.EventBus;
import com.das.jospablo.littewebservice.lib.GreenRobotEventBus;
import com.das.jospablo.littewebservice.webservice.RetroFitService;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        //TODO: Agregar en el content_repository.xml Un RecyclerView, que este pegado al
        // tope del RelativeLayout, y que ocupe todo el espacio disponible que tiene del papa.
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventBus = GreenRobotEventBus.getInstance();
        eventBus.register(this);
    }

    private void setRecyclerView(List<Repo> repos) {
        //TODO: Aqui Agregar el LayoutManager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //TODO: Iniciar el Adapter con la lista
        Adapter adapter = new Adapter(repos);
        //TODO: Setearselo al reciclerView
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        new AddPersonFragment().show(getSupportFragmentManager(), getString(R.string.addPerson_title));
    }

    @Subscribe
    public void onEvent(UserAdded event) {

        //TODO: Aqui deben de leer la lista de usuarios que esta en los shareprefs y ejecutar el servicio de RetroFit, una vez este

        Call<List<Repo>> call = RetroFitService.getInstance().listRepos(event.getUser());
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                try{

                    List<Repo> repos = response.body();

                    setRecyclerView(repos);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
