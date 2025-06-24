package com.example.hotel.model;

import java.math.BigDecimal;
import java.util.List;

public record OccupancyRequest(int premiumRooms, int economyRooms, List<BigDecimal> potentialGuests) {}
