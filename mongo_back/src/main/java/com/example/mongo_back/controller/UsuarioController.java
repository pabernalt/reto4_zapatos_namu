package com.example.mongo_back.controller;

import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/say")
    public String sayHello() {
        return "Hello Spring boot";
    }

    @PostMapping("/register")
    public UsuarioDto createUsuario(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.createUsuario(usuarioDto);
    }

    @GetMapping("/usuarios")
    public List<UsuarioDto> findAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }
    @GetMapping("/usuarios/{id}")
    public UsuarioDto findUsuario(@PathVariable Long id){
        return usuarioService.findUsuario(id);
    }
}
