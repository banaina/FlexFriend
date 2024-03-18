package com.example.flexfriend.progresspage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageProvider{
    public static List<PictureItem> dataItemList;
    public static Map<String, PictureItem> dataItemMap;

    static {
        //initialize each list that will hold the item and its information
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

//        //add each sensor on device as its own data item with its own specific information
//        int sortPos = 0;
//        if (sensorList != null && sensorList.size() > 0){
//            for (int i = 1; i < sensorList.size(); i++) {
//                addItem(new DataItem(null, sensorList.get(i).getName(), sensorList.get(i).getStringType(), sensorList.get(i).getType()));
//            }
//        }
    }

    //a method to add picture items into each list so that they can be accessed within other classes
    private static void addItem(PictureItem item){
        dataItemList.add(item);
        dataItemMap.put(item.getName(),item);
    }

}
