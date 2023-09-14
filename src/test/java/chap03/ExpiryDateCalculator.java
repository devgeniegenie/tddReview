package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calulateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100000 ? 12 : payData.getPayAmount() / 10000;
        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp =
                    payData.getBillingDate().plusMonths(addedMonths);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (dayOfFirstBilling !=
                    candidateExp.getDayOfMonth()) {
                final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMon <
                        dayOfFirstBilling) {
                    return candidateExp.withDayOfMonth(
                            dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(dayOfFirstBilling);
            } else {
                return candidateExp;
            }
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }
}
