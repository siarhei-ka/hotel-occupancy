package com.example.hotel.service;

import com.example.hotel.model.OccupancyResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OccupancyService {
    public OccupancyResponse calculateOccupancy(int premiumRooms, int economyRooms, List<BigDecimal> guests) {

        List<BigDecimal> sorted = new ArrayList<>(guests);
        sorted.sort(Collections.reverseOrder());

        List<BigDecimal> premiumGuests = sorted.stream()
                .filter(p -> p.compareTo(BigDecimal.valueOf(100)) >= 0)
                .toList();
        List<BigDecimal> economyGuests = sorted.stream()
                .filter(p -> p.compareTo(BigDecimal.valueOf(100)) < 0)
                .toList();

        List<BigDecimal> assignedPremium = new ArrayList<>();
        List<BigDecimal> upgraded = new ArrayList<>();

        int remainingPremiumRooms = premiumRooms;

        for (BigDecimal price : premiumGuests) {
            if (remainingPremiumRooms-- > 0)
                assignedPremium.add(price);
        }

        int remainingEconomyRooms = economyRooms;
        List<BigDecimal> assignedEconomy = new ArrayList<>();

        for (BigDecimal price : economyGuests) {
            if (remainingEconomyRooms-- > 0)
                assignedEconomy.add(price);
        }

        remainingPremiumRooms = premiumRooms - assignedPremium.size();
        if (remainingPremiumRooms > 0) {
            List<BigDecimal> upgradeCandidates = economyGuests.stream()
                    .filter(p -> !assignedEconomy.contains(p))
                    .sorted(Collections.reverseOrder())
                    .toList();

            for (BigDecimal price : upgradeCandidates) {
                if (remainingPremiumRooms-- > 0) {
                    upgraded.add(price);
                }
            }
        }

        assignedPremium.addAll(upgraded);
        assignedEconomy.removeAll(upgraded);

        BigDecimal revenuePremium = assignedPremium.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal revenueEconomy = assignedEconomy.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OccupancyResponse(assignedPremium.size(), revenuePremium.setScale(2, java.math.RoundingMode.HALF_UP),
                assignedEconomy.size(), revenueEconomy.setScale(2, java.math.RoundingMode.HALF_UP));
    }
}
