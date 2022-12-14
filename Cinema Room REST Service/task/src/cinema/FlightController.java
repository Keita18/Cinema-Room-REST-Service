package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class FlightController {

    private final List<FlightInfo> flightInfoList = new ArrayList<>();

    public FlightController() {
        flightInfoList.add(
                new FlightInfo(1, "Delhi Indira Gandhi", "Stuttgart", "D80"));
        flightInfoList.add(
                new FlightInfo(2, "Tokyo Haneda", "Frankfurt", "110"));
        flightInfoList.add(
                new FlightInfo(3, "Berlin Schönefeld", "Tenerife", "15"));
        flightInfoList.add(
                new FlightInfo(4, "Kilimanjaro Arusha", "Boston", "15"));
    }

    @GetMapping("flights/{id}")
    public FlightInfo getFlightInfo(@PathVariable int id) {
        for (FlightInfo flightInfo : flightInfoList) {
            if (flightInfo.getId() == id) {
                if (Objects.equals(flightInfo.getFrom(), "Berlin Schönefeld")) {
//                  " server.error.include-message=always " to enable message in spring
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Berlin Schönefeld is closed for service today");

                } else {
                    return flightInfo;
                }
            }
        }

        throw new FlightNotFoundException("Flight not found for id:");
    }

}