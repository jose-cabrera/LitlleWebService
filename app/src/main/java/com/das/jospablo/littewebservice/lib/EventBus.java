package com.das.jospablo.littewebservice.lib;

/**
 * Created by JoséPablo on 09/06/16.
 */
public interface EventBus {

    void register(Object subscriber);
    void unregistrer(Object subscriber);
    void post(Object event);

}
