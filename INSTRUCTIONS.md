# Diverger Test App - Java Full Stack Webapp

Bienvenid@!

A Diverger le ha llegado la información de que eres la persona ideal para que les eches un cable. En la actualidad no disponen de gente suficiente en su equipo, y necesitan realizar un pequeño y curioso desarrollo.

Se trata de escribir un API RESTFul Java basado en Spring Boot que proporcione información sobre personajes de la saga de Star Wars permitiendo su visualización a través de una web basada en ReactJS. Fácil no?

Es importante que sepas que una vez que lo deas por terminado, ya nos han indicado que en 6 meses querrán algunos cambios, por lo que confiamos plenamente en tu buen hacer para en ese momento poder retomar tu código y rápidamente asimilarlo para llevar a cabo esas actualizaciones.

Muchas gracias por tu inestimable ayuda y estamos en tus manos!

## Fundamentos

### Proyecto backend

* El API consumirá a su vez otros endpoint REST de los que obtener la información. En concreto, se apoyará en los servicios que puedes encontrar en `Swapi`: `https://swapi.dev/`
* Utilizando los endpoints necesarios, se pide la creación de un interfaz REST aceptando una única petición del tipo:

```sh
http://{host}:{port}/swapi-proxy/person-info?name=Luke%20Skywalker
```

* Ante una peticición de este tipo, la aplicación deberá retornar un JSON conteniendo la siguiente información y estructura:

```json
{
  "name": "Luke Skywalker",
  "birth_year": "19BBY",
  "gender": "male",
  "planet_name": "Tatooine",
  "fastest_vehicle_driven": "X-wing",
  "films": [
    {
      "name": "A New Hope",
      "release_date": "1977-05-25"
    },
    {
      "name": "The Empire Strikes Back",
      "release_date": "1980-05-17"
    },
    {
      "name": "Return of the Jedi",
      "release_date": "1983-05-25"
    },
    {
      "name": "Revenge of the Sith",
      "release_date": "2005-05-19"
    },
    {
      "name": "The Force Awakens",
      "release_date": "2015-12-11"
    }
  ]
}
```

* Obviamente, la aplicación deberá funcionar para cualquiera de los nombres de personaje incluídos en `Swapi`, y ante un nombre incorrecto, debería responder con un JSON de error y un código `404`.
* El atributo fastest_vehicle_driven debe ser rellenado con el nombre del "vehicle" o "starship" más rápido (con mayor `max_atmosphering_speed`) conducido por el personaje.

### Proyecto frontend

* La parte web dispondrá de al menos una vista para visualizar la lista de personajes.
* Para cada uno de los personajes listados deberá mostrarse su información en el formato que mejor se adapte (Card, List, ...)
* Idealmente, la aplicación deberá contar con paginación y se valorará positivamente que disponga de un buscador para filtrar y buscar personajes de la saga.

## Valoración
* Ambos proyectos deberían estar almacenados en un repositorio de GitHub o Bitbucket.
* Esperamos poder ver los distintos commits realizados hasta llegar a obtener la solución final
* Esperamos recibir el código fuente de las aplicaciones y las instrucciones para su build y ejecución (README.md).
* Claridad y modularidad del código fuente.
* Uso de cachés
* Inclusión de testing
* Dockerización del proyecto
* En el proyecto de backend:
    * La aplicación debe ser construída con un JAR ejecutable
    * Idealmente se debe utilizar `Gradle` para la construcción de la API del proyecto (también aceptaríamos `Maven`)
    * Inclusión de documentación para la API (utilizando swagger por ejemplo)
* En el proyecto de frontend:
    * Idealmente se debe utilizar `create-react-app` para la construcción del web (también aceptariamos una configuración personalizada utilizando NPM, Babel y Webpack)
    * Uso de hooks y componentes reutilizables tanto propios como de librerías publicadas en el registry de npm
    * Inclusión de documentación para la parte web (pudiendo utilizar alguna librería de automatización como docz o storybook)

Y lo más importante **apreciamos y agradecemos mucho tu tiempo y esfuerzo**. No te sientas presionado si por el motivo que sea no consigues el resultado final. Valoramos de manera importante el proceso que hayas seguido y todos los pasos y avances que hayas podido tener, por lo que haznos llegar igualmente todo lo que hayas generado/codificado y lo tendremos igualmente en cuenta. Ánimo!

## Método de entrega del ejercicio
* Deberás subir tu solución a una cuenta de github y nuestro equipo procederá a la revisión del código
* Para ello, añade como contributors del proyecto a los siguientes usuarios a `jorge-diverger`, `diegogrod` y `cesar-diverger`
* Crea una pull request y añádenos como revisores de manera que podamos revisar el código
* Tan pronto recibamos la solicitud, te contestaremos con un correo electrónico de que hemos recibido la PR.
* Cuando procedamos con la revisión de la misma, podrías recibir algún comentario en la propia PR.
* De todas formas lo más interesante es que puedas compartir todo tu razonamiente y las decisiones que has tomado con parte de nuestro equipo técnico, para lo cuál te propondremos una sesión de 30min en un hueco que te venga bien y así aprovechas para conocer a parte del equipo y charlar sobre la prueba