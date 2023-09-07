package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    /**
     * 1. 서비스를 사용하려면 매달 1만원을 선불로 납부. 납부일 기준으로 한 달 뒤가 서비스 만료일
     * 2. 2개월 이상 요금 납부 가능
     * 3. 10만원 납부시 1년 제공
     */

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calulateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    void 만원납부_한달뒤만료() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 4, 1));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 6, 5));
    }

    @Test
    void 납부일과_다음달납부일_다름() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 2, 28));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2020, 2, 29));
    }

}
