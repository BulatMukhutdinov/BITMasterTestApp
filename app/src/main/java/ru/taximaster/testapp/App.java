package ru.taximaster.testapp;

import android.app.Application;

import lombok.Getter;
import lombok.Setter;
import ru.taximaster.testapp.data.network.FlickrApi;
import ru.taximaster.testapp.presentation.util.SearchBus;

public class App extends Application {

    @Getter
    private static App instance;

    @Getter
    private SearchBus bus;

    @Getter
    @Setter
    private String searchText;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FlickrApi.init(getString(R.string.server_url));
        bus = new SearchBus();
    }
}
