package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    /*imprime en consola los objetos*/
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model) {
        List<Producto>listaProducto = productoService.findAll();
        model.addAttribute("productos", listaProducto);
        return "productos/show";
    }

    /*crear producto:*/
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    /*crear producto*/
    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("Este es el objeto producto {}",producto);
        Usuario u = new Usuario(1,"","","","","","","");
        producto.setUsuario(u);
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    /*editar obtiene el producto*/
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        /*obtiene el producto por el id*/
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.obtener(id);
        producto = optionalProducto.get();

        /*envia el producto encontrado al template edit*/
        LOGGER.info("Producto buscado: {}",producto);

        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    /*editar*/
    @PostMapping("/update")
    public String update(Producto producto) {
        productoService.actualizar(producto);
        return "redirect:/productos";
    }

    /*eliminar*/
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
