package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRoomController {

    List<CinemaSeat> cinemaSeatList() {
        List<CinemaSeat> list = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                list.add(new CinemaSeat(i, j));
            }
        }

        return list;
    }

    CinemaRoom cinemaRoom() {
        return new CinemaRoom(9, 9, cinemaSeatList());
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom();
    }
}
