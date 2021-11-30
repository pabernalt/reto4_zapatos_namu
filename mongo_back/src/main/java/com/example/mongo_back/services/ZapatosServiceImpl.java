package com.example.mongo_back.services;

import com.example.mongo_back.domain.Usuarios;
import com.example.mongo_back.domain.Zapatos;
import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.dto.ZapatosDto;
import com.example.mongo_back.repository.UsuarioRepository;
import com.example.mongo_back.repository.ZapatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZapatosServiceImpl implements ZapatosService{
    private final ZapatosRepository zapatosRepository;
    private final UsuarioRepository usuarioRepository;
    private final CounterService counterService;
    @Override
    public ZapatosDto createZapatos(ZapatosDto zapatosDto) {
        Long next = counterService.getNext();
        Zapatos zapatos = new Zapatos();
        usuarioRepository.findById(zapatosDto.getVendedor_id()).orElseThrow(()-> new IllegalStateException("there is no vendedor with same id"));
        zapatos.setVendedor_id(zapatosDto.getVendedor_id());
        zapatos.setColor(zapatosDto.getColor());
        zapatos.setTipo(zapatosDto.getTipo());
        zapatos.setLinea(zapatosDto.getLinea());
        zapatos.setMarca(zapatosDto.getMarca());
        zapatos.setPrecio(zapatosDto.getPrecio());
        zapatos.setTalla(zapatosDto.getTalla());
        zapatos.setId(next);
        return new ZapatosDto().fromEntity(zapatosRepository.save(zapatos));
    }

    @Override
    public ZapatosDto findZapatos(Long id) {
        Zapatos zapatos = zapatosRepository.findById(id).orElseThrow(RuntimeException::new);
        return new ZapatosDto().fromEntity(zapatos);
    }

    @Override
    public List<ZapatosDto> findAllZapatos() {
        return zapatosRepository.findAll().stream().map(zapatos -> new ZapatosDto().fromEntity(zapatos)).collect(Collectors.toList());
    }


}
