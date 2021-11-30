package com.example.mongo_back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
public class Usuarios  implements Serializable {
    //user
    @Id
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String encPassword;
    private String direccion;



}