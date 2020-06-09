package com.example.myshoppingreminder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Content {

    @SerializedName("quotes")
    @Expose
   private ArrayList<DailyQuote> quotes;

    public ArrayList<DailyQuote> getQuotes() {
        return quotes;
    }

    public DailyQuote getFirstQuote() {
        return quotes.get(0);
    }
}
