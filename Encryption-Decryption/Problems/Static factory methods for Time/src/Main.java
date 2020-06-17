import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        return Time.of(12, 0, 0);
    }

    public static Time midnight() {
        return Time.of(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        int hour = (int) seconds / 3600 % 24;
        int minute = (int) seconds / 60 % 60;
        int second = (int) seconds % 60;
        return Time.of(hour, minute, second);
    }

    public static Time of(int hour, int minute, int second) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
            return null;
        }
        Time time = new Time();
        time.hour = hour;
        time.minute = minute;
        time.second = second;
        return time;
    }

}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println((Object) null);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }

}
