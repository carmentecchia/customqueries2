package com.example.customqueries2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> addFlights(){
        List<Flight> newListFlight = new ArrayList<>();
        for(int i= 1 ; i<51 ; i++){
            Flight flight = new Flight();
            flight.setDescription(getRandomString());
            flight.setToAirport(getRandomString());
            flight.setFromAirport(getRandomString());
            flight.setFlightStatus(FlightStatus.ONTIME);
            newListFlight.add(flight);
        }
        return flightRepository.saveAll(newListFlight);
    }
    public List<Flight> addFlightsWithInput(Integer input){
        List<Flight> newListFlight = new ArrayList<>();
        if(input != null){
            for(int i= 1 ; i<=input ; i++){
                Flight flight = new Flight();
                flight.setDescription(getRandomString());
                flight.setToAirport(getRandomString());
                flight.setFromAirport(getRandomString());
                flight.setFlightStatus(getRandomStatusType());
                newListFlight.add(flight);
            }
        }else {
            for(int i= 1 ; i<=100 ; i++){
                Flight flight = new Flight();
                flight.setDescription(getRandomString());
                flight.setToAirport(getRandomString());
                flight.setFromAirport(getRandomString());
                flight.setFlightStatus(getRandomStatusType());
                newListFlight.add(flight);
            }
        }
        return flightRepository.saveAll(newListFlight);
    }

    public String getRandomString(){
        Random random = new Random();
        return  random.ints(50,44)
                .limit(8)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }
    public FlightStatus getRandomStatusType(){
        Random random = new Random();
        Integer value = random.ints(1,4).limit(1).findFirst().getAsInt();
        FlightStatus getStatusType = null;
        switch (value){
            case 1:
                getStatusType = FlightStatus.ONTIME;
                break;
            case 2:
                getStatusType = FlightStatus.DELAYED;
                break;
            case 3:
                getStatusType = FlightStatus.CANCELLED;
                break;
        }
        return getStatusType;
    }
}
