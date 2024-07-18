// README: commented code can be used for future features
// eg. adding of admin user

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    private final User currentUser;

    public static class ScheduleList {
        private static final TreeMap<Integer, Schedule> timeSchedules;
        public static final int MIN_INDEX = 1;
        public static final int MAX_INDEX = 6;

        static {
            // populate schedule list
            timeSchedules = new TreeMap<>();
            timeSchedules.put(MIN_INDEX, new Schedule(LocalTime.of(9, 0), LocalTime.of(10, 0)));
            timeSchedules.put(2, new Schedule(LocalTime.of(10, 0), LocalTime.of(11, 0)));
            timeSchedules.put(3, new Schedule(LocalTime.of(11, 0), LocalTime.of(12, 0)));
            timeSchedules.put(4, new Schedule(LocalTime.of(13, 0), LocalTime.of(14, 0)));
            timeSchedules.put(5, new Schedule(LocalTime.of(14, 0), LocalTime.of(15, 0)));
            timeSchedules.put(MAX_INDEX, new Schedule(LocalTime.of(15, 0), LocalTime.of(16, 0)));
        }

        public static Schedule getSchedule(final int INDEX) {
            return timeSchedules.getOrDefault(INDEX, null);
        }

        public static void printSchedule() {
            for (Integer key : timeSchedules.keySet()) {
                System.out.println("[" + key + "] " + timeSchedules.get(key));
            }
        }
    }


    private static class Seats {
        private static final int COLUMN;
        private static final int ROW;
        public static final Integer[][] seats;      // array to print available seats according to schedule index arg

        static {
            COLUMN = 5;
            ROW = 10;
            seats = new Integer[COLUMN][ROW];
        }

        public static void displayAllSeats(final Schedule SCHEDULE) {
            for (int i = 0; i < COLUMN; ++i) {
                for (int j = 0; j < ROW; ++j) {
                    seats[i][j] = null;
                }
            }

            ArrayList<Reservation> tempArray = ReservationDb.retrieveReservationList(SCHEDULE);
            if (tempArray != null) {
                for (Reservation reservation : tempArray) {
                    final Integer SEAT_NUMBER = reservation.getSeatNumber();
                    if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50) {
                        System.out.println("Seat number must be in the range of 1 to 50.");
                        return;
                    }

                    // parse seat number to column and row on the 2d array
                    int row, column;
                    if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20                              // if seat number is x % 10 == 0, default row to the last index of the row (9)
                            || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
                            || SEAT_NUMBER == 50) {
                        column = (SEAT_NUMBER / 10) - 1;
                        row = 9;
                    } else {
                        row = (SEAT_NUMBER % 10) - 1;
                        column = (SEAT_NUMBER / 10 == 0 ? 0 : (SEAT_NUMBER % 10) - 1);     // if the seat number is less than 10, default the column to 0
                    }
                    seats[column][row] = 1;
                }
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
        boolean runProgram = true;
        while (runProgram) {
            Main.sectionDivider();
            System.out.println("SEAT RESERVATION SYSTEM");
            Main.sectionDivider();

            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Forget password");
            System.out.println("[4] Exit");
            Main.sectionDivider();
            System.out.print("Enter option index: ");
            int choice = InputReader.readInt();
            switch (choice) {
                case 1: {
                    User user = Main.login();
                    if (user == null) {
                        Main.sectionDivider();
                        System.out.println("Email / password incorrect.");
                        continue;
                    }
                    Main main = new Main(user);
                    main.homePage();
                    break;
                }

                case 2: {
                    if (register()) {
                        Main.sectionDivider();
                        System.out.println("Registration successful.");
                    }
                    else {
                        Main.sectionDivider();
                        System.out.println("Registration unsuccessful.");
                    }

                    break;
                }

                case 3: {
                    if (Main.forgotPassword()) {
                        Main.sectionDivider();
                        System.out.println("Password successfully changed");
                    }
                    else {
                        Main.sectionDivider();
                        System.out.println("Email not found.");
                    }
                    break;
                }

                case 4: {
                    InputReader.closeRead();
                    runProgram = false;
                    break;
                }

                default: {
                    Main.sectionDivider();
                    System.out.println("Option index length must be within the range of 1 to 4 only.");
                }
            }
        }
    }

    public Main(final User USER) {
        this.currentUser = USER;
    }

    public static User login() {
        Main.sectionDivider();
        System.out.println("Login");
        Main.sectionDivider();

        final String EMAIL = User.inputEmail();
        final String PASSWORD = User.inputPassword();
        final User TEMP_USER = User.findUser(EMAIL);
        return ((TEMP_USER == null) || !(TEMP_USER.getPassword().equals(PASSWORD)) ? null : TEMP_USER);
    }

    public static Boolean register() {
        Main.sectionDivider();
        System.out.println("Register");
        Main.sectionDivider();

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

    public static Boolean forgotPassword() {
        Main.sectionDivider();
        System.out.println("Forgot Password");
        Main.sectionDivider();

        final String EMAIL = User.inputEmail();
        final User TEMP = User.findUser(EMAIL);
        if (TEMP == null) {
            return false;
        }

        return (TEMP.setPassword(EMAIL));
    }

    public final void homePage() {
        boolean runProgram = true;
        while (runProgram) {
            Main.sectionDivider();
            System.out.println("Home");
            Main.sectionDivider();

            System.out.println("[1] Reserve seat");
            System.out.println("[2] Cancel reservation");
            System.out.println("[3] See reservation");
            System.out.println("[4] See profile");
            System.out.println("[5] Exit");
            Main.sectionDivider();
            System.out.print("Enter option index: ");

            int choice = InputReader.readInt();
            switch (choice) {
                case 1: {
                    Main.sectionDivider();
                    System.out.println("Reserve");
                    Main.sectionDivider();
                    reserve();
                    break;
                }

                case 2: {
                    Main.sectionDivider();
                    System.out.println("Cancel");
                    cancel();
                    break;
                }

                case 3: {
                    Main.sectionDivider();
                    currentUser.displayUserReservation();
                    break;
                }

                case 4: {
                    Main.sectionDivider();
                    System.out.println("User Information");
                    Main.sectionDivider();
                    System.out.println(currentUser);
                    Main.sectionDivider();

                    System.out.print("Press [u] to update your information: ");
                    if (Character.toLowerCase(InputReader.readChar()) == 'u') {
                        updateUser();
                    }
                    break;
                }

                case 5: {
                    runProgram = false;
                    break;
                }

                default:
                    System.out.println("Option index length must be within the range of 1 to 5 only.");
            }
        }

    }

    private void reserve() {
        System.out.println("Available schedules:");
        Main.sectionDivider();
        ScheduleList.printSchedule();
        Main.sectionDivider();
        final String EMAIL = currentUser.getEmail();
        final Schedule SCHEDULE_INDEX = Reservation.inputScheduleIndex();

        Main.sectionDivider();
        System.out.println("Available seats:");
        Main.sectionDivider();
        Seats.displayAllSeats(SCHEDULE_INDEX);
        Main.sectionDivider();
        final Integer SEAT_NUMBER = Reservation.inputSeatNumber();

        final Reservation RESERVATION = new Reservation(EMAIL, SEAT_NUMBER, SCHEDULE_INDEX);
        if (!currentUser.setReservation(RESERVATION.getSchedule(), RESERVATION))
            return;

        ReservationDb.addReservation(SCHEDULE_INDEX, RESERVATION);
        Main.sectionDivider();
        System.out.println("Reservation successful.");
    }

    private void cancel() {
        Main.sectionDivider();
        System.out.println("Select reservation index:");
        Main.sectionDivider();
        if (!currentUser.displayUserReservation()) {
            Main.sectionDivider();
            return;
        }

        Main.sectionDivider();
        System.out.print("Select reservation index: ");
        final int RESERVATION_INDEX = InputReader.readInt();
        final Reservation tempReservation = currentUser.getReservation(RESERVATION_INDEX);
        if (tempReservation == null)
            return;

        if (ReservationDb.findReservation(tempReservation.getSchedule(), tempReservation) == null) {
            Main.sectionDivider();
            System.out.println("Your reservation cannot found.");
            return;
        }

        this.currentUser.removeReservation(RESERVATION_INDEX);
        ReservationDb.deleteReservation(tempReservation.getSchedule(), tempReservation);
        Main.sectionDivider();
        System.out.println("Reservation cancelled.");
    }

    private void updateUser() {
        Main.sectionDivider();
        System.out.println("Update user information");
        Main.sectionDivider();

        User tempUser = UserDb.findUser(currentUser.getEmail());
        if (tempUser == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("What info you want to update?");
        System.out.println("[1] Name");
        System.out.println("[2] Email");
        System.out.println("[3] Password");
        System.out.println("[4] Cancel");
        Main.sectionDivider();
        boolean isValid;
        int choice;
        do {
            isValid = true;
            System.out.print("Enter option index: ");
            choice = InputReader.readInt();
            if (choice < 1 || choice > 4) {
                Main.sectionDivider();
                System.out.println("Option index length must be within the range of 1 to 4 only.");
                Main.sectionDivider();
                isValid = false;
            }
        } while (!isValid);
        Main.sectionDivider();

        switch (choice) {
            case 1: {
                final String newName = User.inputName();
                Main.sectionDivider();
                if (tempUser.setName(newName))
                    System.out.println("Name successfully updated.");
                Main.sectionDivider();
                break;
            }

            case 2: {
                final String newEmail = User.inputEmail();
                Main.sectionDivider();
                if (tempUser.setEmail(newEmail))
                    System.out.println("Email successfully updated.");
                Main.sectionDivider();
                break;
            }

            case 3: {
                final String newPassword = User.inputPassword();
                Main.sectionDivider();
                if (tempUser.setPassword(newPassword))
                    System.out.println("Password successfully updated.");
                Main.sectionDivider();
                break;
            }
        }
    }

    public static void sectionDivider() {
        for (int i = 0; i < 60; ++i)
            System.out.print("-");
        System.out.println();
    }
}