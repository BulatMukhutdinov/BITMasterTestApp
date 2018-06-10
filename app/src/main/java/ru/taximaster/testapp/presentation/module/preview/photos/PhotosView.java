package ru.taximaster.testapp.presentation.module.preview.photos;

import android.support.annotation.StringRes;

import java.util.List;

import ru.taximaster.testapp.presentation.module.base.View;

public interface PhotosView extends View {

    String getString(@StringRes int stringId);

    void showPictures(List<String> photos);

    void showError();
}