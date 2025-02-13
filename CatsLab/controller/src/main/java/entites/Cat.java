package entites;

import java.util.ArrayList;

public class Cat {
    private String Name;
    private int Date;
    private String Breed;
    private int OwnerId;
    private ArrayList<Integer> FriendCats = new ArrayList<>();
    public Cat(String name, int date, String breed, int ownerId, String friendIds) {
        Name = name;
        Date = date;
        Breed = breed;
        OwnerId = ownerId;
        String[] splitedFriends = friendIds.split(",");
        for (String splitedFriend : splitedFriends) {
            FriendCats.add(Integer.parseInt(splitedFriend));
        }
    }
    public String StringData() {
        return Name + " " + Date + " " + Breed + " " + OwnerId + " " + FriendCats;
    }

    public String GetName() {
        return Name;
    }
}
