package cinema;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CinemaRoomController {
    private final List<CinemaSeat> list = new ArrayList<>();

    public CinemaRoomController() {
        cinemaSeatList();
    }

    private void cinemaSeatList() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                var cinema = new CinemaSeat(i, j);
                cinema.purchased = false;
                cinema.token = UUID.randomUUID().toString();
                list.add(cinema);
            }
        }
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        List<CinemaSeat> currentList = list.stream()
                .filter(it -> !it.purchased)
                .collect(Collectors.toList());
        return new CinemaRoom(9, 9, currentList);
    }


    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase(@RequestBody CinemaSeat seat) {

        if (seat.getRow() > 9 || seat.getColumn() > 9 ||
                seat.getRow() < 1 || seat.getColumn() < 1) {
            return new ResponseEntity<> (
                    Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        for (CinemaSeat cinemaSeat : list) {
           if (cinemaSeat.getRow() == seat.getRow()
                    && cinemaSeat.getColumn() == seat.getColumn()) {
               if (!cinemaSeat.purchased) {
                   cinemaSeat.purchased = true;
                   var ticket = Map.of("token", cinemaSeat.token, "ticket", cinemaSeat);
                   return new ResponseEntity<>(ticket, HttpStatus.OK);
               }
            }
        }
        return new ResponseEntity<> (
                Map.of("error", "The ticket has been already purchased!"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returned(@RequestBody TreeMap<String, String> token) {
        var _token = token.firstEntry().getValue();
        for (CinemaSeat seat : list) {
            if (Objects.equals(seat.token, _token)) {
                seat.purchased = false;
                var returnedTicket = Map.of("returned_ticket", seat);
                seat.token = UUID.randomUUID().toString();
                return new ResponseEntity<>(returnedTicket, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(
                Map.of("error", "Wrong token!"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> stats(@RequestParam(required = false) String password) {
        var pass = Objects.equals(password, "super_secret");
        if (pass) {
            int currentIncome, numAvailSeat, purTicket;
            currentIncome = purTicket = 0;
            numAvailSeat = 81;
            for (CinemaSeat seat : list) {
                if (seat.purchased) {
                    numAvailSeat--;
                    purTicket++;
                    currentIncome += seat.getPrice();
                }
            }
            return new ResponseEntity<>(
                    Map.of("current_income", currentIncome,
                            "number_of_available_seats", numAvailSeat,
                            "number_of_purchased_tickets", purTicket),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                Map.of("error", "The password is wrong!"),
                HttpStatus.UNAUTHORIZED);
    }
}
