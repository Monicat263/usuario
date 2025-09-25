package com.javinha.usuario.business;

import com.javinha.usuario.business.converter.UsuarioConverter;
import com.javinha.usuario.business.dto.UsuarioDTO;
import com.javinha.usuario.infrastructure.entity.Usuario;
import com.javinha.usuario.infrastructure.exceptions.ConflictException;
import com.javinha.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    // Injeção de dependência  UsuarioRepository e UsuarioConverter
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);

        // ou faça a forma simplificada

        // public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        //    Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        //    return usuarioConverter.paraUsuarioDTO(
        //           usuarioRepository.save(usuario)
        //    );
        //}

    } public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);

            if(existe){
                throw new ConflictException("E-mail já cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado", e.getCause());
        }

    }

    private boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
