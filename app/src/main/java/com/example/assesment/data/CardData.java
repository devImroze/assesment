package com.example.assesment.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CardData {

    @SerializedName("content")
    private String content;

    @SerializedName("subtitle")
    private String subTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String imageUrl;
}