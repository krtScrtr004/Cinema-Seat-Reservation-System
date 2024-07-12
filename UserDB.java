import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserDB {
    protected static HashMap<String, User> userDb = new HashMap<>();

    public static final void addUser(final User USER) {
        userDb.put(USER.getEmail(), USER);
    }

    public static final void deleteUser(final User USER) {
        if (!userDb.containsKey(USER.getEmail())) {
            System.out.println("User not found!");
            return;
        }

        userDb.remove(USER.getEmail());
    }

    public static final void updateUser(User user) {
        User tempUser = userDb.get(user.getEmail());
        if (tempUser == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("What info you want to update?");
        System.out.println("[1] Name");
        System.out.println("[2] Email");
        System.out.println("[3] Password");

        int choice = 0;
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.print("Enter here: ");
                choice = sc.nextInt();
            } while (choice < 1 || choice > 3);
            sc.nextLine();
        }

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

    public static final User findUser(final String EMAIL) {
        if (!userDb.containsKey(EMAIL)) {
            System.out.println("User not found!");
            return null;
        }

        return userDb.get(EMAIL);
    }

    public static final void displayAllUsers() {
        for (Map.Entry<String, User> entry : userDb.entrySet()) 
            System.out.println(entry);
    }
}
