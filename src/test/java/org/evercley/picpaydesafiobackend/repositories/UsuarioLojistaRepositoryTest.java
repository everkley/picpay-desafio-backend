package org.evercley.picpaydesafiobackend.repositories;

import jakarta.persistence.EntityManager;
import org.evercley.picpaydesafiobackend.entities.UsuarioLojista;
import org.evercley.picpaydesafiobackend.exceptions.UsuarioNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioLojistaRepositoryTest {

    @Autowired
    UsuarioLojistaRepository usuarioLojistaRepository;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("Deve retornar um UsuarioLojista quando buscar por ID existente")
    void findByIdSuccess() {

        UsuarioLojista usuarioLojista = new UsuarioLojista(null, "Loja Exemplo", "email@email.com",
                "senha123", 1000.0, "12.345.678/0001-90");

        this.salvaUsuarioLojista(usuarioLojista);
        em.flush();
        Long id = usuarioLojista.getId();

        var foundedUser = usuarioLojistaRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        assertThat(foundedUser).isNotNull();
    }

    @Test
    @DisplayName("Não deve retornar um UsuarioLojista quando buscar por ID inexistente")
    void findByIdFail() {
        Long id = 999L;

        var foundedUser = usuarioLojistaRepository.findById(id);

        assertThat(foundedUser).isEmpty();
    }

    private void salvaUsuarioLojista(UsuarioLojista usuarioLojista) {
        this.usuarioLojistaRepository.save(usuarioLojista);
    }
}