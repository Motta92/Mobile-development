package com.motty.motz.concentrationgamelab;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Carlos on 3/6/2016.
 */
public class colorHandler {
    private HashMap<Integer, Integer> colorMap;
    private int[] colors = {Color.RED,Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN,Color.GRAY,Color.MAGENTA,Color.YELLOW};

    colorHandler(){
        this.colorMap = new HashMap<Integer, Integer>();
    }

    public void addColor(int key){
        colorMap.put(key, colors[key%8]);
    }

    public int getCorrespondingColor(int key){
        return colorMap.get(key);
    }

    public void setColorMap(HashMap<Integer, Integer> map){
        this.colorMap = map;
    }

    public HashMap<Integer, Integer> getColor(){
        return colorMap;
    }
}
