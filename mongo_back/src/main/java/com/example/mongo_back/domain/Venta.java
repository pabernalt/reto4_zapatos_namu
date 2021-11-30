package com.example.mongo_back.domain;

import com.example.mongo_back.vo.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "venta")
public class Venta {
    //order
    @Id
    private Long id;

    private Long comprador_id;
    private Long zapaots_id;

    private int precio;
    private int cantidad;


    private EstadoVenta estado;


}
