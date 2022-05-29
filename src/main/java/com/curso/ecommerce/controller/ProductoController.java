package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    /*imprime en consola los objetos*/
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService uploadFileService;

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
    @PostMapping("/save")                     /*name="img" del create.html*/
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Este es el objeto producto {}",producto);
        Usuario u = new Usuario(1,"","","","","","","");
        producto.setUsuario(u);

        /*imagen*/
        if (producto.getId() == null) {/*cuando se crea un producto*/
            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }else{
            if (file.isEmpty()) {/*editar prodcuto sin cambiar la imagen*/
                Producto p = new Producto();
                p = productoService.obtener(producto.getId()).get();
                producto.setImagen(p.getImagen());
            } else {
                String nombreImagen = uploadFileService.saveImage(file);
                producto.setImagen(nombreImagen);
            }
        }

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
