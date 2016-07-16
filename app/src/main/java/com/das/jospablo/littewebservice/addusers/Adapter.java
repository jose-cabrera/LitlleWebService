package com.das.jospablo.littewebservice.addusers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.entity.Repo;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by josepablocabreragarcia on 13/06/16.
 * Adapter para el RecyclerView
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Repo> repos;

    public Adapter(List<Repo> repoList) {
        this.repos = repoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO: Agregar en la vista, lo siguiente:
        // TextView para el name
        // TextView para el full_name
        // TextView para el URL

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_repository, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //TODO: Obtener el objeto de la lista en la position obtenida, y del objeto Repo llenar
        // El Holder con cada uno de sus atribuos.
    }

    @Override
    public int getItemCount() {

        //TODO: Cambiar para retornar el tamaño de la lista

        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //TODO: Aqui Agregar las injecciones de ButterKnife de forma manual, despues de agregar las vistas en
        // R.layout.item_content_repository

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
