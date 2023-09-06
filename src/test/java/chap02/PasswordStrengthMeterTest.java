package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    /**
     * 규칙 목록
     * 1. 길이가 8글자 이상
     * 2. 0부터 9 사이의 숫자를 포함
     * 3. 대문자 포함
     * <p>
     * 모두만족 = 강함, 2개만족 = 보통, 1개만족 = 약함
     */

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }
    @Test
    void meetsAllThenStrong() {
        assertStrength("absadf!@#2331ASD", PasswordStrength.STRONG);
    }

    @Test
    void meetAllExceptLength() {
        assertStrength("s!@d4F", PasswordStrength.NORMAL);
    }

    @Test
    void meetAllExceptNumber() {
        assertStrength("s!@daaaabbbF", PasswordStrength.NORMAL);
    }

    @Test
    void nullInput() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void emptyInput() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void meetAllExceptUppercase() {
        assertStrength("sdfsd!344sdfsdf", PasswordStrength.NORMAL);
    }

    @Test
    void meetOnlyLength() {
        assertStrength("asdfsdfsfbsd", PasswordStrength.WEAK);
    }

    @Test
    void meetOnlyNumber() {
        assertStrength("1234", PasswordStrength.WEAK);
    }

    @Test
    void meetOnlyUppercase() {
        assertStrength("SDFS", PasswordStrength.WEAK);
    }

    @Test
    void meetNothing() {
        assertStrength("ab", PasswordStrength.WEAK);
    }

}
