# DivergerTestApp

Este proyecto es una aplicación Spring Boot que utiliza Maven como herramienta de construcción. La aplicación está dockerizada y se puede levantar con Docker. Además, se incluye SonarQube para el análisis de calidad del código.

## Desarrollo
Tiene todos mis pensamientos y decisiones que he tomado durante el desarrollo de este proyecto en el archivo `COMMENTS.md`.

## Swagger

La documentación de la API está disponible a través de Swagger. Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz de usuario de Swagger en `http://localhost:8080/swagger-ui.html`.

## Levantar el proyecto

Para levantar el proyecto, necesitas tener Docker instalado en tu máquina. Una vez que Docker esté instalado, puedes usar el archivo `Makefile` incluido en el proyecto para construir y ejecutar el contenedor Docker.

```bash
make up
```
Tambien puedes ejecutar el Dockerfile directamente con el siguiente comando:

```bash
docker build -t swapi-proxy .
docker run --rm -d -p 8080:8080 --name swapi-proxy swapi-proxy:latest
```

Este comando construirá la imagen Docker y luego ejecutará el contenedor. La aplicación estará disponible en `http://localhost:8080`.

## Uso del Makefile

El archivo `Makefile` incluido en el proyecto proporciona varios comandos para facilitar la construcción, ejecución y prueba del proyecto.

- `make build`: Construye la imagen Docker para la aplicación.
- `make up`: Construye la imagen Docker (si aún no se ha construido) y luego ejecuta el contenedor.
- `make down`: Detiene el contenedor Docker en ejecución.
- `make tests`: Ejecuta las pruebas del proyecto utilizando Maven.
- `make sonar`: Ejecuta un análisis de SonarQube en el proyecto.

## SonarQube

Para levantar el contenedor de SonarQube, puedes usar el siguiente comando de Docker:

```bash
cd sonarqube
docker-compose up -d
```
Las credenciales por defecto para acceder a SonarQube son:
- Usuario: admin
- Pass: admin


Recuerda que necesitas el tocken de SonarQube para ejecutar el análisis. Puedes obtener el token en la configuración de tu cuenta de SonarQube.
Incluyendo la flag `-Dsonar.login=<token>` en el comando de Maven, puedes ejecutar el análisis de SonarQube en el proyecto.

Una vez que SonarQube esté en ejecución, puedes lanzar el análisis de SonarQube en el proyecto utilizando el comando `make sonar` del `Makefile`.


```bash
make sonar 
```
o
    
```bash
    mvn sonar:sonar -Dsonar.login=<token>
```

Después de ejecutar el análisis, puedes ver los resultados en `http://localhost:9000`.