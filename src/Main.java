import java.util.ArrayList;

public class Main {
    private User currentUser = new User();
    private static final UserDb userDB = new UserDb();
    private static final ReservationDb reservationDB = new ReservationDb();

    private static class Seats {
        private static final int COLUMN = 5, ROW = 10;
        public static final Integer[][] seats = new Integer[COLUMN][ROW];

        static {
            for (int i = 0; i < COLUMN; ++i) {
                for (int j = 0; j < ROW; ++j) {
                    seats[i][j] = null;
                }
            }
        }

        public static void displayAllSeats(final TimeSchedule.Schedule SCHEDULE) {
            ArrayList<Reservation> tempArray = reservationDB.retrieveReservationList(SCHEDULE);
            if (tempArray == null)
                return;

            for (Reservation reservation : tempArray) {
                final Integer SEAT_NUMBER = reservation.getSeatNumber();
                if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50) {
                    System.err.println("Seat number must be in the range of 1 to 50.");
                    return;
                }

                // parse seat number to column and row on the 2d array
                int row = 0, column = 0;
                if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
                        || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
                        || SEAT_NUMBER == 50) {
                    column = (SEAT_NUMBER / 10) - 1;
                    row = 9;
                } else {
                    row = (SEAT_NUMBER / 10 == 0 ? 0 : (SEAT_NUMBER % 10) - 1);
                    column = (SEAT_NUMBER % 10) - 1;
                }
                seats[column][row] = 1;
            }

            int counter = 1;
            for (Integer[] x : seats) {
                for (Integer y : x) {
                    if (y != null)
                        System.out.print("x    ");
                    else
                        System.out.print((counter < 10) ? counter + "    " : counter + "   ");
                    counter++;
                }
                System.out.println();
            }
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
                    User user = Main.login();
                    break;
                }

                case 2: {

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
        final User TEMP_USER = User.findUser(EMAIL);
        if (TEMP_USER == null || !(TEMP_USER.getPassword().equals(PASSWORD))) {
            System.out.println("Email / password incorrect!");
            return null;
        }

        return TEMP_USER;
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

    public void reserve() {
        System.out.println("Reserve");

        TimeSchedule.printSchedule();
        final String EMAIL = currentUser.getEmail();
        final TimeSchedule.Schedule SCHEDULE_INDEX = Reservation.inputScheduleIndex();
        final Integer SEAT_NUMBER = Reservation.inputSeatNumber();
        final Reservation RESERVATION = new Reservation(EMAIL, SEAT_NUMBER, SCHEDULE_INDEX);
        if (!currentUser.setReservation(RESERVATION))
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

        if (reservationDB.findReservation(tempReservation.getSchedule(), tempReservation) == null) {
            System.err.println("Your reservation cannot be found on our database.");
            return;
        }

        reservationDB.deleteReservation(tempReservation.getSchedule(), tempReservation);
        System.out.println("Reservation cancelled.");
    }
}