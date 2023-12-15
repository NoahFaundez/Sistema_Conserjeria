# Usa una imagen base con Java
FROM openjdk:18-jdk-slim

# Establece el directorio de trabajo
WORKDIR /usr/app

# Copia los archivos de Gradle primero para aprovechar el almacenamiento en caché
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Copia el resto de los archivos
COPY . .

# Construye la aplicación con Gradle
RUN ./gradlew clean build

# Expone el puerto en el que se ejecuta tu aplicación (ajusta según sea necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["./gradlew", "run"]