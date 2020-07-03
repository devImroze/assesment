package com.example.assesment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assesment.R;
import com.example.assesment.data.CardData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private List<CardData> cardDataList;
    private OnCardClickListener onCardClickListener;

    public CardViewAdapter(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    public void setCardDataList(List<CardData> cardDataList) {
        this.cardDataList = cardDataList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder, parent, false);

        return new CardViewHolder(view, onCardClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardData currentCardData = cardDataList.get(position);
        holder.title.setText(currentCardData.getTitle());
        holder.subTitle.setText(currentCardData.getSubTitle());
        Picasso.get().load(currentCardData.getImageUrl()).fit().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return cardDataList == null? 0:cardDataList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView subTitle;
        private ImageView image;

        private OnCardClickListener onCardClickListener;

        public CardViewHolder(View itemView, OnCardClickListener onCardClickListener) {
            super(itemView);

            title = itemView.findViewById(R.id.titleMain);
            subTitle = itemView.findViewById(R.id.subTitle);
            image = itemView.findViewById(R.id.contentImage);

            this.onCardClickListener = onCardClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCardClickListener.onCardClick(cardDataList.get(getAdapterPosition()), image);
        }
    }

    public interface OnCardClickListener{
        void onCardClick(CardData cardData, ImageView imageView);
    }
}
