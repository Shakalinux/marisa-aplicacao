package com.br.shakalinux.mariasaaplicacao.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseConexao {
    public static Connection getConexao() {
        Connection conexao = null;
        try{
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/marisa",
                    "shakalinux", "221025");
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }
}
