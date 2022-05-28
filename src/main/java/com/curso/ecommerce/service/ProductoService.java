package com.curso.ecommerce.service;

import com.curso.ecommerce.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public Producto guardar(Producto producto);

    public Optional<Producto> obtener(Integer id);

    public void actualizar(Producto producto);

    public void eliminar(Integer id);

    public List<Producto> findAll();
}
