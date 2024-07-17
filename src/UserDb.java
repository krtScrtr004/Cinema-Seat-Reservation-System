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

    public static void updateUser(User user) {
        User tempUser = userDb.get(user.getEmail());
        if (tempUser == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("What info you want to update?");
        System.out.println("[1] Name");
        System.out.println("[2] Email");
        System.out.println("[3] Password");
        boolean isValid = true;
        int choice;
        do {
            System.out.print("Enter option index: ");
            choice = InputReader.readInt();
            if (choice < 1 || choice > 3) {
                System.err.println("Option index length must be within the range of 1 to 3 only.");
                isValid = false;
            }
        } while (!isValid);

        switch (choice) {
            case 1: {
                final String newName = User.inputName();
                user.setName(newName);
                break;
            }

            case 2: {
                final String newEmail = User.inputEmail();
                user.setEmail(newEmail);
                break;
            }

            case 3: {
                final String newPassword = User.inputPassword();
                user.setPassword(newPassword);
                break;
            }
        }
    }

    public static User findUser(final String EMAIL) {
        return (userDb.getOrDefault(EMAIL, null));
    }

//    public static void displayAllUsers() {
//        for (Map.Entry<String, User> entry : userDb.entrySet())
//            System.out.println(entry);
//    }
}