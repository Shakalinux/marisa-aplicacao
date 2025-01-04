package com.br.shakalinux.mariasaaplicacao.model;

public class Area {
    private Long idArea;
    private String nome;

    public Area() {}

    public Area(Long idArea, String nome) {
        this.idArea = idArea;
        this.nome = nome;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Area{" +
                "idArea=" + idArea +
                ", nome='" + nome + '\'' +
                '}';
    }

}
