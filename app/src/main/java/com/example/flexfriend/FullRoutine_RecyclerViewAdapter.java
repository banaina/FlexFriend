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
    ArrayList<String> movements;
    public FullRoutine_RecyclerViewAdapter(Context context, ArrayList<String> movements){
        this.context = context;
        this.movements = movements;
    }
    @NonNull
    @Override
    public FullRoutineVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_routine_model, parent, false);
        FullRoutineVH viewHolder = new FullRoutineVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FullRoutine_RecyclerViewAdapter.FullRoutineVH holder, int position) {
        String[] results = (movements.get(position).toString()).split(",");
        holder.movementNameTV.setText(results[0]);
        holder.repsTV.setText(results[1]);
        holder.setsTV.setText(results[2]);

    }

    @Override
    public int getItemCount() {
        return movements.size();
    }
    public static class FullRoutineVH extends RecyclerView.ViewHolder{
        TextView movementNameTV, repsTV, setsTV;
        Context context;

        public FullRoutineVH(@NonNull View itemView){
            super(itemView);

            movementNameTV = itemView.findViewById(R.id.movementName);
            repsTV = itemView.findViewById(R.id.repOrsec);
            setsTV = itemView.findViewById(R.id.sets);
            context = itemView.getContext();
        }
    }
}
