package ru.taximaster.testapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDto implements Parcelable {

    public final PhotosDto photos;

    public final String stat;

    protected ResponseDto(Parcel in) {
        photos = in.readParcelable(PhotosDto.class.getClassLoader());
        stat = in.readString();
    }

    public static final Creator<ResponseDto> CREATOR = new Creator<ResponseDto>() {
        @Override
        public ResponseDto createFromParcel(Parcel in) {
            return new ResponseDto(in);
        }

        @Override
        public ResponseDto[] newArray(int size) {
            return new ResponseDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(photos, flags);
        dest.writeString(stat);
    }
}