package com.example.hotel_booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Hotel> hotelArrayList;
    private OnHotelItemListener monHotelItemListener;
    public MyAdapter(Context context, ArrayList<Hotel> hotelArrayList, OnHotelItemListener onHotelItemListener) {
        this.monHotelItemListener = onHotelItemListener;
        this.context = context;
        this.hotelArrayList = hotelArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false);
        return new MyViewHolder(v,monHotelItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Hotel hotel = hotelArrayList.get(position);

        holder.hotelName.setText(hotel.hotelName);


    }

    @Override
    public int getItemCount() {
        return hotelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView hotelName;
        OnHotelItemListener onHotelItemListener;

        public MyViewHolder(@NonNull View itemView, OnHotelItemListener onHotelItemListener) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.tvhotelName);
            this.onHotelItemListener = onHotelItemListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onHotelItemListener.onHotelClick(getAdapterPosition());
        }
    }
    public interface OnHotelItemListener{
        void onHotelClick(int position);
    }
}
