# Granja de Caballos

Proyecto demo con Spring Boot 4, JPA, MySQL y migraciones con Flyway.

## Paso a Paso Resumido

### 1) Levantar base de datos

```bash
docker compose up -d --build db
```

### 2) Usar Java 25

Este proyecto compila con release 25.

```bash
usejava25
java -version
```

### 3) Configurar Flyway

Dependencias requeridas en `pom.xml`:

- `spring-boot-starter-flyway`
- `flyway-core`
- `flyway-mysql`

Propiedades clave en `src/main/resources/application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

### 4) Crear migraciones versionadas

Ubicacion: `src/main/resources/db/migration`

Migraciones actuales:

- `V1__init_schema.sql`
- `V2__add_color_to_caballos.sql`

Regla de nombre: `V<version>__<descripcion>.sql`

### 5) Sincronizar modelo y DTO

Para el campo `color` se actualizo:

- `Caballo` (entidad)
- `CaballoDto` (request/response)
- `CaballoServiceImpl` (mapeo entity <-> dto)

### 6) Ejecutar aplicacion

```bash
./mvnw clean spring-boot:run
```

> Nota: usar `clean` evita errores por archivos viejos en `target/classes`.

### 7) Validar API

Crear tipo:

```bash
curl -sS -X POST http://localhost:8080/api/tipos \
  -H 'Content-Type: application/json' \
  -d '{"nombre":"Demo Tipo","descripcion":"Tipo para prueba"}'
```

Crear caballo (incluyendo `color`):

```bash
curl -sS -X POST http://localhost:8080/api/caballos \
  -H 'Content-Type: application/json' \
  -d '{"nombre":"Relampago","color":"marron","edad":5,"tipo":{"id":1,"nombre":"Demo Tipo","descripcion":"Tipo para prueba"}}'
```

Listar caballos:

```bash
curl -sS http://localhost:8080/api/caballos
```

## Script Para Mostrar La DB Completa (Demo)

Se incluye script para imprimir todas las tablas y sus datos en formato tabla ASCII.

Linux/macOS:

```bash
./print_db_snapshot.sh
```

Windows (PowerShell):

```powershell
.\print_db_snapshot.ps1
```

Windows (cmd):

```bat
print_db_snapshot.cmd
```

Variables opcionales:

- `DB_CONTAINER`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`

## Lo Aprendido (Errores Comunes)

1. Si Flyway no aparece en logs, revisa que el starter de Flyway este en dependencias.
2. Si sale "Found more than one migration with version X", hay conflicto de versiones.
3. Si el código fuente esta bien pero sigue fallando con migraciones viejas, ejecutar `clean`.
4. `ddl-auto=validate` exige que el esquema exista y coincida con entidades.
5. En este proyecto, el DTO de tipo requiere `id`, `nombre` y `descripcion` para pasar validaciones.

## Comprobacion SQL Rapida

```sql
SELECT version, description, success
FROM flyway_schema_history
ORDER BY installed_rank;
```