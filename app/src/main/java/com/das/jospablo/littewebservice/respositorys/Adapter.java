package com.das.jospablo.littewebservice.respositorys;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.entity.Repo;

import java.util.List;

import butterknife.BindView;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_repository, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo repo = repos.get(position);

        holder.fullName.setText(repo.getFull_name());
        holder.github.setText(repo.getGit_url());
        holder.createdDate.setText(repo.getCreated_at());
        holder.updatedDate.setText(repo.getUpdated_at());
        holder.url.setText(repo.getHtml_url());
    }

    @Override
    public int getItemCount() {

        if (repos != null)
            return repos.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fullName)
        TextView fullName;
        @BindView(R.id.url)
        TextView url;
        @BindView(R.id.github)
        TextView github;
        @BindView(R.id.created_date)
        TextView createdDate;
        @BindView(R.id.updated_date)
        TextView updatedDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
