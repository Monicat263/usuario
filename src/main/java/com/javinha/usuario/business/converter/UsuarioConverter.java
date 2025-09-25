package com.javinha.usuario.business.converter;

import com.javinha.usuario.business.dto.EnderecoDTO;
import com.javinha.usuario.business.dto.TelefoneDTO;
import com.javinha.usuario.business.dto.UsuarioDTO;
import com.javinha.usuario.infrastructure.entity.Endereco;
import com.javinha.usuario.infrastructure.entity.Telefone;
import com.javinha.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    // #############################################################
    // ### Seção 1: CONVERSÃO DE DTO PARA OBJETO DE DOMÍNIO (Entity) ###
    // #############################################################

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();

    }

    /**
     * Converte uma lista de EnderecoDTO para uma lista de Endereco.
     * Utiliza a Stream API para aplicar a conversão a cada item da lista.
     *
     * @param enderecosDTOS A lista de EnderecoDTO a ser convertida.
     * @return Uma nova lista contendo os objetos Endereco convertidos.
     */

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecosDTOS) {
        return enderecosDTOS.stream().map(this::paraEndereco).toList();
    }

    /**
     * Converte um único objeto EnderecoDTO para um objeto Endereco.
     * Usa o padrão Builder para criar o objeto de forma clara e segura.
     *
     * @param enderecoDTO O EnderecoDTO a ser convertido.
     * @return O Endereco resultante da conversão.
     */
    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();

    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefonesDTOS) {
        return telefonesDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()

                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }


    // #############################################################
    // ### Seção 2: CONVERSÃO DE OBJETO DE DOMÍNIO (Entity) PARA DTO ###
    // #############################################################


    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();

    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecosDTOS) {
        return enderecosDTOS.stream().map(this::paraEnderecoDTO).toList();
    }


    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();

    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefonesDTOS) {
        return telefonesDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

}
