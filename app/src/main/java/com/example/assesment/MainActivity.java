package com.example.assesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.assesment.adapters.CardViewAdapter;
import com.example.assesment.data.CardData;
import com.example.assesment.data.CardDataResponse;
import com.example.assesment.networking.ApiClient;
import com.example.assesment.networking.ApiService;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements CardViewAdapter.OnCardClickListener {

    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Covid News");

        recyclerView = findViewById(R.id.cardList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add(apiService.getCardData("NEEDKEYHERE")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayData));
    }

    private void displayData(CardDataResponse data) {
        CardViewAdapter cardViewAdapter = new CardViewAdapter(this);
        cardViewAdapter.setCardDataList(data.getCardDataList());
        recyclerView.setAdapter(cardViewAdapter);
        cardViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(CardData cardData, ImageView imageView) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(getString(R.string.image), cardData.getImageUrl());
        intent.putExtra(getString(R.string.content), cardData.getContent());
        intent.putExtra(getString(R.string.title), cardData.getTitle());

        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                imageView,
                Objects.requireNonNull(ViewCompat.getTransitionName(imageView)));

        startActivity(intent, option.toBundle());
    }
}