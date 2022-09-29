package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaRoomController {
    private final List<CinemaSeat> list = new ArrayList<>();
    private void cinemaSeatList() {

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                var price = 8;
                if (i <= 4)
                    price = 10;
                list.add(new CinemaSeat(i, j, price));
            }
        }
    }
    public CinemaRoomController() {
        cinemaSeatList();
    }

    CinemaRoom cinemaRoom() {
        return new CinemaRoom(9, 9, list);
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom();
    }


    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase(@RequestBody CinemaSeat cinemaSeat) {

        if (cinemaSeat.getRow() > 9 || cinemaSeat.getColumn() > 9 ||
                cinemaSeat.getRow() < 1 || cinemaSeat.getColumn() < 1) {
            return new ResponseEntity<> (
                    Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);

        }
        for (CinemaSeat _cinemaSeat1 : list) {
           if (_cinemaSeat1.getRow() == cinemaSeat.getRow()
                    && _cinemaSeat1.getColumn() == cinemaSeat.getColumn()) {
                list.remove(_cinemaSeat1);
                return new ResponseEntity<>(_cinemaSeat1, HttpStatus.OK);
            }
        }
        return new ResponseEntity<> (
                Map.of("error",
                        "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }


    private final ConcurrentHashMap<String, String> addressList = new ConcurrentHashMap<>();

    @GetMapping("/address")
    public ConcurrentHashMap<String, String> getAddress() {
        return addressList;
    }

    @PostMapping("/address")
    public void postAddress(@RequestParam String name, @RequestParam String address) {
        addressList.put(name, address);
    }

    @DeleteMapping("/address")
    public void deleteAddress(@RequestParam String name) {
        addressList.remove(name);
    }
}
