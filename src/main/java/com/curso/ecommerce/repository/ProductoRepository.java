package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
