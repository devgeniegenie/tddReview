package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }
        boolean check = meetContainNumber(s);
        if(!check) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
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
