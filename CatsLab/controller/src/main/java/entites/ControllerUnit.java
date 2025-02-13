package entites;

import org.example.DataBaseUnit;
import org.example.DataStructure;
import org.example.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Objects;

public class ControllerUnit {
    private String Command;

    public ControllerUnit(String command) {
        Command = command;
    }
    public void LoadCommand(String command) {
        Command = command;
    }
    public String Run() {
        String passwordSalt = "$2a$10$rBB8jC6vfrtPg0aOyHgSye";
        ArrayList<Cat> cats = new ArrayList<Cat>();
        ArrayList<User> users = new ArrayList<User>();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataBaseUnit dataBaseUnit = (DataBaseUnit) context.getBean("dataBaseUnit");
        DataStructure data = new DataStructure();
        data.LoadCatData(dataBaseUnit.ConnectAndGetCatData());
        data.LoadUserData(dataBaseUnit.ConnectAndGetUserData());
        Parser parser = new Parser();
        for (String catLine : data.GetCatData()) {
            cats.add(parser.ReadCat(catLine));
        }
        for (String userLine : data.GetUserData()) {
            users.add(parser.ReadUser(userLine));
        }
        if (Objects.equals(Command, "GET All Admin")) {
            StringBuilder answer = new StringBuilder();
            answer.append("Users:\n");
            for (User user : users) {
                answer.append(user.StringData()).append('\n');
            }
            answer.append("Cats:\n");
            for (Cat cat : cats) {
                answer.append(cat.StringData()).append('\n');
            }
            return answer.toString();
        } else {
            String[] splited = Command.split(" ");
            String catName = "";
            String name = splited[2];
            String hash = splited[3];
            User currentUser = new User("Null", "0");
            for (User user : users) {
                if (Objects.equals(name, user.GetName())) {
                    currentUser = user;
                }
            }

            catName = cats.get(currentUser.GetCatsId().get(0) - 1).GetName();

            if (BCrypt.hashpw(name + catName, passwordSalt).equals(hash)) {
                StringBuilder answer = new StringBuilder();
                answer.append("User:\n");
                answer.append(currentUser.StringData()).append('\n');
                answer.append("Cats:\n");
                for (Integer number : currentUser.GetCatsId()) {
                    answer.append(cats.get(number - 1).StringData()).append('\n');
                }
                return answer.toString();
            } else {
                return "Wrong Password";
            }
        }
    }
}
