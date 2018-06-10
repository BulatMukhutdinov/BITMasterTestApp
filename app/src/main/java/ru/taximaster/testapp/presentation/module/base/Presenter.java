package ru.taximaster.testapp.presentation.module.base;

public interface Presenter {

    void setView(View v);

    void onStop();
}