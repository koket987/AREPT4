# Usa una imagen base con OpenJDK 17
FROM openjdk:17

# Define el directorio de trabajo dentro del contenedor
WORKDIR /usrapp/bin

# Variable de entorno para el puerto
ENV PORT 35000

# Copia las clases y dependencias al contenedor
COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

# Comando para ejecutar el servidor
CMD ["java", "-cp", "./classes:./dependency/*", "co.edu.eci.arep.HttpServer", "co.edu.eci.arep.GreetingController"]
