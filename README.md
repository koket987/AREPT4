# Proyecto: Servidor Web Concurrente en AWS con Docker y EC2

## Descripción del Proyecto
Este proyecto consiste en la implementación de un servidor web en Java, utilizando un framework propio (NO SPRING), que permite manejar peticiones REST y servir archivos estáticos. El servidor ha sido mejorado para soportar concurrencia y apagado elegante. Se despliega en AWS utilizando una instancia EC2 y Docker.

## Arquitectura del Proyecto

### ACLARACION
**(Si deseas correr este proyecto en tu maquina fisica solo debes cambiar en la ruta estatica de los archivos
Por: src/main/resources/www)**

### Tecnologías Utilizadas
- **Lenguaje:** Java
- **Framework Propio:** Implementado para manejar rutas y respuestas HTTP
- **Servidor Web:** Implementado desde cero sin uso de Spring
- **Contenerización:** Docker
- **Cloud Provider:** AWS EC2

### Componentes Principales
1. **Servidor HTTP**: Maneja peticiones GET y POST, y permite servir archivos estáticos desde un directorio predefinido.
2. **Controladores REST**: Se registran mediante el framework propio y permiten definir endpoints dinámicos.
3. **Manejo de Concurrencia**: Se mejoró la gestión de conexiones concurrentes usando hilos.
4. **Apagado Elegante**: Se agregó un endpoint para apagar el servidor de forma segura mediante una petición HTTP con autenticación.

## Diseño de Clases

### 1. **HttpServer**
Clase principal que inicia el servidor y gestiona peticiones HTTP.

### 2. **MicroSpringBoot**
Clase que maneja el registro y la ejecución de controladores REST.

### 3. **PiController**
Ejemplo de controlador REST que calcula valores de Pi.

### 4. **RequestHandler**
Manejo de peticiones concurrentes en hilos.

## Instrucciones de Instalación y Despliegue

### 1. Clonar el Repositorio
```sh
 git clone https://github.com/koket987/AREPT4
 cd AREPT4
```

### 2. Construir la Imagen de Docker
```sh
ocker build --tag dockerminispring .
```

### 3. Ejecutar en Local con Docker
- Crearemos un container para poder ejecutarlo
```sh
docker run -d -p 34000:35000 --name container1 dockerminispring
```

### 4. Configurar AWS EC2
- Antes de todo necesitamos subir el docker a docker hub ya despues podemos proceder
  Con lo que sigue(no se incluye el archivo docker-compose)
  
- Crear una instancia en AWS EC2 con un sistema operativo basado en Linux.
- Instalar Docker en la instancia:
```sh
sudo yum update -y
sudo yum install docker
sudo service docker start
sudo usermod -a -G docker ec2-user
```
### 5. Descargar y ejecutar la imagen

```sh
docker run -d -p 42000:6000 --name firstdockerimageaws usuariodedocker/nombredelrepo
```

## Pruebas y Resultados
- Acceder al servidor en `http://ip-publica`
- Probar endpoints REST en `http://ip-publica/App/rests/pi`
- Apagar el servidor enviando una petición GET a `http://ip-publica/App/shutdown?password=AREPTALLER`

## Capturas de Pantalla
![image](https://github.com/user-attachments/assets/b7bccab9-dd9a-48b3-a9b6-bdd53dd9edad)
![image](https://github.com/user-attachments/assets/fe69f43b-292c-4c99-9bee-ed2e9d2aebf1)
![image](https://github.com/user-attachments/assets/2b58f543-19eb-495d-a32d-46822c41b800)
![image](https://github.com/user-attachments/assets/2c76ecdd-54de-4d94-8fb7-aade6aa62d53)
![image](https://github.com/user-attachments/assets/e5d8d142-10c9-45e4-8a7c-7e889a1d737d)

## Construido Con
- **Java** - Lenguaje principal utilizado.
- **Maven** - Para la gestión de dependencias.
- **JUnit** - Para pruebas automatizadas.

## Contribuciones
Siéntete libre de hacer fork y enviar pull requests para mejorar el proyecto.

## Autor
* **Santiago** - *Trabajo inicial* - [GitHub Personal](https://github.com/koket987)

## Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos
- Inspiración de implementaciones de servidores web minimalistas.
- Comunidad de código abierto por las mejores prácticas.



