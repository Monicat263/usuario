package com.javinha.aprendendospring.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor /*Construtor com argumentos*/
@NoArgsConstructor /*Construtor sem argumentos*/
@Entity            /*usado para indicar que esta classe se trata de uma tablea */
@Table(name = "usuario") /*nome da tabela no bando de dados */
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome" , length = 100)
    private String nome;
    @Column(name ="email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;

    // relacionamento entre tabelas
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id" )
    private List<Telefone> telefones;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
