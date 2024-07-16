
public class Main {
    private User currentUser = new User();
    private static final UserDb userDB = new UserDb();
    private static final ReservationDb reservationDB = new ReservationDb();


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
        final Reservation RESERVATION = new Reservation(currentUser.getEmail(),
                                                        Reservation.inputSeatNumber(),
                                                        Reservation.inputScheduleIndex());
        if (reservationDB.findReservation(RESERVATION)) {
            System.err.println("Reservation already exists.");
            return;
        }

        reservationDB.addReservation(RESERVATION);
        System.out.println("Reservasion successful.");
    }

    public void cancel() {
        System.out.println("Cancel");

        if (currentUser.getReservation() == null) {
            System.err.println("You do not have pending reservation.");
            return;
        }

        if (!reservationDB.findReservation(currentUser.getReservation())) {
            System.err.println("Your reservation cannot be found on our database.");
            return;
        }

        reservationDB.deleteReservation(currentUser.getReservation());
        System.out.println("Reservation cancelled.");
    }
}