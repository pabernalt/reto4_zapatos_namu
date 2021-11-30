package com.example.mongo_back.dto;

import com.example.mongo_back.domain.Venta;
import com.example.mongo_back.domain.Zapatos;
import com.example.mongo_back.vo.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private Long id;
    private Long comprador_id;
    private Long zapaots_id;
    private int precio;
    private int cantidad;
    private EstadoVenta estado;

    public VentaDto fromEntity(Venta venta){
        this.id = venta.getId();
        this.comprador_id = venta.getComprador_id();
        this.zapaots_id = venta.getZapaots_id();
        this.precio = venta.getPrecio();
        this.cantidad = venta.getCantidad();
        this.estado = venta.getEstado();
        return this;
    }


}
