public class SeatReservationSystem {
    private static UserDB userDB = new UserDB();
    private static ReservationDB reservationDB = new ReservationDB();

    // static private class Seats {
    // private static Integer[][] seats = new Integer[5][10];

    // public static final Boolean isFull() {
    // for (Integer[] row : seats) {
    // for (Integer i : row) {
    // if (i != null)
    // return false;
    // }
    // }

    // return true;
    // }

    // public static final Boolean isReserved(final int SEAT_NUMBER) {
    // if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50)
    // throw new IllegalArgumentException("Invalid seat number");

    // int row, column;
    // if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
    // || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
    // || SEAT_NUMBER == 50) {
    // row = (SEAT_NUMBER / 10) - 1;
    // column = 9;
    // } else {
    // row = (SEAT_NUMBER / 10 <= 0 ? 0 : SEAT_NUMBER % 10);
    // column = (SEAT_NUMBER % 10) - 1;
    // }

    // return seats[row][column] != null;
    // }

    // public static final void reserve(final int SEAT_NUMBER) {
    // if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50)
    // throw new IllegalArgumentException("Invalid seat number");

    // int row, column;
    // if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
    // || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
    // || SEAT_NUMBER == 50) {
    // row = (SEAT_NUMBER / 10) - 1;
    // column = 9;
    // } else {
    // row = (SEAT_NUMBER / 10 <= 0 ? 0 : SEAT_NUMBER % 10);
    // column = (SEAT_NUMBER % 10) - 1;
    // }

    // seats[row][column] = 1;
    // }

    // public static final void cancel(final int SEAT_NUMBER) {
    // if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50)
    // throw new IllegalArgumentException("Invalid seat number");

    // int row, column;
    // if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
    // || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
    // || SEAT_NUMBER == 50) {
    // row = (SEAT_NUMBER / 10) - 1;
    // column = 9;
    // } else {
    // row = (SEAT_NUMBER / 10 <= 0 ? 0 : SEAT_NUMBER % 10);
    // column = (SEAT_NUMBER % 10) - 1;
    // }

    // seats[row][column] = null;
    // }

    // public static final void displaySeats() {
    // for (Integer[] row : seats) {
    // for (Integer i : row) {
    // if (i == null)
    // System.out.print("0 ");
    // else
    // System.out.print("1 ");
    // }
    // System.out.println();
    // }
    // System.out.println();
    // }
    // }

    public static void main(String[] args) {
        while (true) {
            System.out.println("SEAT RESERVATION SYSTEM");
            System.out.println("[1] Login"); // ADD ENUMERATION HERE
            System.out.println("[2] Register");
            System.out.println("[3] Exit");

            System.out.print("Enter your choice: ");
            int choice = InputReader.readInt();

            switch (choice) {
                case 1: {
                    if (login() != null)
                        System.out.println("Login successful!");
                    else
                        System.out.println("Login failed!");
                    break;
                }

                case 2: {
                    if (register())
                        System.out.println("Registration successful!");
                    else
                        System.out.println("Registration failed!");

                    UserDB.displayAllUsers();
                    break;
                }

                default:
                    break;
            }
        }

        // InputReader.closeRead();
    }

    public static final User login() {
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

    public static final Boolean register() {
        System.out.println("Register");

        final String NAME = User.inputName();
        final String EMAIL = User.inputEmail();
        final String Password = User.inputPassword();
        User temp = new User();
        if (!temp.setName(NAME) || !temp.setEmail(EMAIL) || !temp.setPassword(Password))
            return false;
        else
            UserDB.addUser(temp);

        return true;
    }
}
