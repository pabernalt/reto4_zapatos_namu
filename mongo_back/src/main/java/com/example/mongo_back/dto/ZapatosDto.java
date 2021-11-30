package com.example.mongo_back.dto;

import com.example.mongo_back.domain.Usuarios;
import com.example.mongo_back.domain.Zapatos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZapatosDto {
    private Long id;

    private Long vendedor_id;
    //detail
    private int precio;
    private int talla;
    private String color;
    private String marca;
    private String linea;
    private String tipo;

    public ZapatosDto fromEntity(Zapatos zapatos){
        this.id = zapatos.getId();
        this.vendedor_id = zapatos.getVendedor_id();
        this.precio = zapatos.getPrecio();
        this.talla = zapatos.getTalla();
        this.color = zapatos.getColor();
        this.marca = zapatos.getMarca();
        this.linea = zapatos.getLinea();
        this.tipo = zapatos.getTipo();

        return this;
    }
}
