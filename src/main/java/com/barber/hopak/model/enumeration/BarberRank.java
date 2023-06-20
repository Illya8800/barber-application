package com.barber.hopak.model.enumeration;

import com.barber.hopak.exception.RankNotFoundException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum BarberRank {
    BARBER_APPRENTICE,
    BARBER_TRAINEE,
    JUNIOR_BARBER,
    BARBER,
    TOP_BARBER,
    BARBER_SENIOR,
    CHEF_BARBER;

    private static final Map<String, String> ranks = new HashMap<>();

    static {
        for (BarberRank rank : values()) {
            ranks.put(rank.name(), rank.name());
        }
    }

    public static boolean isValidRank(BarberRank barberRank) {
        if (barberRank == null || StringUtils.isEmpty(barberRank.name())) throw new RankNotFoundException("Empty rank name");
        return ranks.containsKey(barberRank.name());
    }
}

