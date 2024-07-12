// import java.util.Objects;
import java.util.Scanner;

public class User extends UserDB {
    private String name;
    private String email;
    private String password;

    public User(final String NAME, final String EMAIL, final String PASSWORD) {
        this.name = NAME;
        this.email = EMAIL;
        this.password = PASSWORD;
    }

    public static final String inputName() {
        String name = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your name: (LN, FN, MI.)");
            name = sc.nextLine();
        }
        return name;
    }

    public static final String inputEmail() {
        String email = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your email: ");
            email = sc.nextLine();
        }
        return email;
    }

    public static final String inputPassword() {
        String password = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your password: ");
            password = sc.nextLine();
        }
        return password;
    }

    public final void setName(final String NAME) {
        if (NAME.length() < 5 || NAME.length() > 100) {
            System.out.println("Entered name length must be between 5 and 100!");
            return;
        }

        this.name = NAME;
    }

    public final void setEmail(final String EMAIL) {
        if (EMAIL.length() < 5 || EMAIL.length() > 50) {
            System.out.println("Entered email length must be between 5 and 50!");
            return;
        }

        if (!EMAIL.contains("@")) {
            System.out.println("Incorrect email address!");
            return;
        }

        if (userDb.containsKey(EMAIL)) {
            System.out.println("Email address already exists!");
            return;
        }

        this.email = EMAIL;
    }

    public final void setPassword(final String PASSWORD) {
        if (PASSWORD.length() < 5 || PASSWORD.length() > 50) {
            System.out.println("Entered password length must be between 5 and 50!");
            return;
        }

        this.password = PASSWORD;
    }

    public final String getName() {
        return this.name;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getPassword() {
        return this.password;
    }

    @Override
    public final String toString() {
        return "Name = '" + name + '\'' +
                ", Email = '" + email + '\'' +
                ", Password = '" + password + '\'';
    }

    // @Override
    // public final boolean equals(Object o) {
    //     if (this == o) 
    //         return true;

    //     if (o == null || getClass() != o.getClass()) 
    //         return false;

    //     User user = (User) o;
    //     return Objects.equals(email, user.email);
    // }

    // @Override
    // public final int hashCode() {
    //     return Objects.hash(name, email);
    // }
}
