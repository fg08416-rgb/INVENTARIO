import exception.EmptyInventoryException;
import exception.ProductNotFoundException;
import model.Producto;
import service.Inventario;

public class Main {
    public static void main(String[] args) {

        Inventario inv = new Inventario();

        inv.agregarProducto(new Producto(1, "Manzana", 50));
        inv.agregarProducto(new Producto(2, "Naranja", 30));
        inv.agregarProducto(new Producto(3, "Pera", 20));

        try {
            Producto p = inv.buscarProducto(1);
            System.out.println("Encontrado: " + p);
        } catch (ProductNotFoundException e) {
            System.err.println(e.getMessage());
        }

        try {
            inv.buscarProducto(99);
        } catch (ProductNotFoundException e) {
            System.err.println("Error esperado: " + e.getMessage());
        }

        try {
            inv.eliminarProducto(2);
        } catch (ProductNotFoundException | EmptyInventoryException e) {
            System.err.println(e.getMessage());
        }

        try {
            inv.eliminarProducto(99);
        } catch (ProductNotFoundException | EmptyInventoryException e) {
            System.err.println("Error esperado: " + e.getMessage());
        }

        try { inv.eliminarProducto(1); } catch (Exception ignored) {}
        try { inv.eliminarProducto(3); } catch (Exception ignored) {}

        try {
            inv.eliminarProducto(1);
        } catch (ProductNotFoundException | EmptyInventoryException e) {
            System.err.println("Error esperado: " + e.getMessage());
        }
    }
}
