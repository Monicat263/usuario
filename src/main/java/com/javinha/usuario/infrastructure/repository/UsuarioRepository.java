package com.javinha.aprendendospring.infrastructure.repository;

import com.javinha.aprendendospring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/* Dentro de <> Incluimos o nome da entidade(tabela) e o tipo do ID que que nesse caso é o Long */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
