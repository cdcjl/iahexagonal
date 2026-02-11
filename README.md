# Plantilla Hexagonal Spring Boot

Estructura mínima para comenzar con Arquitectura Hexagonal (Ports & Adapters) usando Spring Boot.

Estructura principal:
- domain: lógica de negocio y puertos (interfaces)
- application: casos de uso / DTOs
- adapters/in: controladores web (REST)
- adapters/out: persistencia / adaptadores externos

Comandos rápidos:

```bash
mvn -U package
java -jar target/iahexagonal-0.0.1-SNAPSHOT.jar
```

Sugerencias: abrir [src/main/java/com/example/hexagonal/HexagonalApplication.java](src/main/java/com/example/hexagonal/HexagonalApplication.java) y revisar los adaptadores.
