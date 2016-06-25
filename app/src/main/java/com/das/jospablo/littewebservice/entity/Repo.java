package com.das.jospablo.littewebservice.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JoséPablo on 24/06/16.
 * Repo POJO
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
