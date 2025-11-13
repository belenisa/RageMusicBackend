package com.example.ragemusica.service;

import com.example.ragemusica.model.Producto;
import com.example.ragemusica.repository.ProductoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@SuppressWarnings("null")
@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    public Producto partialUpdate(Producto producto){
        Producto existingProducto = repo.findById(producto.getId()).orElse(null);
        if (existingProducto != null) {
                if (producto.getNombre() != null) {
                    existingProducto.setNombre(producto.getNombre());
                }
                if (producto.getTipoProducto() != null) {
                    existingProducto.setTipoProducto(producto.getTipoProducto());
                }
                if (producto.getArtista() != null) {
                    existingProducto.setArtista(producto.getArtista());
                }
                if (producto.getDescripcion() != null) {
                    existingProducto.setDescripcion(producto.getDescripcion());
                }
                if (producto.getPrecio() != null) {
                    existingProducto.setPrecio(producto.getPrecio());
                }
                if (producto.getStock() != null) {
                    existingProducto.setStock(producto.getStock());
                }

            return repo.save(existingProducto);
        }
        return null;
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public List<Producto> findByArtistaId(Integer artistaId) {
        return repo.findByArtistaId(artistaId);
    }


}
