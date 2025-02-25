package co.edu.eci.arep;

import co.edu.eci.arep.annotation.GetMapping;
import co.edu.eci.arep.annotation.RestController;
import co.edu.eci.arep.annotation.RequestParam;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class MicroSpringBoot {
    // Mapea las rutas definidas en los métodos anotados con @GetMapping
    private static final Map<String, Method> routeMapping = new HashMap<>();
    // Almacena instancias de los controladores registrados
    private static final Map<Class<?>, Object> controllerInstances = new HashMap<>();

    // Registra el controlador si tiene la anotación @RestController
    public static void registerController(Class<?> controllerClass) throws Exception {
        if (!controllerClass.isAnnotationPresent(RestController.class)) {
            System.out.println(controllerClass.getName() + " no está anotada con @RestController");
            return;
        }
        Object instance = controllerClass.getDeclaredConstructor().newInstance();
        controllerInstances.put(controllerClass, instance);

        for (Method method : controllerClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(GetMapping.class)) {
                String route = method.getAnnotation(GetMapping.class).value();
                routeMapping.put(route, method);
                System.out.println("Registrado route: " + route + " -> " + controllerClass.getName() + "." + method.getName());
            }
        }
    }

    // Invoca el método correspondiente a la ruta dada y le pasa los parámetros obtenidos de la query
    public static Object handle(String route, Map<String, String> queryParams) throws Exception {
        Method method = routeMapping.get(route);
        if (method == null) {
            return "{\"message\": \"404 Not Found\"}";
        }
        Object controller = controllerInstances.get(method.getDeclaringClass());
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            RequestParam rp = parameters[i].getAnnotation(RequestParam.class);
            if (rp != null) {
                String value = queryParams.getOrDefault(rp.value(), rp.defaultValue());
                args[i] = value; // Se asume que los parámetros son de tipo String para simplificar
            }
        }
        Object result = method.invoke(controller, args);
        // Se asume que el método retorna String ya formateado en JSON
        return result;
    }
}
