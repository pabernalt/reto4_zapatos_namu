package com.example.mongo_back.services;

import com.example.mongo_back.dto.UsuarioDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UsuarioService extends UserDetailsService {
    UsuarioDto createUsuario(UsuarioDto usuarioDto);
    UsuarioDto findUsuario(Long id);
    List<UsuarioDto> findAllUsuarios();

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
