package co.edu.eci.arep;

import co.edu.eci.arep.annotation.RestController;
import co.edu.eci.arep.annotation.GetMapping;
import co.edu.eci.arep.annotation.RequestParam;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // Se retorna una cadena JSON con la respuesta
        return "{\"message\": \"Hola " + name + "\"}";
    }
}
