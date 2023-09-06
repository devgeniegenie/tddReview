package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calulateExpiryDate(LocalDate billingDate, int pay) {
        return billingDate.plusMonths(1);
    }
}
