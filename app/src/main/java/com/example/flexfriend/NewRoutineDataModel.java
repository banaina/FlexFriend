package com.example.flexfriend;

public class NewRoutineDataModel {

    // this is what is in the cardview of creating a movement
    String movementName;
    int set, rep, cardviewId;
    boolean time;

    public NewRoutineDataModel(int cardviewId, String movementName, int set, int rep, boolean time){
        this.cardviewId = cardviewId; // this will tell the position of the card in the arraylist
        this.movementName = movementName;
        this.set = set;
        this.rep = rep;
        this.time = time;
    }

    public int getCardViewId() {return cardviewId;}
    public int getRep() {return rep;}
    public int getSet(){return set;}
    public String getMovementName(){return movementName;}
    public boolean isTime() {return time;}

}
