# _💻 Thinking Process Technical Challenge 💻_

This is a markdown file you can see the content on this webpage: https://markdownlivepreview.com

## 📝 Introduction 📝
Que es esto?
Esto es la parte en la que expondre todos mis pensamientos y decisiones que he tomado durante el desarrollo de este proyecto.

Todos los pasos seguidos en orden cronologico, por lo tanto no es un manual que explique al pie de la letra los pasos a seguir sino una guia de como he llegado a la solucion y a lo largo de todo el proceso ciertas decisiones tomadas pueden cambiar.

## 📚 Arquitectura y Diseño 📚
Tras las primeras entrevistas con recusos humanos he llegado a la conclusion de que tanto la arquitectura hexzagonal como el diseño DDD son muy importantes para el proyecto por eso este proyecto va a seguir estas arquitecturas.

## Primeros pasos 🚶‍♂️
He visto que se me pide crear un proxy para la api de StarWars Swapi y comenzando a pensar en como voy a estructurar el dominio creo que la mejor opcion sera crear las siguientes clases:
- Character (El personaje de StarWars)
- Film (La pelicula de StarWars)
- Planet (El planeta de StarWars)
- Vehicle_Base (La base de los vehiculos de StarWars)
- Vehicle hereda de Vehicle_Base (Los vehiculos terrestres de StarWars)
- Starship hereda de Vehicle_Base (Las naves de StarWars)

Para este caso en concreto no seria necesario crear las clases hijas pero si buscamos crear un modelo de datos que sea escalable y facil de mantener en el futuro es mejor hacerlo asi.

## Estructura
Ire creando la estructura de paquetes y clases a medida que vaya avanzando en el desarrollo del proyecto.
Pero por ahora la estructura que tengo en la cabeza es la siguiente:
```
com
    └── jars
        └── DivergerTestApp
            ├── application
            │   ├── dto
            │   ├── exception
            │   ├── service
            │   └── controller
            ├── domain
            │   └── model
            ├── infrastructure
            │   ├── config
            │   └── external
            └── StarWarsApplication.java
```

## Interaccion con la API
Acabo de encontrar el primer problema en el desarrollo. Leyendome la documentacion de la API al parecer no hay un endpoint que me devuelva la informacion de un personaje en concreto mediante su nombre.
Pero revisando su documentacion tienen habilitado el SearchFilter de Django por lo que puedo hacer una peticion a la url de la API y filtrar por el nombre del personaje. (https://swapi.dev/documentation#search)

Por lo tanto la peticion que voy a hacer sera la siguiente:
```
https://swapi.dev/api/people/?search=Luke%20Skywalker
```

