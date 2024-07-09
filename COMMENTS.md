# 💻 Desafío Técnico  💻

Este es un archivo markdown. Puedes ver el contenido en esta página web: https://markdownlivepreview.com

## 📝 Introducción 📝
¿Qué es esto?
Esta es la parte en la que expondré todos mis pensamientos y decisiones que he tomado durante el desarrollo de este proyecto.

Todos los pasos seguidos en orden cronológico, por lo tanto, no es un manual que explique paso a paso qué hacer sino una guía de cómo he llegado a la solución y a lo largo de todo el proceso ciertas decisiones tomadas pueden cambiar.

## 📚 Arquitectura y Diseño 📚
Tras las primeras entrevistas con recursos humanos, he llegado a la conclusión de que tanto la arquitectura hexagonal como el diseño DDD son muy importantes para el proyecto, por eso este proyecto seguirá estas arquitecturas.

## 🚶‍♂️ Primeros Pasos 🚶‍♂️
He visto que se me pide crear un proxy para la API de StarWars Swapi y comenzando a pensar en cómo voy a estructurar el dominio, creo que la mejor opción será crear las siguientes clases:
- Character (El personaje de StarWars)
- Film (La película de StarWars)
- Planet (El planeta de StarWars)
- Vehicle_Base (La base de los vehículos de StarWars)
- Vehicle hereda de Vehicle_Base (Los vehículos terrestres de StarWars)
- Starship hereda de Vehicle_Base (Las naves de StarWars)

Para este caso en concreto no sería necesario crear las clases hijas pero si buscamos crear un modelo de datos que sea escalable y fácil de mantener en el futuro es mejor hacerlo así.

## Estructura
Iré creando la estructura de paquetes y clases a medida que vaya avanzando en el desarrollo del proyecto.
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

## 🌐 Interacción con la API 🌐
Acabo de encontrar el primer problema en el desarrollo. Leyendo la documentación de la API, parece que no hay un endpoint que me devuelva la información de un personaje en concreto mediante su nombre.
Pero revisando su documentación, tienen habilitado el SearchFilter de Django por lo que puedo hacer una petición a la URL de la API y filtrar por el nombre del personaje. (https://swapi.dev/documentation#search)

Por lo tanto, la petición que voy a hacer será la siguiente:
```
https://swapi.dev/api/people/?search=Luke%20Skywalker
```
## 🚫 Error con la API 🚫
Estoy tratando de usar RestTemplate para hacer la petición a la API pero me está dando un error de que no se puede conectar a la API.
Al parecer, la página web de la API no ha renovado su certificado SSL y por lo tanto no puedo hacer peticiones a la API.
Mientras busco una solución a este problema, voy a seguir con el desarrollo del proyecto por otras partes creando los DTOs y las funciones necesarias para hacer el mapeo de los datos.

## 📚 Creación de los DTOs 📚
He decidido usar el patrón DTO para hacer el mapeo de los datos de la API a los objetos de mi aplicación.
Solo voy a necesitar 2 DTOs para hacer el mapeo, uno para la lista de películas y otro para el personaje, ya que otros atributos como el planeta o los vehículos no son necesarios enviarlos al cliente.

## 🚨 Sistema de Errores y Excepciones 🚨
He creado un pequeño sistema de errores y excepciones para manejar los errores que puedan surgir en la aplicación y crear mis propias excepciones personalizadas.
Es un sistema sencillo pero que puede ser escalable y fácilmente mantenible en el futuro.
En este caso, creo que las excepciones más importantes que pueden surgir son:
- Personaje no encontrado
- Error en la petición a la API
- Más de un personaje encontrado con ese nombre

## 🛠️ Creación de Servicios y Controladores 🛠️
Creando el endpoint para la API y el servicio que recoge los datos.
Ya que en este caso no estamos usando una base de datos, la mayor parte de la lógica del microservicio se encuentra en una parte externa a la aplicación que sería toda la interacción con la API de StarWars.

Lo único reseñable del servicio sería que realizamos un mapeo a los DTOs que hemos creado anteriormente para no enviar al cliente los modelos completos.

El endpoint está cacheado con el propio sistema de Spring para que no haga peticiones a la API cada vez que se haga la misma petición al endpoint.

## 🌐 Interacción con la API 🌐
Primeramente, he realizado toda la lógica en una sola clase ya que no pensaba que iba a ser tan extensa, pero tras realizar toda la funcionalidad he llegado a la conclusión de que no estaba aplicando correctamente el principio de responsabilidad única.
Por lo que he decidido dividir la lógica en varias clases continuando con algo que he tratado de aplicar durante el desarrollo que son los principios SOLID.

En todo momento he tratado de que sea escalable y que si en el futuro se quisieran usar más campos del personaje, incluir la búsqueda de naves o cualquier otra funcionalidad sea fácil de implementar y se pueda reutilizar gran parte del código.
Para hacer esto he utilizado en varias de las funciones tipos genéricos.

## 🚫 Solución al Problema de la API 🚫
Al momento de terminar la prueba, la API sigue sin funcionar correctamente por lo que no puedo hacer peticiones a la API de StarWars.
Podría haber utilizado otra manera de obtener los datos o tratar de deshabilitar la verificación del certificado SSL pero son cosas que me llevarían más tiempo del que me llevaría el resto de la prueba y creo que no es el objetivo de la prueba.

En su lugar, estoy utilizando https://swapi.py4e.com que es una réplica de la API de StarWars y funciona correctamente.
Por lo que me he tomado la libertad de cambiar la URL de la API en el archivo de propiedades.

## 📝 Comentarios Finales 📝
Soy consciente de que hay muchas cosas que se podrían mejorar en este proyecto pero no dispongo de más tiempo para realizarlas sin tenerles esperando mi respuesta.

También incluir que debido a los problemas que he tenido con la API no he podido hacer los commits pertinentes para que se fuera viendo el progreso del proyecto.
Espero que con mis comentarios en este documento se pueda ver el progreso y las decisiones que he tomado durante el desarrollo del proyecto.

Los últimos ajustes que he hecho han sido dockerizar la aplicación e incluir sonarqube para hacer un análisis de calidad del código.
He incluido un archivo makefile para facilitar la ejecución de la aplicación, dockerización y el uso de sonarqube.