package com.example.mongo_back.controller;

import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.dto.ZapatosDto;
import com.example.mongo_back.services.UsuarioService;
import com.example.mongo_back.services.ZapatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zapatos")
public class ZapatosController {
    @Autowired
    private ZapatosService zapatosService;

    @GetMapping("/say")
    public String sayHello() {
        return "Hello Spring boot";
    }

    @PostMapping
    public ZapatosDto createZapatos(@RequestBody ZapatosDto zapatosDto){
        return zapatosService.createZapatos(zapatosDto);
    }

    @GetMapping
    public List<ZapatosDto> findAllZapatos() {
        return zapatosService.findAllZapatos();
    }
    @GetMapping("/{id}")
    public ZapatosDto findZapatos(@PathVariable Long id){
        return zapatosService.findZapatos(id);
    }
}
