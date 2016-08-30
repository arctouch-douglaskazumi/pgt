package com.dk.pgt;

import android.app.Application;

import com.dk.pgt.data.DaggerDataComponent;
import com.dk.pgt.data.DataComponent;
import com.dk.pgt.data.DataModule;

/**
 * Created by douglaskazumi on 8/20/16.
 */

public class PGTApp extends Application {
    public static final String PGT = "PGT";
    private DataComponent mDataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build();
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }
}
