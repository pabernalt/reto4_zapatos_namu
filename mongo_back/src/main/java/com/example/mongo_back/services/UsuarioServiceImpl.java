package com.example.mongo_back.services;

import com.example.mongo_back.domain.Usuarios;
import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final CounterService counterService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        String encPassword = passwordEncoder.encode(usuarioDto.getPassword());
        usuarioRepository.findUsuariosByUsername(usuarioDto.getUsername()).ifPresent(usuarios -> {throw new IllegalStateException("user is already exist");});

        Long next = counterService.getNext();
        Usuarios usuarios = new Usuarios();
        usuarios.setApellidos(usuarioDto.getApellidos());
        usuarios.setNombres(usuarioDto.getNombres());
        usuarios.setEmail(usuarioDto.getEmail());
        usuarios.setDireccion(usuarioDto.getDireccion());
        usuarios.setUsername(usuarioDto.getUsername());
        usuarios.setEncPassword(encPassword);
        usuarios.setId(next);
        return new UsuarioDto().fromEntity(usuarioRepository.save(usuarios));
    }

    @Override
    public UsuarioDto findUsuario(Long id) {
        Usuarios usuarios = usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
        return new UsuarioDto().fromEntity(usuarios);
    }

    @Override
    public List<UsuarioDto> findAllUsuarios() {
        return usuarioRepository.findAll().stream().map(usuarios -> new UsuarioDto().fromEntity(usuarios)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = usuarioRepository.findUsuariosByUsername(username).orElseThrow(RuntimeException::new);
        return new User(username, usuarios.getEncPassword(), true, true, true, true, new ArrayList<>());
    }
}
