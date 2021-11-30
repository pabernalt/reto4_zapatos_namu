package com.example.mongo_back.services;

import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.dto.VentaDto;

import java.util.List;

public interface VentaService {
    VentaDto createVenta(VentaDto ventaDto);
    VentaDto findVenta(Long id);
    List<VentaDto> findAllVentas();
    VentaDto cancelVenta(Long id);
    VentaDto acceptVenta(Long id);

    void deleteVenta(Long id);
}
