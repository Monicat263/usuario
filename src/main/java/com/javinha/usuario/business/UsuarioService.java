package com.javinha.usuario.business;

import com.javinha.usuario.business.converter.UsuarioConverter;
import com.javinha.usuario.business.dto.UsuarioDTO;
import com.javinha.usuario.infrastructure.entity.Usuario;
import com.javinha.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    // Injeção de dependência  UsuarioRepository e UsuarioConverter
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
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

    }


}
