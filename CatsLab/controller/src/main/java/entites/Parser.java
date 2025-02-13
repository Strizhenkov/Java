package entites;

public class Parser {
    public Cat ReadCat(String data) {
        String[] splitedData = data.split(" ");
        return new Cat(splitedData[1], Integer.parseInt(splitedData[2]), splitedData[3], Integer.parseInt(splitedData[4]), splitedData[5]);
    }

    public User ReadUser(String data) {
        String[] splitedData = data.split(" ");
        return new User(splitedData[1],  splitedData[2]);
    }
}
