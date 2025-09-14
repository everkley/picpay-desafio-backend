package org.evercley.picpaydesafiobackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.evercley.picpaydesafiobackend.dtos.TransferDTO;
import org.evercley.picpaydesafiobackend.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @Operation(summary = "Realiza uma transferência entre usuários")
    public ResponseEntity<String> trasferir(@RequestBody TransferDTO dto) {
        transferService.transfer(dto.getValue(),dto.getPayer(),dto.getPayee());
        return ResponseEntity.ok("Transferência realizada com sucesso");
    }
}
