package com.br.shakalinux.mariasaaplicacao.model;

public class Cliente extends Pessoa {
    private Long idCliente;
    private String email;

    public Cliente() {}


    public Cliente(Long id, String nome, String cpf, String telefone, Long idCliente, String email) {
        super(id, nome, cpf, telefone);
        this.idCliente = idCliente;
        this.email = email;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
