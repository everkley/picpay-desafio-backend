package org.evercley.picpaydesafiobackend.repositories;

import org.evercley.picpaydesafiobackend.entities.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioComumRepository extends JpaRepository<UsuarioComum, Long> {
    Optional<UsuarioComum> findById(Long id);
}
