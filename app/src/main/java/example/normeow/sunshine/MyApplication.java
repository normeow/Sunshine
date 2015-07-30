package example.normeow.sunshine;

import android.app.Application;

/**
 * Created by Admin on 30.07.2015.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    public  static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
