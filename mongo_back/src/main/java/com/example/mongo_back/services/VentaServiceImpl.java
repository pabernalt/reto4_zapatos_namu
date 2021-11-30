package com.example.mongo_back.services;

import com.example.mongo_back.domain.Counter;
import com.example.mongo_back.domain.Venta;
import com.example.mongo_back.domain.Zapatos;
import com.example.mongo_back.dto.VentaDto;
import com.example.mongo_back.dto.ZapatosDto;
import com.example.mongo_back.repository.UsuarioRepository;
import com.example.mongo_back.repository.VentaRepository;
import com.example.mongo_back.repository.ZapatosRepository;
import com.example.mongo_back.vo.EstadoVenta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService{
    private final VentaRepository ventaRepository;
    private final ZapatosRepository zapatosRepository;
    private final CounterService counterService;
    private final UsuarioRepository usuarioRepository;
    @Override
    public VentaDto createVenta(VentaDto ventaDto) {

        Zapatos zapatos = zapatosRepository.findById(ventaDto.getZapaots_id()).orElseThrow(() -> new IllegalStateException("There is no Zapatos matching with zapatos_id"));
        usuarioRepository.findById(ventaDto.getComprador_id()).orElseThrow(() -> new IllegalStateException("There is no Usuario with same Comprador_id"));
        if(zapatos.getVendedor_id() == ventaDto.getComprador_id()){
            throw new IllegalStateException("Comprador_id and Vendedor_id cannot be same");
        }else{
            Long next = counterService.getNext();
            Venta venta = new Venta();
            venta.setComprador_id(ventaDto.getComprador_id());
            venta.setZapaots_id(ventaDto.getZapaots_id());
            venta.setPrecio(zapatos.getPrecio()* ventaDto.getCantidad());
            venta.setCantidad(ventaDto.getCantidad());
            venta.setEstado(EstadoVenta.Solicitud);
            venta.setId(next);
            return new VentaDto().fromEntity(ventaRepository.save(venta));
        }

    }

    @Override
    public VentaDto findVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(RuntimeException::new);
        return new VentaDto().fromEntity(venta);
    }

    @Override
    public List<VentaDto> findAllVentas() {
        return ventaRepository.findAll().stream().map(venta -> new VentaDto().fromEntity(venta)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VentaDto cancelVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(RuntimeException::new);
        if(venta.getEstado()== EstadoVenta.Solicitud){
            venta.setEstado(EstadoVenta.Cancelada);

            return new VentaDto().fromEntity(ventaRepository.save(venta));
        }else{
            throw new IllegalStateException("Only Cancelable when it is in request status");
        }
    }

    @Override
    @Transactional
    public VentaDto acceptVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(RuntimeException::new);
        if(venta.getEstado()== EstadoVenta.Solicitud){
            venta.setEstado(EstadoVenta.Aceptada);
            return new VentaDto().fromEntity(ventaRepository.save(venta));
        }else{
            throw new IllegalStateException("Only Acceptable when it is in request status");
        }

    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepository.findById(id).orElseThrow(IllegalStateException::new);
        ventaRepository.deleteById(id);
    }
}
