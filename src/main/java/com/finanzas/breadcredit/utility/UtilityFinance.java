package com.finanzas.breadcredit.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class UtilityFinance {
    public static BigDecimal calcFutureValue(BigDecimal presentValue, String creditType, BigDecimal rate, Date purchaseDate, Date currentDate, Long compounding) {
        BigDecimal futureValue;
        double period;
        long diffInMillies = currentDate.getTime() - purchaseDate.getTime();
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        switch (creditType.charAt(2)) {
            case 'Q':
                period = 15;
                break;
            case 'M':
                period = 30;
                break;
            case 'B':
                period = 60;
                break;
            case 'T':
                period = 90;
                break;
            case 'C':
                period = 120;
                break;
            case 'S':
                period = 180;
                break;
            case 'A':
                period = 360;
                break;
            default:
                period = 0;
                break;
        }
        if (creditType.charAt(1) == 'N') {
            double finalRate = Math.pow(((double) 1 + (rate.doubleValue() / (100 * period / (double) compounding))), period / (double) compounding) - (double) 1;
            rate = BigDecimal.valueOf(finalRate);
        }
        double base = 1 + rate.divide(new BigDecimal(100), 10, RoundingMode.HALF_UP).doubleValue();
        double time = new BigDecimal(diffInDays).divide(new BigDecimal(period), 10, RoundingMode.HALF_UP).doubleValue();
        double result = Math.pow(base, time);
        futureValue = presentValue.multiply((new BigDecimal(result).setScale(10, RoundingMode.HALF_UP)));
        return futureValue;
    }
}