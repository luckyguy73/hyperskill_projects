package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String ans = "", num = "";
        int radix = 0, target = 0;
        if (s.hasNextInt()) radix = s.nextInt();
        if (s.hasNext()) num = s.next();
        if (s.hasNextInt()) target = s.nextInt();
        if (radix < 1 || radix > 36 || target < 1 || target > 36) ans = "Error: Radix should be in range 1 - 36";
        if (num.equals("")) ans = "Error: missing number";
        String[] parts = num.split("\\.");
        ans = ans.equals("") ? convertWholeNumber(radix, parts[0], target) : ans;
        if (parts.length > 1) ans += "." + fracDecToBase(fracBaseToDec(radix, parts[1]), target);
        System.out.println(ans);
    }

    private static String convertWholeNumber(int radix, String num, int target) {
        int source = radix == 1 ? num.length() : Integer.parseInt(num, radix);
        return target == 1 ? "1".repeat(Integer.parseInt(num)) : Integer.toString(source, target);
    }

    private static String fracBaseToDec(int radix, String frac) {
        if (radix == 10) return "0." + frac;
        double total = 0.0;
        for (int i = 0; i < frac.length(); ++i) {
            int digit = Character.getNumericValue(frac.charAt(i));
            total += digit / Math.pow(radix, i + 1);
        }
        return Double.toString(total);
    }

    private static String fracDecToBase(String frac, int target) {
        double fracNum = Double.parseDouble(frac);
        int num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; ++i) {
            num = (int) (fracNum * target);
            sb.append(Character.forDigit(num, target));
            fracNum = (fracNum * target) - num;
        }
        return sb.toString();
    }

}
