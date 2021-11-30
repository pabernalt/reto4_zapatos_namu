package com.example.mongo_back.controller;

import com.example.mongo_back.dto.VentaDto;
import com.example.mongo_back.dto.ZapatosDto;
import com.example.mongo_back.services.VentaService;
import com.example.mongo_back.services.ZapatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/say")
    public String sayHello() {
        return "Hello Spring boot";
    }

    @PostMapping
    public VentaDto createZapatos(@RequestBody VentaDto ventaDto){
        return ventaService.createVenta(ventaDto);
    }

    @GetMapping
    public List<VentaDto> findAllVentas() {
        return ventaService.findAllVentas();
    }
    @GetMapping("/{id}")
    public VentaDto findVenta(@PathVariable Long id){
        return ventaService.findVenta(id);
    }

    @PatchMapping("/{id}/aceptado")
    public VentaDto aceptadoVenta(@PathVariable Long id){return ventaService.acceptVenta(id);}
    @PatchMapping("/{id}/cancelado")
    public VentaDto canceladoVenta(@PathVariable Long id){return ventaService.cancelVenta(id);}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id){
        ventaService.deleteVenta(id);
        return ResponseEntity.ok("venta deleted");
    }

}
