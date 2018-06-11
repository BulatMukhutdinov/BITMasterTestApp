package ru.taximaster.testapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhotoDto implements Parcelable {

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

    public final double latitude;

    public final double longitude;

    private PhotoDto(Parcel in) {
        id = in.readLong();
        owner = in.readString();
        secret = in.readString();
        server = in.readInt();
        farm = in.readInt();
        title = in.readString();
        isPublic = in.readInt();
        isFriend = in.readInt();
        isFamily = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    static final Creator<PhotoDto> CREATOR = new Creator<PhotoDto>() {
        @Override
        public PhotoDto createFromParcel(Parcel in) {
            return new PhotoDto(in);
        }

        @Override
        public PhotoDto[] newArray(int size) {
            return new PhotoDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(owner);
        dest.writeString(secret);
        dest.writeInt(server);
        dest.writeInt(farm);
        dest.writeString(title);
        dest.writeInt(isPublic);
        dest.writeInt(isFriend);
        dest.writeInt(isFamily);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
