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
        public static final Integer[][] seats;

        static {
            COLUMN = 5;
            ROW = 10;
            seats = new Integer[COLUMN][ROW];
            for (int i = 0; i < COLUMN; ++i) {
                for (int j = 0; j < ROW; ++j) {
                    seats[i][j] = null;
                }
            }
        }

        public static void displayAllSeats(final Schedule SCHEDULE) {
            ArrayList<Reservation> tempArray = ReservationDb.retrieveReservationList(SCHEDULE);
            if (tempArray != null) {
                for (Reservation reservation : tempArray) {
                    final Integer SEAT_NUMBER = reservation.getSeatNumber();
                    if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50) {
                        System.err.println("Seat number must be in the range of 1 to 50.");
                        return;
                    }

                    // parse seat number to column and row on the 2d array
                    int row, column;
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
            System.out.println("SEAT RESERVATION SYSTEM");

            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Forget password");
            System.out.println("[4] Exit");
            System.out.print("Enter option index: ");
            int choice = InputReader.readInt();
            switch (choice) {
                case 1: {
                    User user = Main.login();
                    if (user == null) {
                        System.err.println("Email / password incorrect.");
                        continue;
                    }
                    Main main = new Main(user);
                    main.homePage();
                    break;
                }

                case 2: {
                    if (register())
                        System.out.println("Registration successful.");
                    else
                        System.err.println("Registration unsuccessful.");

                    break;
                }

                case 3: {
                    if (Main.forgotPassword())
                        System.out.println("Password successfully changed");
                    else
                        System.err.println("Email not found.");
                    break;
                }

                case 4: {
                    InputReader.closeRead();
                    runProgram = false;
                    break;
                }

                default:
                    System.err.println("Option index length must be within the range of 1 to 4 only.");
            }
        }
    }

    public Main(final User USER) {
        this.currentUser = USER;
    }

    public static User login() {
        System.out.println("Login");

        final String EMAIL = User.inputEmail();
        final String PASSWORD = User.inputPassword();
        final User TEMP_USER = User.findUser(EMAIL);
        return (TEMP_USER == null || !(TEMP_USER.getPassword().equals(PASSWORD)) ? null : TEMP_USER);
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

    public static Boolean forgotPassword() {
        System.out.println("Forgot Password");

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
            System.out.println("Home");

            System.out.println("[1] Reserve seat");
            System.out.println("[2] Cancel reservation");
            System.out.println("[3] See reservation");
            System.out.println("[4] See profile");
            System.out.println("[5] Exit");
            System.out.print("Enter option index: ");
            int choice = InputReader.readInt();
            switch (choice) {
                case 1: {
                    reserve();
                    break;
                }

                case 2: {
                    currentUser.displayUserReservation();
                    cancel();
                    break;
                }

                case 3: {
                    currentUser.displayUserReservation();
                    break;
                }

                case 4: {
                    System.out.println("User Information");
                    System.out.println(currentUser);

                    System.out.println("Press [u] to update your information");
                    if (Character.toLowerCase(InputReader.readChar()) == 'u') {
                        UserDb.updateUser(currentUser);
                    }
                    break;
                }

                case 5: {
                    runProgram = false;
                    break;
                }

                default:
                    System.err.println("Option index length must be within the range of 1 to 5 only.");
            }
        }

    }

    private void reserve() {
        System.out.println("Reserve");

        ScheduleList.printSchedule();
        final String EMAIL = currentUser.getEmail();
        final Schedule SCHEDULE_INDEX = Reservation.inputScheduleIndex();
        Seats.displayAllSeats(SCHEDULE_INDEX);
        final Integer SEAT_NUMBER = Reservation.inputSeatNumber();
        final Reservation RESERVATION = new Reservation(EMAIL, SEAT_NUMBER, SCHEDULE_INDEX);
        if (!currentUser.setReservation(RESERVATION))
            return;

        ReservationDb.addReservation(SCHEDULE_INDEX, RESERVATION);
        System.out.println("Reservation successful.");
    }

    private void cancel() {
        System.out.println("Cancel");

        final Reservation tempReservation = currentUser.getReservation();
        if (tempReservation == null) {
            System.err.println("You do not have pending reservation.");
            return;
        }

        if (ReservationDb.findReservation(tempReservation.getSchedule(), tempReservation) == null) {
            System.err.println("Your reservation cannot be found on our database.");
            return;
        }

        ReservationDb.deleteReservation(tempReservation.getSchedule(), tempReservation);
        System.out.println("Reservation cancelled.");
    }
}