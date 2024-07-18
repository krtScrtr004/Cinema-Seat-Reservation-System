import java.util.ArrayList;
import java.util.Objects;

public class User extends UserDb {
    private String name;
    private String email;
    private String password;
    private final ArrayList<Reservation> reservation;

    private static final int MAX_RESERVATION_COUNT = 50;

    public User() {
        this.name = null;
        this.email = null;
        this.password = null;
        this.reservation =  new ArrayList<>();
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
            Main.sectionDivider();
            System.out.println("Name length must be within the range of 5 to 100 only.");
            return false;
        }

        this.name = NAME;
        return true;
    }

    public final Boolean setEmail(final String EMAIL) {
        if (EMAIL.length() < 5 || EMAIL.length() > 50) {
            Main.sectionDivider();
            System.out.println("Email length must be within the range of 5 to 50 only.");
            return false;
        }

        if (!EMAIL.contains("@")) {
            Main.sectionDivider();
            System.out.println("Email must have a '@' character.");
            return false;
        }

        if (findUser(EMAIL) != null) {
            Main.sectionDivider();
            System.out.println("Email address already exists.");
            return false;
        }

        this.email = EMAIL;
        return true;
    }

    public final Boolean setPassword(final String PASSWORD) {
        if (PASSWORD.length() < 8 || PASSWORD.length() > 25) {
            Main.sectionDivider();
            System.out.println("Password length must be within the range of 8 to 25 only.");
            return false;
        }

        this.password = PASSWORD;
        return true;
    }

    public final boolean setReservation(final Schedule SCHEDULE, final Reservation RESERVATION) {
        if (ReservationDb.findReservation(SCHEDULE, RESERVATION) != null) {
            Main.sectionDivider();
            System.out.println("Seat is already reserved.");
            return false;
        }

        if (reservation.size() > MAX_RESERVATION_COUNT) {
            Main.sectionDivider();
            System.out.println("Your reservation count reached the maximum (50).");
            return false;
        }

        this.reservation.add(RESERVATION);
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

    public final Reservation getReservation(final int INDEX) {
        return this.reservation.get(INDEX - 1);
    }

    public final void removeReservation(final int INDEX) {
        this.reservation.remove(INDEX - 1);
    }

    public final boolean displayUserReservation() {
        if (this.reservation.isEmpty()) {
            System.out.println("You have no pending reservation.");
            return false;
        }

        int currentIndex = 1;
        for (Reservation reservations : reservation) {
            System.out.println("[" + currentIndex + "] " + reservations);
            currentIndex++;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Password: " + password;
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
