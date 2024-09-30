Explicaciòn del primer punto
a)

En primer lugar, se debe crear y correr el contenedor de MySQL , esto se hace con el comando: docker run --name yms-mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=yms -e MYSQL_USER=yms_user -e MYSQL_PASSWORD=yms_clave -p 3306:3306 -d mysql:8.0

Una vez creado el contenedor , se puede ejecutar el aplicativo con el siguiente comando ( ./gradlew bootRun) o bien, corriendolo de forma normal desde el IDE Intellij




Explicaciòn para crear la imagen de docker y ejecutarla (punto 5):

Dirigiendonos a la ruta donde està el archivo Dockerfile, se escribe el comando: docker build -t parcial-app .

Esto construye la imagen, para ejecutarla se usa el comando:  docker run -p 8082:8082 parcial-app





