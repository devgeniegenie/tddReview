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

    @Test
    void 첫납부일과_만료일일자가_다를때_만원납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    void 이만원이상납부하면_비례해서만료일계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20000)
                        .build(), LocalDate.of(2019, 5, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30000)
                        .build(), LocalDate.of(2019, 6, 1)
        );
    }

    @Test
    void 첫납부일과_만료일날자가다를때_이만원이상납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2019, 4, 30)
        );
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 3, 31))
                        .billingDate(LocalDate.of(2019,4,30))
                        .payAmount(30000)
                        .build(),
                LocalDate.of(2019, 7, 31)
        );
    }

    @Test
    void 십만원납부_1년제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100000)
                        .build(),
                LocalDate.of(2020, 1, 28)
        );
    }

}
