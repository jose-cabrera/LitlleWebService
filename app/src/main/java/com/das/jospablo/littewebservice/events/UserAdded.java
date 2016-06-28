package com.das.jospablo.littewebservice.events;

/**
 * Created by JoséPablo on 27/06/16.
 * Evento a enviar
 */
public class UserAdded {

    String user;

    public UserAdded(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
