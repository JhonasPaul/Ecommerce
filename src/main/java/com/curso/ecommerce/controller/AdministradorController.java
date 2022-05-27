package com.curso.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

@Controller
@RequestMapping("/administrador")
public class AdministradorController implements Serializable {

    @GetMapping("")
    public String home() {
        return "administrador/home";
    }

    public static final long serialVersionUID = 1L;
}
