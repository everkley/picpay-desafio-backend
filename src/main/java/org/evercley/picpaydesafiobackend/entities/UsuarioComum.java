package org.evercley.picpaydesafiobackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_comum")
public class UsuarioComum extends Usuario {
    @Column(unique = true)
    private String cpf;


    public UsuarioComum() {
        super();
    }

    public UsuarioComum(Long id, String nomeCompleto, String email, String senha, Double saldo, String cpf) {
        super(id, nomeCompleto, email, senha, saldo);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
