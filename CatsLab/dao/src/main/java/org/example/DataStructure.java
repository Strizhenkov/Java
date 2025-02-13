package org.example;

import java.util.ArrayList;

public class DataStructure {
    private ArrayList<String> CatData, UserData;
    public DataStructure(){
    }

    public void LoadCatData(ArrayList<String> catData) {
        CatData = catData;
    }

    public void LoadUserData(ArrayList<String> userData) {
        UserData = userData;
    }

    public ArrayList<String> GetCatData() {
        return CatData;
    }

    public ArrayList<String> GetUserData() {
        return UserData;
    }
}
