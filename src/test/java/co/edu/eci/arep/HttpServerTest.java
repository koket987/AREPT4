package co.edu.eci.arep;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServerTest {

    private static final String SERVER_URL = "http://localhost:35000";
    private static Process serverProcess;

    @BeforeAll
    static void setup() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", "target/classes", "co.edu.eci.arep.HttpServer", "co.edu.eci.arep.GreetingController"
        );
        processBuilder.inheritIO();
        serverProcess = processBuilder.start();

        // Esperar para que el servidor se inicie
        Thread.sleep(3000);
    }

    @Test
    void testHelloEndpoint() throws IOException {
        URL url = new URL(SERVER_URL + "/App/rests/greeting?name=Juan");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode(), "Error: Código de respuesta no es 200");
        assertEquals("{\"message\": \"Hola Juan\"}", readResponse(conn), "Error: Respuesta incorrecta del servidor");
    }

    @Test
    void testPiEndpoint() throws IOException {
        URL url = new URL(SERVER_URL + "/App/rests/pi");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode(), "Error: Código de respuesta no es 200");
        assertEquals(String.valueOf(Math.PI), readResponse(conn), "Error: Valor de PI incorrecto");
    }

//    @Test
//    void testStaticFileServing() throws IOException {
//        URL url = new URL(SERVER_URL + "/index.html");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//
//        assertEquals(200, conn.getResponseCode(), "Error: Código de respuesta no es 200");
//        String response = readResponse(conn);
//        assertTrue(response.contains("<html>") || response.contains("<!DOCTYPE html>"), "Error: No se sirvió correctamente el archivo estático");
//    }

    @AfterAll
    static void tearDown() {
        if (serverProcess != null) {
            serverProcess.destroyForcibly();
            System.out.println("Servidor detenido por la fuerza.");
        }
    }


    private String readResponse(HttpURLConnection conn) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
