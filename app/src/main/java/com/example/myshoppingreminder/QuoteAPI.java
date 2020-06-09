package com.example.myshoppingreminder;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteAPI {

    @GET("/qod.json")
    Call<QuoteResponse> getDailyQuote();
}
