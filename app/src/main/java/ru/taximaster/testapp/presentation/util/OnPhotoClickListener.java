package ru.taximaster.testapp.presentation.util;

import android.widget.ImageView;

import ru.taximaster.testapp.data.dto.PhotoDto;

public interface OnPhotoClickListener {

    void onClick(String photo, ImageView image);
}