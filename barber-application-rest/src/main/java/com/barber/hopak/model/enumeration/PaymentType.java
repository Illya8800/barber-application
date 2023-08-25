package com.barber.hopak.model.enumeration;

import com.barber.hopak.exception.RankNotFoundException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum PaymentType {
    CARD,
    CASH;

    private static final Map<String, String> paymentTypes = new HashMap<>();

    static {
        for (PaymentType rank : values()) {
            paymentTypes.put(rank.name(), rank.name());
        }
    }

    public static boolean isValidType(PaymentType paymentType) {
        if (paymentType == null || StringUtils.isEmpty(paymentType.name())) throw new RankNotFoundException("Empty payment type name");
        return paymentTypes.containsKey(paymentType.name());
    }
}
