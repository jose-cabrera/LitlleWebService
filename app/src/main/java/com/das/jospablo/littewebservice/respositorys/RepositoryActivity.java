package com.das.jospablo.littewebservice.respositorys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.addperson.AddPersonFragment;
import com.das.jospablo.littewebservice.events.UserAdded;
import com.das.jospablo.littewebservice.lib.EventBus;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepositoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

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

        eventBus.register(this);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        new AddPersonFragment().show(getSupportFragmentManager(), getString(R.string.addPerson_title));
    }

    @Subscribe
    public void onEvent(UserAdded event){

        //TODO: Aqui deben de leer la lista de usuarios que esta en los shareprefs y ejecutar el servicio de RetroFit, una vez este


    }

}
