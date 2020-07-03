package com.example.assesment.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CardDataResponse {
    @SerializedName("card_data")
    private List<CardData> cardDataList;
}
