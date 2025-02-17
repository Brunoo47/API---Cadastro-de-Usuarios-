package com.project.appusuarios.controller;

import com.project.appusuarios.domain.Usuario;
import com.project.appusuarios.dto.UsuarioDTO;
import com.project.appusuarios.repositorys.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .map(usuario -> new UsuarioDTO(usuario.getNome(), usuario.getEmail()))
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(u -> ResponseEntity.ok(new UsuarioDTO(u.getNome(), u.getEmail()))).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> CriarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());

        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(new UsuarioDTO(novoUsuario.getNome(), novoUsuario.getEmail()));
    }
    @PutMapping
    public ResponseEntity<UsuarioDTO> AtualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

    }
}
