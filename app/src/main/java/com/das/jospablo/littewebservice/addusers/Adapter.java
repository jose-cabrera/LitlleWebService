package com.das.jospablo.littewebservice.addusers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.das.jospablo.littewebservice.R;
import com.das.jospablo.littewebservice.entity.GitHubUser;
import com.das.jospablo.littewebservice.lib.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by josepablocabreragarcia on 13/06/16.
 * Adapter para el RecyclerView
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    RealmResults<GitHubUser> users;
    private ImageLoader imageLoader;
    private onClick listener;

    public Adapter(RealmResults<GitHubUser> users, ImageLoader imageLoader, onClick listener) {
        this.users = users;
        this.imageLoader = imageLoader;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_githubuser, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GitHubUser user = users.get(position);

        holder.name.setText(user.getName());
        imageLoader.load(holder.avatar, user.getAvatar_url());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(user.getLogin());
            }
        });
    }

    @Override
    public int getItemCount() {

        if (users != null)
            return users.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.name)
        TextView name;

        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

    public interface onClick{
        void onClick(String usuario);
    }

}
