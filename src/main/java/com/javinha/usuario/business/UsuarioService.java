package com.javinha.usuario.business;

import com.javinha.usuario.business.converter.UsuarioConverter;
import com.javinha.usuario.business.dto.UsuarioDTO;
import com.javinha.usuario.infrastructure.entity.Usuario;
import com.javinha.usuario.infrastructure.exceptions.ConflictException;
import com.javinha.usuario.infrastructure.exceptions.ResourcesNotFoundException;
import com.javinha.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public Usuario buscaUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourcesNotFoundException("E-mail não cadastrado" + email));
    }
    @Transactional
    public void deletaUsuarioByEmail(String email) {
        // Valida se o usuário existe. Se não, ResourcesNotFoundException é lançada.
        // O resultado da busca (o objeto Usuario) é ignorado, mas a validação ocorre.
        usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourcesNotFoundException("E-mail não cadastrado" + email));
        // Executa a deleção.
        // Se o usuário não existe, a exceção acima já teria interrompido o método.
        usuarioRepository.deleteByEmail(email);

    }
}

