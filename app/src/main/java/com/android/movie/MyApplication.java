package com.android.movie;

import android.app.Application;

import com.android.movie.di.components.ApplicationComponent;
import com.android.movie.di.components.DaggerApplicationComponent;
import com.android.movie.di.modules.ApplicationModule;


/**
 * Created by bhavesh on 20/03/19.
 */

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;

      public MyApplication(){

      }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                                .builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();

    }

    public ApplicationComponent getApplicationComponent(){
          return applicationComponent;
      }

}
