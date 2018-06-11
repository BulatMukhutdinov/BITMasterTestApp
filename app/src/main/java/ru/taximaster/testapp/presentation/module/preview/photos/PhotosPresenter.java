package ru.taximaster.testapp.presentation.module.preview.photos;

import ru.taximaster.testapp.presentation.module.base.Presenter;
import ru.taximaster.testapp.presentation.util.OnPhotoClickListener;

public interface PhotosPresenter extends Presenter, OnPhotoClickListener {

    void onStart(int page);

    void onLoadError(Throwable throwable);

    void mapMenuClicked();

    void onResume();
}