package co.edu.eci.arep;

import co.edu.eci.arep.annotation.GetMapping;
import co.edu.eci.arep.annotation.RestController;

@RestController

public class PiController {

    @GetMapping("/pi")
    public static String getPi() {
        return String.valueOf(Math.PI);
    }
}
