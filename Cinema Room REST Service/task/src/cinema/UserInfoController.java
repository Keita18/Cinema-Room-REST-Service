package cinema;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class UserInfoController {


    @PostMapping("/user")
    public String userStatus(@RequestBody UserInfo user) {
        if (user.isEnabled()) {
            return String.format("Hello! %s. Your account is enabled.", user.getName());
        } else {
            return String.format("Hello! Nice to see you, %s! Your account is disabled",
                    user.getName());
        }
    }



    @PostMapping("/users")
    public String userStatus(@RequestBody List<UserInfo> userList) {
        return String.format("Added %d users", userList.size());
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
