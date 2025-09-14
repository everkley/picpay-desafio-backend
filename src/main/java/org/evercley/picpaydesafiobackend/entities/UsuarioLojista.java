package org.evercley.picpaydesafiobackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_lojista")
public class UsuarioLojista extends Usuario {
    @Column(unique = true)
    private String cnpj;

    public UsuarioLojista() {
        super();
    }

    public UsuarioLojista(Long id, String nomeCompleto, String email, String senha, Double saldo, String cnpj) {
        super(id, nomeCompleto, email, senha, saldo);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }



}
