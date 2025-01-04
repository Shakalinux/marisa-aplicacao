package com.br.shakalinux.mariasaaplicacao.model;

public class Funcionario extends Pessoa {
    private Long idFuncionario;
    private String matricula;
    private Area area;
    private double salario;

    public Funcionario() {}

    public Funcionario(Long idPessoa, String nome, String cpf, String telefone, Long idFuncionario, String matricula, Area area, double salario) {
        super(idPessoa, nome, cpf, telefone);
        this.idFuncionario = idFuncionario;
        this.matricula = matricula;
        this.area = area;
        this.salario = salario;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
