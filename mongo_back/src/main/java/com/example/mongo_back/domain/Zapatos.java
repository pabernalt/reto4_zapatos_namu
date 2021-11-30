package com.example.mongo_back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "zapatos")
public class Zapatos {
    //item
    @Id
    private Long id;

    private Long vendedor_id;
    //detail
    private int precio;
    private int talla;
    private String color;
    private String marca;
    private String linea;
    private String tipo;




}
