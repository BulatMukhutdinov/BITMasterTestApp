package ru.taximaster.testapp.presentation;

import android.widget.ImageView;

import ru.taximaster.testapp.data.dto.ResponseDto;
import ru.taximaster.testapp.presentation.module.base.Router;

public interface MainRouter extends Router {

    void showDetailed(String photo, ImageView image);

    void showPreview();

    void showMap(ResponseDto responseDto);

    void enableToolbar(boolean enable);
}