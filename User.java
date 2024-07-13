import java.util.Objects;

public class User extends UserDB {
    private String name;
    private String email;
    private String password;

    public User() {
        this.name = null;
        this.email = null;
        this.password = null;
    }

    public User(final String NAME, final String EMAIL, final String PASSWORD) {
        this.name = NAME;
        this.email = EMAIL;
        this.password = PASSWORD;
    }

    public static final String inputName() {
        System.out.print("Enter your name: (LN, FN, MI.)");
        return InputReader.readString();
    }

    public static final String inputEmail() {
        System.out.print("Enter your email: ");
        return InputReader.readString();
    }

    public static final String inputPassword() {
        System.out.print("Enter your password: ");
        return InputReader.readString();
    }

    public final Boolean setName(final String NAME) {
        if (NAME.length() < 5 || NAME.length() > 100) {
            System.out.println("Entered name length must be between 5 and 100!");
            return false;
        }

        this.name = NAME;
        return true;
    }

    public final Boolean setEmail(final String EMAIL) {
        if (EMAIL.length() < 5 || EMAIL.length() > 50) {
            System.out.println("Entered email length must be between 5 and 50!");
            return false;
        }

        if (!EMAIL.contains("@")) {
            System.out.println("Incorrect email address!");
            return false;
        }

        if (findUser(EMAIL) != null) {
            System.out.println("Email address already exists!");
            return false;
        }

        this.email = EMAIL;
        return true;
    }

    public final Boolean setPassword(final String PASSWORD) {
        if (PASSWORD.length() < 5 || PASSWORD.length() > 50) {
            System.out.println("Entered password length must be between 5 and 50!");
            return false;
        }

        this.password = PASSWORD;
        return true;
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) 
            return true;

        if (o == null || getClass() != o.getClass()) 
            return false;

        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(email);
    }
}
