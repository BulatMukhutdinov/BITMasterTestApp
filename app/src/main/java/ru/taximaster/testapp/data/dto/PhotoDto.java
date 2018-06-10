package ru.taximaster.testapp.data.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhotoDto {

    public final long id;

    public final String owner;

    public final String secret;

    public final int server;

    public final int farm;

    public final String title;

    @SerializedName("ispublic")
    public final int isPublic;

    @SerializedName("isfriend")
    public final int isFriend;

    @SerializedName("isfamily")
    public final int isFamily;
}
