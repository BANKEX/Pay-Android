package com.bankex.pay.data.entity.mappers.value;

import com.bankex.pay.domain.models.network.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Конвертер всяких разных важных значений для отображения по супер формулам из API
 *
 * @author Pavel Apanovskiy on 25.06.2018.
 */
public class ValueConverter {

    public BigDecimal convertValueToNormal(String value, Token token) {
        BigInteger valueBigInteger = new BigInteger(value, 16);
        double resultDouble = valueBigInteger.doubleValue() / Math.pow(10, token.getDcm());
        return BigDecimal.valueOf(resultDouble);
    }

    public BigDecimal convertCostToNormal(BigInteger gasCost, BigInteger gasUsed) {
        double result = (gasCost.multiply(gasUsed).doubleValue()) / Math.pow(10, 18);
        return BigDecimal.valueOf(result);
    }

    public BigDecimal convertBalanceToNormal(String balance) {
        BigInteger valueBigInteger = new BigInteger(balance, 16);
        double resultDouble = valueBigInteger.doubleValue() / Math.pow(10, 18);
        return BigDecimal.valueOf(resultDouble);
    }

    public BigDecimal convertBalanceToNormalWithDecimals(String balance, int decimals) {
        BigInteger valueBigInteger = new BigInteger(balance, 16);
        double resultDouble = valueBigInteger.doubleValue() / Math.pow(10, decimals);
        return BigDecimal.valueOf(resultDouble);
    }

    public BigDecimal convertBalanceToNormal(String balance, int dcm) {
        BigDecimal balanceBigDecimal = new BigDecimal(new BigInteger(balance, 16));
        double pow = Math.pow(10, dcm);
        BigDecimal result = balanceBigDecimal
                .divide(BigDecimal.valueOf(pow), 8, RoundingMode.HALF_DOWN)
                .stripTrailingZeros();
        if (result.signum() == 0) {
            result = BigDecimal.ZERO;
        }
        return result;
    }

}
