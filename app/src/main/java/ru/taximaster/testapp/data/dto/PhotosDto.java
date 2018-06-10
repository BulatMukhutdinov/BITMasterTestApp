package ru.taximaster.testapp.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhotosDto {

    public final int page;

    public final int pages;

    @SerializedName("perpage")
    public final int perPage;

    public final int total;

    @SerializedName("photo")
    public final List<PhotoDto> photos;
}
