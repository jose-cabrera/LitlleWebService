package com.das.jospablo.littewebservice.lib;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by josepablocabreragarcia on 20/11/15.
 * Clase maestra para IntentServices
 */
public abstract class IntentServiceMaster extends IntentService {

    public static final String NAME = "INTENTSERVICE_MASTER_VISITAS";
    protected static String TAG;

    public IntentServiceMaster() {
        super(NAME);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceMaster(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ejecutarAccion(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = getClass().toString();
    }

    public abstract void ejecutarAccion(Intent intent);
}