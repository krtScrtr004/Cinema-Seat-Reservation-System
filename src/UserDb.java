import java.util.HashMap;

public class UserDb {
    protected static HashMap<String, User> userDb;

    static {
        userDb = new HashMap<>();
    }

    public static void addUser(final User USER) {
        userDb.put(USER.getEmail(), USER);
    }

//    public static void deleteUser(final User USER) {
//        if (!userDb.containsKey(USER.getEmail())) {
//            System.out.println("User not found!");
//            return;
//        }
//
//        userDb.remove(USER.getEmail());
//    }

    public static User findUser(final String EMAIL) {
        return (userDb.getOrDefault(EMAIL, null));
    }

//    public static void displayAllUsers() {
//        for (Map.Entry<String, User> entry : userDb.entrySet())
//            System.out.println(entry);
//    }
}