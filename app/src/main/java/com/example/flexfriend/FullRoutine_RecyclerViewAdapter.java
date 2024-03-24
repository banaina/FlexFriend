package com.example.flexfriend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FullRoutine_RecyclerViewAdapter extends RecyclerView.Adapter<FullRoutine_RecyclerViewAdapter.FullRoutineVH> {

    Context context;
    ArrayList<String[]> movements;
    public FullRoutine_RecyclerViewAdapter(Context context, ArrayList<String[]> movements){
        this.context = context;
        this.movements = movements;
    }
    @NonNull
    @Override
    public FullRoutineVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_desc_model, parent, false);
        return new FullRoutine_RecyclerViewAdapter.FullRoutineVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullRoutine_RecyclerViewAdapter.FullRoutineVH holder, int position) {
        TextView movementNameTV = holder.movementNameTV;
        TextView repsTV = holder.repsTV;
        TextView setsTV = holder.setsTV;

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class FullRoutineVH extends RecyclerView.ViewHolder{
        TextView movementNameTV, repsTV, setsTV;
        Context context;

        public FullRoutineVH(@NonNull View itemView){
            super(itemView);

            movementNameTV = itemView.findViewById(R.id.movementNameTV);
            repsTV = itemView.findViewById(R.id.repsTV);
            setsTV = itemView.findViewById(R.id.setsTV);
            context = itemView.getContext();
        }
    }
}
