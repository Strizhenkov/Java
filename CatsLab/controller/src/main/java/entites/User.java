package entites;

import java.util.ArrayList;

public class User {
    private String Name;
    private ArrayList<Integer> Cats = new ArrayList<>();
    public User(String name, String catsId) {
        Name = name;
        String[] splitedFriends = catsId.split(",");
        for (String splitedFriend : splitedFriends) {
            Cats.add(Integer.parseInt(splitedFriend));
        }
    }
    public String StringData() {
        return Name + " " + Cats;
    }

    public String GetName() {
        return Name;
    }

    public ArrayList<Integer> GetCatsId() {
        return Cats;
    }
}
