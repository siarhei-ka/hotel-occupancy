package com.example.hotel.service;

import com.example.hotel.model.OccupancyResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OccupancyServiceTest {
    private final OccupancyService service = new OccupancyService();

    @Test
    void testCase2() {
        List<BigDecimal> guests = List.of(
                BigDecimal.valueOf(23.0),
                BigDecimal.valueOf(45.0),
                BigDecimal.valueOf(155.0),
                BigDecimal.valueOf(374.0),
                BigDecimal.valueOf(22.0),
                BigDecimal.valueOf(99.99),
                BigDecimal.valueOf(100.0),
                BigDecimal.valueOf(101.0),
                BigDecimal.valueOf(115.0),
                BigDecimal.valueOf(209.0)
        );

        OccupancyResponse result = service.calculateOccupancy(7, 5, guests);

        assertEquals(6, result.usagePremium());
        assertEquals(0, result.revenuePremium().compareTo(BigDecimal.valueOf(1054.0)));
        assertEquals(4, result.usageEconomy());
        assertEquals(0, result.revenueEconomy().compareTo(BigDecimal.valueOf(189.99)));
    }
}