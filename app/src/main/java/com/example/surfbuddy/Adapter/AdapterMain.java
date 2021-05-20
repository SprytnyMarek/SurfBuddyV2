package com.example.surfbuddy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surfbuddy.R;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.MainViewHolder> {


    String data1[], data2[];
    int images[];
    Context context;
    private OnNoteListener mOnNoteListener;


    public AdapterMain(Context ct, String s1[], String s2[], int img[], OnNoteListener onNoteListener)
    {
        this.context = ct;
        this.data1 = s1;
        this.data2 = s2;
        this.images = img;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MainViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.namesOfInstructors.setText(data1[position]);
        holder.typeOfSurfing.setText(data2[position]);
        holder.profilePictures.setImageResource(images[position]);


    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView namesOfInstructors, typeOfSurfing;
        ImageView profilePictures;
        OnNoteListener onNoteListener;

        public MainViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            namesOfInstructors = itemView.findViewById(R.id.namesOfInstructors);
            typeOfSurfing = itemView.findViewById(R.id.typeOfSurfing);
            profilePictures = itemView.findViewById(R.id.profilePictures);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener
    {
        void onNoteClick(int position);
    }
    
}
