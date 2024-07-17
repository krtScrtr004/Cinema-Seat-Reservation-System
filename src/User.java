import java.util.Objects;

public class User extends UserDb {
    private String name;
    private String email;
    private String password;
    private Reservation reservation;

    public User() {
        this.name = null;
        this.email = null;
        this.password = null;
        this.reservation = null;
    }

//    public User(final String NAME, final String EMAIL, final String PASSWORD, final Reservation RESERVATION) {
//        this.name = NAME;
//        this.email = EMAIL;
//        this.password = PASSWORD;
//        this.reservation = RESERVATION;
//    }

    public static String inputName() {
        System.out.print("Enter your name: (LN, FN, MI.) ");
        return InputReader.readString();
    }

    public static String inputEmail() {
        System.out.print("Enter your email: ");
        return InputReader.readString();
    }

    public static String inputPassword() {
        System.out.print("Enter your password: ");
        return InputReader.readString();
    }

    public final Boolean setName(final String NAME) {
        if (NAME.length() < 5 || NAME.length() > 100) {
            System.err.println("Name length must be within the range of 5 to 100 only.");
            return false;
        }

        this.name = NAME;
        return true;
    }

    public final Boolean setEmail(final String EMAIL) {
        if (EMAIL.length() < 5 || EMAIL.length() > 50) {
            System.err.println("Email length must be within the range of 5 to 50 only.");
            return false;
        }

        if (!EMAIL.contains("@")) {
            System.err.println("Email must have a '@' character.");
            return false;
        }

        if (findUser(EMAIL) != null) {
            System.err.println("Email address already exists.");
            return false;
        }

        this.email = EMAIL;
        return true;
    }

    public final Boolean setPassword(final String PASSWORD) {
        if (PASSWORD.length() < 8 || PASSWORD.length() > 25) {
            System.err.println("Password length must be within the range of 8 to 25 only.");
            return false;
        }

        this.password = PASSWORD;
        return true;
    }

    public final Boolean setReservation(final Reservation RESERVATION) {
        if ((reservation == null) || (ReservationDb.findReservation(reservation.getSchedule(), RESERVATION) == null)) {
            System.err.println("Reservation already exists.");
            return false;
        }

        this.reservation = RESERVATION;
        return true;
    }

//    public final String getName() {
//        return this.name;
//    }

    public final String getEmail() {
        return this.email;
    }

    public final String getPassword() {
        return this.password;
    }

    public final Reservation getReservation() {
        return this.reservation;
    }

    public final void displayUserReservation() {
        if (this.reservation == null) {
            System.err.println("You have no pending reservation.");
            return;
        }
        System.out.println("----------------------------------------------");
        System.out.println(this.reservation);
        System.out.println("----------------------------------------------");
    }

    @Override
    public final String toString() {
        return "Name: " + name +
                " Email: " + email +
                " Password: '" + password;
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
