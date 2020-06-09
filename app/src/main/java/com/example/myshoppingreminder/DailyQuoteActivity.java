package com.example.myshoppingreminder;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DailyQuoteActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewText;
    private TextView textViewAuthor;
    private TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quote);

       textViewTitle = findViewById(R.id.title);
       textViewText = findViewById(R.id.quote);
       textViewAuthor = findViewById(R.id.author);
       textViewDate = findViewById(R.id.quoteDate);

        getDailyQuote();
    }
    private void getDailyQuote() {
        Retrofit retrofit = ServiceGenerator.getRetrofitBuilder();
        QuoteAPI quoteAPI = retrofit.create(QuoteAPI.class);
        Call call = quoteAPI.getDailyQuote();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null) {
                    QuoteResponse quoteResponse = (QuoteResponse) response.body();

                    String message;
                    message = quoteResponse.getContent().getFirstQuote().getTitle();
                    textViewTitle.setText(message);

                    message = quoteResponse.getContent().getFirstQuote().getQuote();
                    textViewText.setText(message);

                    message = quoteResponse.getContent().getFirstQuote().getAuthor();
                    textViewAuthor.setText(message);

                    message = quoteResponse.getContent().getFirstQuote().getDate();
                    textViewDate.setText(message);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                textViewTitle.setText(t.getMessage());
                textViewText.setText(t.getMessage());
                textViewAuthor.setText(t.getMessage());
                textViewDate.setText(t.getMessage());
                Log.e(" error_", t.getMessage());
            }
        });


    }
}
