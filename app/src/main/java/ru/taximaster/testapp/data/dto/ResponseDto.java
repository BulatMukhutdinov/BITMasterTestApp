package ru.taximaster.testapp.data.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDto {

    public final PhotosDto photos;

    public final String stat;
}