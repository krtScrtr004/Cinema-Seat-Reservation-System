
public class Main {
    private User currentUser = new User();
    private static final UserDb userDB = new UserDb();
    private static final ReservationDb reservationDB = new ReservationDb();

    private static class Seats {
        public static final Integer[][] seats = new Integer[5][10];

        public static void displayAllSeats(final int SEAT_SCHEDULE_INDEX) {

        }
    }

    public static void main(String[] args) {
        while (true) {
            User temp = new User("Secretario, Kurt O.", "kurt123@gmail.com", "kurt123", null);
            userDB.addUser(temp);

            System.out.println("SEAT RESERVATION SYSTEM");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.print("Enter your choice: ");
            int choice = InputReader.readInt();
            switch (choice) {
                case 1: {
                    // For Login
                    Main main = new Main(temp);
                    main.reserve();
                    reservationDB.displayAllReservations();
                    main.cancel();
                    reservationDB.displayAllReservations();
                    break;
                }

                case 2: {
                    // For Signup
                    break;
                }

                default:
                    break;
            }
        }

        // InputReader.closeRead();
    }

    public Main(final User USER) {
        this.currentUser = USER;
    }

    public static User login() {
        System.out.println("Login");

        final String EMAIL = User.inputEmail();
        final String PASSWORD = User.inputPassword();
        final User TEMP = User.findUser(EMAIL);
        if (TEMP == null || !(TEMP.getPassword().equals(PASSWORD))) {
            System.out.println("Email / password incorrect!");
            return null;
        }

        return TEMP;
    }

    public final void forgotPassword() {
        System.out.println("Forgot Password");

        final String EMAIL = User.inputEmail();
        final User TEMP = User.findUser(EMAIL);
        if (TEMP == null) {
            System.out.println("Email not found!");
            return;
        }

        if (TEMP.setPassword(EMAIL))
            System.out.println("Password successfully changed");
    }

    public static Boolean register() {
        System.out.println("Register");

        final String NAME = User.inputName();
        final String EMAIL = User.inputEmail();
        final String Password = User.inputPassword();
        User temp = new User();
        if (!temp.setName(NAME) || !temp.setEmail(EMAIL) || !temp.setPassword(Password))
            return false;
        else
            UserDb.addUser(temp);

        return true;
    }

    public void reserve() {
        System.out.println("Reserve");

        TimeSchedule.printSchedule();
        final String EMAIL = currentUser.getEmail();
        final Integer SEAT_NUMBER = Reservation.inputSeatNumber();
        final TimeSchedule.Schedule SCHEDULE_INDEX = Reservation.inputScheduleIndex();
        final Reservation RESERVATION = new Reservation(EMAIL, SEAT_NUMBER, SCHEDULE_INDEX);
        if (currentUser.setReservation(RESERVATION))
            return;

        reservationDB.addReservation(SCHEDULE_INDEX, RESERVATION);
        System.out.println("Reservation successful.");
    }

    public void cancel() {
        System.out.println("Cancel");

        final Reservation tempReservation = currentUser.getReservation();
        if (tempReservation == null) {
            System.err.println("You do not have pending reservation.");
            return;
        }

        if (reservationDB.findReservation(tempReservation.getSchedule(), tempReservation) == null ) {
            System.err.println("Your reservation cannot be found on our database.");
            return;
        }

        reservationDB.deleteReservation(tempReservation.getSchedule(), tempReservation);
        System.out.println("Reservation cancelled.");
    }
}