package org.evercley.picpaydesafiobackend.services;

import org.evercley.picpaydesafiobackend.entities.UsuarioComum;
import org.evercley.picpaydesafiobackend.entities.UsuarioLojista;
import org.evercley.picpaydesafiobackend.repositories.UsuarioComumRepository;
import org.evercley.picpaydesafiobackend.repositories.UsuarioLojistaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private UsuarioComumRepository usuarioComumRepository;
    @Mock
    private UsuarioLojistaRepository usuarioLojistaRepository;
    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private TransferService transferService;


    @Test
    @DisplayName("Deve criar uma transferência com sucesso de usuário comum para usuário comum")
    void transferSuccess() {
        UsuarioComum payer = new UsuarioComum();
        payer.setId(1L);
        payer.setSaldo(1000.0);

        UsuarioComum payee = new UsuarioComum();
        payee.setId(2L);
        payee.setSaldo(500.0);

        when(authorizationService.authorizeTransaction(1L, 300.0)).thenReturn(true);
        when(usuarioComumRepository.findById(1L)).thenReturn(Optional.of(payer));
        when(usuarioComumRepository.findById(2L)).thenReturn(Optional.of(payee));
        when(usuarioLojistaRepository.findById(2L)).thenReturn(Optional.empty());

        transferService.transfer(300.0, 1L, 2L);
        assertEquals(700.0, payer.getSaldo());
        assertEquals(800.0, payee.getSaldo());
        verify(usuarioComumRepository).save(payer);
        verify(usuarioComumRepository).save(payee);
    }
    @Test
    @DisplayName("Deve criar transferência de usuário comum para usuário lojista com sucesso")
    void transferSuccessComumParaLojista() {
        UsuarioComum payer = new UsuarioComum();
        payer.setId(1L);
        payer.setSaldo(100.0);

        UsuarioLojista payee = new UsuarioLojista();
        payee.setId(3L);
        payee.setSaldo(200.0);

        when(authorizationService.authorizeTransaction(1L, 50.0)).thenReturn(true);
        when(usuarioComumRepository.findById(1L)).thenReturn(Optional.of(payer));
        when(usuarioLojistaRepository.findById(3L)).thenReturn(Optional.of(payee));

        transferService.transfer(50.0, 1L, 3L);

        assertEquals(50.0, payer.getSaldo());
        assertEquals(250.0, payee.getSaldo());
        verify(usuarioComumRepository).save(payer);
        verify(usuarioLojistaRepository).save(payee);
    }

}