import java.util.*;

public class timeTableGenerator {

    static final String[] DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
    };
    static final int PERIODS = 8;

    static List<String> subjects = new ArrayList<>();
    static Map<String, String> teachers = new HashMap<>();
    static Map<String, Integer> weeklyLimit = new HashMap<>();

    static String[][] timetable = new String[5][PERIODS];

    static Map<String, Integer> weeklyCount = new HashMap<>();
    static Map<String, int[]> dailyCount = new HashMap<>();

    // ---------- CSP CONSTRAINT CHECK ----------
    static boolean isValid(int day, int period, String subject) {

        // Weekly limit constraint
        if (weeklyCount.get(subject) >= weeklyLimit.get(subject))
            return false;

        // Max 2 periods per day constraint
        if (dailyCount.get(subject)[day] >= 2)
            return false;

        // No consecutive same subject constraint
        if (period > 0 && subject.equals(timetable[day][period - 1]))
            return false;

        return true;
    }

    // ---------- BACKTRACKING CSP SOLVER ----------
    static boolean solve(int day, int period) {

        if (day == 5)
            return true;

        int nextDay = day;
        int nextPeriod = period + 1;
        if (nextPeriod == PERIODS) {
            nextDay++;
            nextPeriod = 0;
        }

        for (String sub : subjects) {
            if (isValid(day, period, sub)) {

                timetable[day][period] = sub;
                weeklyCount.put(sub, weeklyCount.get(sub) + 1);
                dailyCount.get(sub)[day]++;

                if (solve(nextDay, nextPeriod))
                    return true;

                // Backtrack
                timetable[day][period] = null;
                weeklyCount.put(sub, weeklyCount.get(sub) - 1);
                dailyCount.get(sub)[day]--;
            }
        }
        return false;
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter subject name: ");
            String sub = sc.nextLine();

            System.out.print("Enter teacher for " + sub + ": ");
            teachers.put(sub, sc.nextLine());

            System.out.print("Enter weekly periods for " + sub + ": ");
            weeklyLimit.put(sub, sc.nextInt());
            sc.nextLine();

            subjects.add(sub);
            weeklyCount.put(sub, 0);
            dailyCount.put(sub, new int[5]);
        }

        if (solve(0, 0)) {
            System.out.println("\n✅ TIMETABLE GENERATED (JAVA CSP)\n");
            for (int d = 0; d < 5; d++) {
                System.out.println(DAYS[d]);
                for (int p = 0; p < PERIODS; p++) {
                    String s = timetable[d][p];
                    System.out.println("  Period " + (p + 1) + ": "
                            + s + " (" + teachers.get(s) + ")");
                }
                System.out.println();
            }
        } else {
            System.out.println("❌ No feasible timetable found");
        }
        System.out.println("\nCLASS TIME TABLE\n");

System.out.printf("%-10s", "DAY");
for (int p = 1; p <= 8; p++) {
    System.out.printf("|  P%-2d  ", p);
}
System.out.println();
System.out.println("---------------------------------------------------------------");

for (int d = 0; d < 5; d++) {
    System.out.printf("%-10s", DAYS[d]);
    for (int p = 0; p < 8; p++) {
        String sub = timetable[d][p];
        System.out.printf("| %-5s", sub);
    }
    System.out.println();
}


        sc.close();
    }
}
