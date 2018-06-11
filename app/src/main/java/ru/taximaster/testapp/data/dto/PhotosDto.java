package ru.taximaster.testapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhotosDto implements Parcelable {

    public final int page;

    public final int pages;

    @SerializedName("perpage")
    public final int perPage;

    public final int total;

    @SerializedName("photo")
    public final List<PhotoDto> photos;

    private PhotosDto(Parcel in) {
        page = in.readInt();
        pages = in.readInt();
        perPage = in.readInt();
        total = in.readInt();
        photos = in.createTypedArrayList(PhotoDto.CREATOR);
    }

    public static final Creator<PhotosDto> CREATOR = new Creator<PhotosDto>() {
        @Override
        public PhotosDto createFromParcel(Parcel in) {
            return new PhotosDto(in);
        }

        @Override
        public PhotosDto[] newArray(int size) {
            return new PhotosDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(pages);
        dest.writeInt(perPage);
        dest.writeInt(total);
        dest.writeTypedList(photos);
    }
}
