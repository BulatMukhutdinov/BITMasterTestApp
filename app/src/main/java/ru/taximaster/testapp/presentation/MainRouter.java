package ru.taximaster.testapp.presentation;

import android.widget.ImageView;

import ru.taximaster.testapp.presentation.module.base.Router;

public interface MainRouter extends Router {

    void showDetailed(String photo, ImageView image);

    void enableToolbar(boolean enable);
}