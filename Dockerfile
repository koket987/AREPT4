FROM openjdk:17

WORKDIR /usrapp/bin

ENV PORT 35000

# Copia las clases y dependencias
COPY target/classes /usrapp/bin/classes
COPY target/dependency /usrapp/bin/dependency

# Copia los archivos estáticos (index.html y otros) a la ruta correcta
COPY src/main/resources/www /usrapp/bin/www

CMD ["java", "-cp", "./classes:./dependency/*", "co.edu.eci.arep.HttpServer", "co.edu.eci.arep.GreetingController"]
