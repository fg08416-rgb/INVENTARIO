package service;

import exception.EmptyInventoryException;
import exception.ProductNotFoundException;
import model.Producto;

import java.util.HashMap;

public class Inventario {

    private HashMap<Integer, Producto> productos = new HashMap<>();

    public void agregarProducto(Producto p) {
        try {
            if (p == null) throw new IllegalArgumentException("El producto no puede ser null.");
            productos.put(p.getId(), p);
            System.out.println("Producto agregado: " + p);
        } catch (Exception e) {
            LogManager.registrarError("logs/inventario_log", "[UNEXPECTED] Error al agregar producto: " + e.getMessage());
            System.err.println("Error inesperado al agregar producto: " + e.getMessage());
        }
    }

    public Producto buscarProducto(int id) throws ProductNotFoundException {
        try {
            if (!productos.containsKey(id)) {
                throw new ProductNotFoundException(id);
            }
            return productos.get(id);
        } catch (ProductNotFoundException e) {
            LogManager.registrarError("logs/expected_errors_log", "[EXPECTED] " + e.getMessage());
            throw e;
        } catch (Exception e) {
            LogManager.registrarError("logs/inventario_log", "[UNEXPECTED] Error al buscar id " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void eliminarProducto(int id) throws ProductNotFoundException, EmptyInventoryException {
        try {
            if (productos.isEmpty()) {
                throw new EmptyInventoryException();
            }
            if (!productos.containsKey(id)) {
                throw new ProductNotFoundException(id);
            }
            Producto eliminado = productos.remove(id);
            System.out.println("Producto eliminado: " + eliminado);
        } catch (EmptyInventoryException | ProductNotFoundException e) {
            LogManager.registrarError("logs/expected_errors_log", "[EXPECTED] " + e.getMessage());
            throw e;
        } catch (Exception e) {
            LogManager.registrarError("logs/inventario_log", "[UNEXPECTED] Error al eliminar id " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
