package com.example.hotel.model;

import java.math.BigDecimal;

public record OccupancyResponse(int usagePremium, BigDecimal revenuePremium, int usageEconomy, BigDecimal revenueEconomy) {}
