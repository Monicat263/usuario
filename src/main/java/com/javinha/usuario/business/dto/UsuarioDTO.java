package com.javinha.usuario.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor                                          // - Gera um construtor com um argumento para cada campo na classe. A ordem dos argumentos é a mesma ordem em que os campos são declarados.
@NoArgsConstructor                                           // -  Gera um construtor sem argumentos (ou "construtor vazio").
@Builder
public class UsuarioDTO {

    // Crie  os mesmos atributos da entity, COM EXCEÇÃO do Id, das anoteçãoes e restrições de tamanho,e relacionamentos

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;
}
