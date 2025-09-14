package org.evercley.picpaydesafiobackend.repositories;

import org.evercley.picpaydesafiobackend.entities.UsuarioLojista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioLojistaRepository extends JpaRepository<UsuarioLojista, Long> {
    Optional<UsuarioLojista> findById(Long id);
}
