package exception;

public class EmptyInventoryException extends Exception {
    public EmptyInventoryException() {
        super("El inventario esta vacio. No se puede eliminar ningun producto.");
    }
}
