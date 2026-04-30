# Inventario - Log de Errores

Ejercicio práctico de Java que implementa un sistema de inventario de productos con manejo de excepciones personalizadas y registro de errores en archivos de log.

---

## Estructura del proyecto

```
inventario-log/
├── src/
│   ├── model/
│   │   └── Producto.java
│   ├── exception/
│   │   ├── ProductNotFoundException.java
│   │   └── EmptyInventoryException.java
│   ├── service/
│   │   └── Inventario.java
│   └── Main.java
├── logs/
│   ├── expected_errors_log.txt   (se genera al correr el programa)
│   └── inventario_log.txt        (se genera al correr el programa)
└── README.md
```

---

## Clases

### `model/Producto.java`
Representa un producto del inventario. Tiene tres atributos privados: `id` (int), `nombre` (String) y `cantidad` (int). Incluye constructor, getters y un `toString()` para mostrarlo en consola.

### `exception/ProductNotFoundException.java`
Excepción personalizada que se lanza cuando se intenta **buscar o eliminar** un producto con un `id` que no existe en el inventario. Extiende `Exception`.

### `exception/EmptyInventoryException.java`
Excepción personalizada que se lanza cuando se intenta **eliminar** un producto pero el inventario está completamente vacío. Extiende `Exception`.

### `service/Inventario.java`
Clase principal del sistema. Usa un `HashMap<Integer, Producto>` donde la clave es el `id` del producto. Implementa tres métodos:

- **`agregarProducto(Producto p)`** — agrega un producto al HashMap. Si ocurre un error inesperado, lo registra en `inventario_log.txt`.
- **`buscarProducto(int id)`** — busca un producto por su id. Si no existe, lanza `ProductNotFoundException` y lo registra en `expected_errors_log.txt`.
- **`eliminarProducto(int id)`** — elimina un producto por su id. Lanza `EmptyInventoryException` si el inventario está vacío, o `ProductNotFoundException` si el id no existe. Ambas se registran en `expected_errors_log.txt`. Cualquier otro error va a `inventario_log.txt`.

### `Main.java`
Clase de prueba que ejecuta el programa. Crea un inventario, agrega tres productos y prueba los siguientes escenarios:
1. Buscar un producto que existe → éxito.
2. Buscar un producto con id inexistente → `ProductNotFoundException`.
3. Eliminar un producto que existe → éxito.
4. Eliminar un producto con id inexistente → `ProductNotFoundException`.
5. Vaciar el inventario y luego intentar eliminar → `EmptyInventoryException`.

---

## Archivos de log

| Archivo | Contenido |
|---|---|
| `logs/expected_errors_log.txt` | Errores esperados: `ProductNotFoundException` y `EmptyInventoryException` |
| `logs/inventario_log.txt` | Errores inesperados para los programadores (cualquier otra excepción) |

Cada línea del log tiene el formato:
```
2026-04-29T22:00:00 - [EXPECTED] Producto con id 99 no encontrado en el inventario.
```

---

## Cómo compilar y ejecutar

Desde la raíz del proyecto, ejecutar los siguientes comandos:

```bash
# 1. Crear carpeta de salida
mkdir -p out

# 2. Compilar todos los archivos
find src -name "*.java" > sources.txt
javac -d out -sourcepath src @sources.txt

# 3. Ejecutar
java -cp out Main
```

> La carpeta `logs/` debe existir antes de correr el programa. Ya está incluida en el repositorio.
