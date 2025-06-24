package com.example.hotel.controller;

import com.example.hotel.model.OccupancyRequest;
import com.example.hotel.model.OccupancyResponse;
import com.example.hotel.service.OccupancyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/occupancy")
public class OccupancyController {
    private final OccupancyService service;

    public OccupancyController(OccupancyService service) {
        this.service = service;
    }

    @PostMapping
    public OccupancyResponse calculate(@RequestBody OccupancyRequest request) {
        return service.calculateOccupancy(
                request.premiumRooms(),
                request.economyRooms(),
                request.potentialGuests()
        );
    }
}
