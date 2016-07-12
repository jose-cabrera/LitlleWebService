package com.das.jospablo.littewebservice.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JoséPablo on 24/06/16.
 * Repo Plain Old Java Object POJO
 */
public class Repo {

    private int id;
    private String name;
    private String full_name;
    @SerializedName("private")
    private boolean bPrivate;
    private String html_url;
    private String description;
    private boolean fork;
    private String url;
    private String created_at;
    private String updated_at;
    private String git_url;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isbPrivate() {
        return bPrivate;
    }

    public void setbPrivate(boolean bPrivate) {
        this.bPrivate = bPrivate;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
