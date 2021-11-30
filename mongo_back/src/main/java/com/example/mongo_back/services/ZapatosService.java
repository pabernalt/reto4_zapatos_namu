package com.example.mongo_back.services;

import com.example.mongo_back.domain.Zapatos;
import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.dto.ZapatosDto;

import java.util.List;

public interface ZapatosService {
    ZapatosDto createZapatos(ZapatosDto zapatosDto);
    ZapatosDto findZapatos(Long id);
    List<ZapatosDto> findAllZapatos();
}
