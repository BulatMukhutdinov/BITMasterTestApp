package ru.taximaster.testapp.presentation;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.presentation.module.detailed.DetailedFragment;
import ru.taximaster.testapp.presentation.module.preview.PreviewFragment;

public class MainActivity extends AppCompatActivity implements MainRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new PreviewFragment())
                    .commit();
        }
    }

    @Override
    public void showDetailed(String photo, ImageView image) {
        getSupportFragmentManager().beginTransaction()
                .addSharedElement(image, ViewCompat.getTransitionName(image))
                .replace(R.id.container, DetailedFragment.newInstance(photo, ViewCompat.getTransitionName(image)))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void enableToolbar(boolean enable) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
            getSupportActionBar().setDisplayShowHomeEnabled(enable);
        }

    }

}