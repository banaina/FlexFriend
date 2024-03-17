package com.example.flexfriend;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// this class will add new cardviews to the recycler of NewRoutineActivity
// the cardview model -> create_routine_model.xml

public class NewRoutine_RecyclerViewAdapter extends RecyclerView.Adapter<MovementVH>{

    Context context;
    ArrayList<NewRoutineDataModel> movementInfo;
    //ArrayList<String> movementInfo;

    ArrayList<String> movementNameArray = new ArrayList<>();
    ArrayList<Integer> setsArray = new ArrayList<>();
    ArrayList<Integer> repsArray = new ArrayList<>();
    ArrayList<Boolean> timeArray = new ArrayList<>();

    boolean isOnTextChangedMovement = false;
    boolean isOnTextChangedSets = false;
    boolean isOnTextChangedReps = false;


    public NewRoutine_RecyclerViewAdapter(ArrayList<NewRoutineDataModel> movementInfo){
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

        int id = movementInfo.get(position).getCardViewId();

        movementET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChangedMovement = true;

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isOnTextChangedMovement) {
                    isOnTextChangedMovement = false;

                    try {

                        for (int i = 0; i < id; i++) {
                            if(i!=id){
                                movementNameArray.add("");
                            }
                            else{
                                movementNameArray.add("");
                                movementNameArray.set(id,s.toString());

                            }
                        }

                    }catch (NumberFormatException e){

                    }
                }
                Log.d("movementNameArray: ", movementNameArray.toString());

            }
        });

        setsET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChangedSets = true;

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isOnTextChangedSets) {
                    isOnTextChangedSets = false;
                }

            }
        });

        secRepET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChangedReps = true;

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isOnTextChangedReps) {
                    isOnTextChangedReps = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movementInfo.size();
    }
}

//the recyclerview viewholder
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

    public MovementVH linkAdapter(NewRoutine_RecyclerViewAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
