package com.curso.ecommerce.service;

import com.curso.ecommerce.model.Producto;

import com.curso.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto guardar(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> obtener(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void actualizar(Producto producto) {
		productoRepository.save(producto);		
	}

	@Override
	public void eliminar(Integer id) {
		productoRepository.deleteById(id);		
	}

	/*@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}*/

}
