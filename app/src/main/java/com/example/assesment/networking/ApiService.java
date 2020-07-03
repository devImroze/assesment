package com.example.assesment.networking;

import com.example.assesment.data.CardDataResponse;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/v1/test/route/animation")
    Observable<CardDataResponse> getCardData(@Header("x-api-key") String key);
}
