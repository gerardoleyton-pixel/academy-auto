Academy Backend — instrucciones para Windows (arranque automático)

Este archivo explica cómo arrancar el backend en Windows usando los helpers PowerShell que añadí.

Archivos añadidos en la raíz `backend`:
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
- `backend\logs\backend.log` — salida estándar (stdout)
- `backend\logs\backend.err` — salida de error (stderr)

Notas y recomendaciones:
- Los scripts asumen que `gradlew.bat` está presente para la compilación. Si no lo está, ejecuta `.\gradlew.bat clean build` manualmente antes de `start-backend.ps1`.
- El script `start-backend.ps1` arranca el proceso Java con redirección de stdout/stderr a los archivos de log y devuelve el PID en pantalla.
- Para desarrollo iterativo, puedes usar también `.\gradlew.bat bootRun`.

Accesos rápidos cuando la app corre:
- Swagger: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:academydb`, user: `SA`, password: vacío)

Si quieres que incorpore los contenidos de `README_WINDOWS.md` dentro del `README.md` principal y deje una sola versión actualizada, dime y lo actualizo (tengo cuidado con el bloque que mostraba caracteres extraños en el README original).