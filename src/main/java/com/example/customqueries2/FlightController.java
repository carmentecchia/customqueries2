package com.example.customqueries2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")

public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepository flightRepository;
    @PostMapping("/add")
    public ResponseEntity<List<Flight>> addFlights(){
        return ResponseEntity.ok().body(flightService.addFlights());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok().body(flightRepository.findAll());
    }


    @PostMapping("/toairport/{input}")
    public ResponseEntity<List<Flight>> toAirport(@PathVariable int input){
        return ResponseEntity.ok().body(flightService.addFlightsWithInput(input));
    }

    @GetMapping("/fromairport")
    public ResponseEntity<Page<Flight>> flightsFromAirport(){
        Page<Flight> response = flightRepository.retrieveAllFlightsOrdered(PageRequest.of(0,10));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/ontime")
    public  ResponseEntity<List<Flight>> getFlightsOnTime(){
        return ResponseEntity.ok().body(flightRepository.retrieveAllFlightsInTime());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam (name = "p1") String p1, @RequestParam (name = "p2") String p2){
        return ResponseEntity.ok().body(flightRepository.retrieveFlightsWithP1AndP2(p1, p2));
    }
}