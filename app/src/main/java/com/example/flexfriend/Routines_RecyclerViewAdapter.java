package com.example.flexfriend;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Routines_RecyclerViewAdapter extends RecyclerView.Adapter<Routines_RecyclerViewAdapter.RoutinesCategoryVH>{

    Context context;
    ArrayList<String> routineNames;

    public Routines_RecyclerViewAdapter(Context context, ArrayList<String> routineNames){
        this.context = context;
        this.routineNames = routineNames;
    }

    @NonNull
    @Override
    public RoutinesCategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is where the layout is inflated and gives the look to the rows
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_model_row, parent, false);
        //RoutinesCategoryVH routinesCategoryVH = new RoutinesCategoryVH(view);
        return new Routines_RecyclerViewAdapter.RoutinesCategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutinesCategoryVH holder, int position) {
        // assigning values to each of the row based on the position of the recycler view
        holder.routineName.setText(routineNames.get(position));
    }

    @Override
    public int getItemCount() {
        return routineNames.size();
    }

    public static class RoutinesCategoryVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView routineName;
        Context context;

        public RoutinesCategoryVH(@NonNull View itemView) {
            super(itemView);

            routineName = itemView.findViewById(R.id.routineNameTV);
            itemView.setOnClickListener(this);
            context = itemView.getContext();
        }


        // when user clicks one of the routines, go to the class that runs the routine (FullRoutineActivity.java)
        // send the position of the routineName as a value
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "you have clicked " + routineName.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent (v.getContext(), FullRoutineActivity.class);
            Log.d("adapterPos", String.valueOf(getAdapterPosition()));
            intent.putExtra("ROUTINE NAME", routineName.getText().toString());
            v.getContext().startActivity(intent);
        }
    }
}
