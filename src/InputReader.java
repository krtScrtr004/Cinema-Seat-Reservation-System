import java.util.Scanner;

public class InputReader {
    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static int readInt() {
        int temp = sc.nextInt();
        sc.nextLine();
        return temp;
    }

    public static char readChar() {
        String input;
        input = sc.nextLine();
        return input.charAt(0);
    }

    public static String readString() {
        return sc.nextLine();
    }

    public static void closeRead() {
        sc.close();
    }
}
