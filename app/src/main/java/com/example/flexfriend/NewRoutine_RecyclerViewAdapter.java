package com.example.flexfriend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// this class will add new cardviews to the recycler of NewRoutineActivity
// the cardview model -> create_routine_model.xml

public class NewRoutine_RecyclerViewAdapter extends RecyclerView.Adapter<MovementVH>{

    ArrayList<String> movementInfo;

    public NewRoutine_RecyclerViewAdapter(ArrayList<String> movementInfo){
        this.movementInfo = movementInfo;

    }
    @NonNull
    @Override
    public MovementVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_routine_model,
                parent, false);
        return new MovementVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovementVH holder, int position) {
//        holder.movementET.setText(items.get(position));
//        holder.setsET.setText(items.get(position));
//        holder.secRepET.setText(items.get(position));

        // initialize the view with view holder
        CheckBox timeCheckBox = holder.timeCheckBox;
        EditText movementET = holder.movementET;
        EditText setsET = holder.setsET;
        EditText secRepET = holder.secRepET;
    }

    @Override
    public int getItemCount() {
        return movementInfo.size();
    }
}

class MovementVH extends RecyclerView.ViewHolder{

    CheckBox timeCheckBox;
    EditText movementET, setsET, secRepET;

    private NewRoutine_RecyclerViewAdapter adapter;

    public MovementVH(@NonNull View itemView) {
        super(itemView);
        timeCheckBox = itemView.findViewById(R.id.timeCheckBox);
        movementET = itemView.findViewById(R.id.movementET);
        setsET = itemView.findViewById(R.id.setsET);
        secRepET = itemView.findViewById(R.id.secRepET);

        itemView.findViewById(R.id.deleteBtn).setOnClickListener(view -> {
            adapter.movementInfo.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public String getCardInfo(){
        String timedBox = String.valueOf(timeCheckBox.isChecked());
        String movement = movementET.getText().toString();
        String sets = setsET.getText().toString();
        String reps = secRepET.getText().toString();

        String cardInfo = timedBox+ " " + movement + " " + sets+ " " + reps;
        return cardInfo;
    }

    public MovementVH linkAdapter(NewRoutine_RecyclerViewAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
