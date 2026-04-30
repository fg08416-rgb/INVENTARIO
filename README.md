# Inventario - Log de Errores

Ejercicio práctico de Java que implementa un sistema de inventario de productos con manejo de excepciones personalizadas y registro de errores en archivos de log.

## Clases

### model/Producto.java
Representa un producto del inventario. Tiene tres atributos privados: id (int), nombre (String) y cantidad (int). Incluye constructor, getters y un toString() para mostrarlo en consola.

### exception/ProductNotFoundException.java
Excepción personalizada que se lanza cuando se intenta **buscar o eliminar** un producto con un id que no existe en el inventario. Extiende Exception.

### exception/EmptyInventoryException.java
Excepción personalizada que se lanza cuando se intenta **eliminar** un producto pero el inventario está completamente vacío. Extiende Exception.

### service/LogManager.java
Clase utilitaria con un único método estático registrarError(String nombreArchivo, String mensajeError). Se encarga de abrir el archivo indicado en modo append y escribir una línea con la fecha y hora actuales seguida del mensaje de error. Al ser estático, cualquier clase del proyecto puede llamarlo sin necesidad de instanciarlo. Toda la lógica de escritura en disco está centralizada aquí.

### service/Inventario.java
Clase principal del sistema. Usa un HashMap<Integer, Producto> donde la clave es el id del producto. Implementa tres métodos, y delega todo el registro de errores a LogManager:

- **agregarProducto(Producto p)** — agrega un producto al HashMap. Si ocurre un error inesperado, llama a LogManager para registrarlo en inventario_log.txt.
- **buscarProducto(int id)** — busca un producto por su id. Si no existe, lanza ProductNotFoundException y la registra en expected_errors_log.txt via LogManager.
- **eliminarProducto(int id)** — elimina un producto por su id. Lanza EmptyInventoryException si el inventario está vacío, o ProductNotFoundException si el id no existe. Ambas se registran en expected_errors_log.txt. Cualquier otro error inesperado va a inventario_log.txt.

### Main.java
Clase de prueba que ejecuta el programa. Crea un inventario, agrega tres productos y prueba los siguientes escenarios:
1. Buscar un producto que existe -> éxito.
2. Buscar un producto con id inexistente -> ProductNotFoundException.
3. Eliminar un producto que existe -> éxito.
4. Eliminar un producto con id inexistente -> ProductNotFoundException.
5. Vaciar el inventario y luego intentar eliminar -> EmptyInventoryException.

## Archivos de log

| Archivo | Contenido |
|---|---|
| logs/expected_errors_log.txt | Errores esperados: ProductNotFoundException y EmptyInventoryException |
| logs/inventario_log.txt | Errores inesperados para los programadores (cualquier otra excepción) |

