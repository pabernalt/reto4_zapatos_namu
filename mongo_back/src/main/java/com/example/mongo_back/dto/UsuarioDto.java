package com.example.mongo_back.dto;

import com.example.mongo_back.domain.Usuarios;
import com.example.mongo_back.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String password;
    private String direccion;

    public UsuarioDto fromEntity(Usuarios usuarios){
        this.id = usuarios.getId();
        this.nombres = usuarios.getNombres();
        this.apellidos = usuarios.getApellidos();
        this.direccion = usuarios.getDireccion();
        this.email = usuarios.getEmail();
        this.username = usuarios.getUsername();
        this.password = usuarios.getEncPassword();
        return this;
    }
}
