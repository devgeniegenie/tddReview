package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int meetCount = getMeetCount(s);

        if(meetCount <= 1) return PasswordStrength.WEAK;
        if(meetCount == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMeetCount(String s) {
        int meetCount = 0;
        boolean lengthCheck = s.length() >= 8;
        if(lengthCheck){
            meetCount++;
        }
        boolean containNumber = meetContainNumber(s);
        if(containNumber){
            meetCount++;
        }
        boolean containUppercase = meetContainUppercase(s);
        if(containUppercase){
            meetCount++;
        }
        return meetCount;
    }

    private boolean meetContainUppercase(String s) {
        boolean containUppercase = false;
        for (char c : s.toCharArray()) {
            if(Character.isUpperCase(c)) {
                containUppercase = true;
                break;
            }
        }
        return containUppercase;
    }

    private boolean meetContainNumber(String s) {
        boolean check = false;
        for (char c : s.toCharArray()) {
            if(c > '0' && c <= '9') {
                check = true;
                break;
            }
        }
        return check;
    }
}
