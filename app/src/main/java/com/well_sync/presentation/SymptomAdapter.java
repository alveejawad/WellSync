package com.well_sync.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.well_sync.R;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder> {
    private String[] symptomsList;

    // Constructor and other methods...
    protected static OnItemClickListener onItemClickListener;
    public SymptomAdapter(String[] symptomsList) {

        this.symptomsList = symptomsList;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public SymptomAdapter.SymptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.symptoms_view, parent, false);
        return new SymptomAdapter.SymptomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SymptomAdapter.SymptomViewHolder holder, int position) {
        String symptom = symptomsList[position];
        holder.nameTextView.setText(symptom);
    }
    @Override
        public int getItemCount () {
            return symptomsList.length;
        }


        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public class SymptomViewHolder extends RecyclerView.ViewHolder {
            TextView nameTextView;
            RatingBar ratingBar;
            public SymptomViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.symptom);
                ratingBar = itemView.findViewById(R.id.rating);
            }
            public int getCurrentRating() {
                return (int)ratingBar.getRating();
            }
        }
    }

