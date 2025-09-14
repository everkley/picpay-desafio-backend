package org.evercley.picpaydesafiobackend.services;

import org.evercley.picpaydesafiobackend.entities.Usuario;
import org.evercley.picpaydesafiobackend.entities.UsuarioComum;
import org.evercley.picpaydesafiobackend.entities.UsuarioLojista;
import org.evercley.picpaydesafiobackend.exceptions.InsufficientFundException;
import org.evercley.picpaydesafiobackend.exceptions.UnauthorizedExcpetion;
import org.evercley.picpaydesafiobackend.exceptions.UsuarioNotFoundException;
import org.evercley.picpaydesafiobackend.repositories.UsuarioComumRepository;
import org.evercley.picpaydesafiobackend.repositories.UsuarioLojistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {
    private final UsuarioComumRepository usuarioComumRepository;
    private final UsuarioLojistaRepository usuarioLojistaRepository;
    private final AuthorizationService authorizationService;

    public TransferService(UsuarioComumRepository usuarioComumRepository,
                           UsuarioLojistaRepository usuarioLojistaRepository, AuthorizationService authorizationService) {
        this.usuarioComumRepository = usuarioComumRepository;
        this.usuarioLojistaRepository = usuarioLojistaRepository;
        this.authorizationService = authorizationService;
    }
    @Transactional
    public void transfer(Double value, Long payerId, Long payeeId) {
        Boolean authorized = authorizationService.authorizeTransaction(payerId, value);
        if (!authorized) {
            throw new UnauthorizedExcpetion("Unauthorized transaction");
        }
        UsuarioComum payer = usuarioComumRepository.findById(payerId)
                .orElseThrow(() -> new UsuarioNotFoundException("Payer not found"));

        Usuario payee = usuarioLojistaRepository.findById(payeeId)
                .map(u -> (Usuario) u)
                .orElseGet(() -> usuarioComumRepository.findById(payeeId)
                        .map(u -> (Usuario) u)
                        .orElseThrow(() -> new UsuarioNotFoundException("Payee not found")));


        if (payer.getSaldo() < value) {
            throw new InsufficientFundException("Insufficient funds");
        }

        payer.setSaldo(payer.getSaldo() - value);
        payee.setSaldo(payee.getSaldo() + value);

        usuarioComumRepository.save(payer);
        if (payee instanceof UsuarioComum) {
            usuarioComumRepository.save((UsuarioComum) payee);
        } else if (payee instanceof UsuarioLojista) {
            usuarioLojistaRepository.save((UsuarioLojista) payee);
        }
    }
}
