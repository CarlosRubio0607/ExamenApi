package com.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	
	private List<Producto> productos = new ArrayList<>();
	
	public ProductoController() {
		// Mock data
        productos.add(new Producto(1L, "Producto 1", 10.0));
        productos.add(new Producto(2L, "Producto 2", 20.0));
        productos.add(new Producto(3L, "Producto 3", 30.0));
	}
	
	// Metodo para obtener todos los productos 
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productos;
    }

    // Metodo para obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productos.stream().filter(p -> p.getId().equals(id)).findFirst();
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Metodo para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        nuevoProducto.setId((long) (productos.size() + 1)); // Asignar un ID único
        productos.add(nuevoProducto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // Metodo para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Optional<Producto> productoExistente = productos.stream().filter(p -> p.getId().equals(id)).findFirst();

        if (productoExistente.isPresent()) {
            productoExistente.get().setNombre(productoActualizado.getNombre());
            productoExistente.get().setPrecio(productoActualizado.getPrecio());
            return ResponseEntity.ok(productoExistente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productos.removeIf(p -> p.getId().equals(id));
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
