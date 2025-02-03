package com.project.appusuarios.controller;

import com.project.appusuarios.domain.Usuario;
import com.project.appusuarios.dto.UsuarioDTO;
import com.project.appusuarios.repositorys.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping
    public List<UsuarioDTO> listarTodos() {
      return usuarioRepository.findAll().stream()
              .map(usuario -> new UsuarioDTO())
    };

}
