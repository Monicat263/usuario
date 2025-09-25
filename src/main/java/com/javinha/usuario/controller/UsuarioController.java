package com.javinha.usuario.controller;

import com.javinha.usuario.business.UsuarioService;
import com.javinha.usuario.business.dto.UsuarioDTO;
import com.javinha.usuario.infrastructure.entity.Usuario;
import com.javinha.usuario.infrastructure.repository.UsuarioRepository;
import com.javinha.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    //Injetar a classe UsuarioService
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    // Criação  do método  de login

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }


    @GetMapping
    public ResponseEntity<Usuario> buscaUsuarioByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscaUsuarioByEmail(email));
    }

//    @GetMapping("/perfil")
//    public ResponseEntity<Usuario> getPerfil(Authentication authentication) {
//        String emailDoUsuarioLogado = authentication.getName();
//        return ResponseEntity.ok(usuarioService.buscaUsuarioByEmail(emailDoUsuarioLogado));
//    }

}
