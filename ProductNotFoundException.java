package exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(int id) {
        super("Producto con id " + id + " no encontrado en el inventario.");
    }
}
