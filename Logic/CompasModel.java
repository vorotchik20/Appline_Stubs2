package ru.appline.Logic;

import java.util.HashMap;
import java.util.Map;

/*
{
    "North": "337-21",
    "North-East": "22-66",
    "East":  "67-111",
    "East-South":  "112-156",
    "South": "157-202",
    "South-West": "203-246",
    "West": "247-291",
    "West-North": "292-337"
}
 */

public class CompasModel {
    private static final CompasModel instance = new CompasModel();
    private Map<String, String> model;

    public CompasModel() {
        model = new HashMap<>();
    }

    public static CompasModel getInstance() {
        return instance;
    }

    public Map<String, String> getModel(){
        return model;
    }

    public void create(Map<String, String> model) {
        this.model = model;
    }

    public String getDegreeBySide(String side) {
        return model.get(side);
    }

    public String getSideByDegree(Integer degree) {
        int from;
        int to;
        for (String key : model.keySet()) {
            from = getFrom(model.get(key));
            to = getTo(model.get(key));
            if (degree <= to && degree >= from) {
                return key;
            }
        }
        return "Сторона не найдена.";
    }

    private int getTo(String s) {
        char[] arr = s.toCharArray();
        StringBuilder result = new StringBuilder();
        if (arr[arr.length - 2] == '-') { //цифра
            result.append(arr[arr.length - 1]);
            return Integer.parseInt(result.toString());
        } else if (arr[arr.length - 3] == '-') { //двухзначное число
            result.append(arr[arr.length - 2]);
            result.append(arr[arr.length - 1]);
            return Integer.parseInt(result.toString());
        } else if (arr[arr.length - 4] == '-') { //трёхзначное
            result.append(arr[arr.length - 3]);
            result.append(arr[arr.length - 2]);
            result.append(arr[arr.length - 1]);
            return Integer.parseInt(result.toString());
        }
        return -1;
    }

    private int getFrom(String s) {
        char[] arr = s.toCharArray();
        StringBuilder result = new StringBuilder();
        if (arr[1] == '-'){ //цифра
            result.append(arr[0]);
            return Integer.parseInt(result.toString());
        }else if(arr[2] == '-'){ //двухзначное число
            result.append(arr[0]);
            result.append(arr[1]);
            return Integer.parseInt(result.toString());
        }else if(arr[3] == '-'){ //трёхзначное
            result.append(arr[0]);
            result.append(arr[1]);
            result.append(arr[2]);
            return Integer.parseInt(result.toString());
        }
        return -1;
    }
}
