package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    /**
     * 규칙 목록
     * 1. 길이가 8글자 이상
     * 2. 0부터 9 사이의 숫자를 포함
     * 3. 대문자 포함
     *
     * 모두만족 = 강함, 2개만족 = 보통, 1개만족 = 약함
     */
    @Test
    void meetsAllThenStrong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("absadf!@#2331ASD");
        assertEquals(PasswordStrength.STRONG, result);
    }

    @Test
    void meetAllExceptLength() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("s!@d4F");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    void meeAllExceptNumber() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("s!@daaaabbbF");
        assertEquals(PasswordStrength.NORMAL, result);
    }

}
