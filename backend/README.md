# Academy Backend — instrucciones rápidas

This README explains how to run the backend, access Swagger/OpenAPI UI and the H2 console, and the recommended Java/Gradle setup.

## Versión detectada
- Gradle wrapper: 8.5 (archivo `gradle/wrapper/gradle-wrapper.properties`)
- `build.gradle` está configurado para usar Java 17 toolchain (reproducible)
- Spring Boot: 3.2.0

## Recomendación de versión de Java
- Recomendado: Java 17 (LTS). Es estable, probado con Spring Boot 3.2.x y es la configuración por defecto del proyecto.
- Opcional: Java 21 es soportado por Spring Boot 3.2, pero sólo cámbiala si quieres usar nuevas características del lenguaje y tienes JDK 21 instalado en tu máquina.

Si quieres cambiar a Java 21, edita `build.gradle` y cambia el toolchain a 21:

```groovy
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

Y luego asegúrate de que tu JDK 21 esté instalado y en PATH, o que Gradle pueda usarlo.

## Cómo construir y ejecutar
Desde PowerShell en Windows (directorio `backend`):

- Construir JAR (usa el wrapper incluido):
```powershell
cd .\backend
.\gradlew.bat clean build
```

- Ejecutar la aplicación con el JAR generado:
```powershell
cd build\libs
java -jar academy-backend-0.0.1-SNAPSHOT.jar
```

- Alternativa para desarrollo (arranca desde Gradle):
```powershell
.\gradlew.bat bootRun
```

Si `gradlew.bat` no se ejecuta en tu entorno, asegúrate de ejecutar PowerShell como administrador o ejecutar `.uild	ools
un` con Java instalado.

## Endpoints útiles
- Swagger / OpenAPI UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:academydb`
  - User: `SA`
  - Password: (vacío)

## Frontend
El frontend está en `../frontend`.
Para arrancarlo:
```powershell
cd ..\frontend
npm install
npm start
```
La app se abrirá en http://localhost:3000 y usa los endpoints del backend.

## Notas rápidas
- El proyecto está fijado a Java 17 para compatibilidad y reproducibilidad.
- Gradle wrapper (8.5) es compatible con Java 17 (y con Java 21 en la mayoría de las configuraciones).
- Si quieres que actualice el proyecto a Java 21 y actualice la documentación y la configuración del wrapper, dímelo y lo hago.

---
Generado automáticamente por el asistente para facilitar ejecución y debugging.

## Windows — arranque automático (scripts añadidos)

He añadido dos helpers PowerShell en la raíz del proyecto `backend` para facilitar el arranque y la parada en Windows:

- `start-backend.ps1` — Construye (por defecto) y arranca el JAR en background, guarda logs en `./logs/`.
- `stop-backend.ps1` — Detiene procesos java que parecen corresponder al backend (busca el nombre del JAR en la línea de comandos de Java). 

Uso básico (PowerShell, desde la carpeta `backend`):

1) Construir y arrancar (por defecto el script ejecuta `gradlew.bat clean build`):
```powershell
.\start-backend.ps1
```

2) Arrancar sin compilar (usa el JAR ya existente en `build\libs`):
```powershell
.\start-backend.ps1 -NoBuild
```

3) Arrancar y abrir Swagger/H2 en el navegador:
```powershell
.\start-backend.ps1 -Open
```

4) Para detener el backend (detención por coincidencia con el nombre del JAR):
```powershell
.\stop-backend.ps1
```

5) Detener por PID conocido:
```powershell
.\stop-backend.ps1 -Pid 12345
```

Dónde están los logs:
- `backend\\logs\\backend.log` — salida estándar (stdout)
- `backend\\logs\\backend.err` — salida de error (stderr)

Notas y recomendaciones:
- Los scripts asumen que `gradlew.bat` está presente para la compilación. Si no lo está, ejecuta `.
  \\gradlew.bat clean build` manualmente antes de `start-backend.ps1`.
- El script `start-backend.ps1` arranca el proceso Java con redirección de stdout/stderr a los archivos de log y devuelve el PID en pantalla.
- Para desarrollo iterativo, también puedes usar `.
  \\gradlew.bat bootRun`.

