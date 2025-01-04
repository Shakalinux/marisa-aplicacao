package com.br.shakalinux.mariasaaplicacao.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DashboardDAO {

    public int getTotalFuncionarios() {
        try (Connection conn = BaseConexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM funcionarios")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalProdutos() {
        try (Connection conn = BaseConexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM produtos")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalVendas() {
        try (Connection conn = BaseConexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM vendas")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Object> getTopFuncionario() {
        Map<String, Object> topFuncionario = new HashMap<>();
        try (Connection conn = BaseConexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT f.idFuncionario, p.nome, COUNT(v.idVenda) AS totalVendas " +
                             "FROM vendas v " +
                             "JOIN funcionarios f ON v.idFuncionario = f.idFuncionario " +
                             "JOIN pessoa p ON f.idPessoa = p.idPessoa " +
                             "GROUP BY f.idFuncionario, p.nome " +
                             "ORDER BY totalVendas DESC " +
                             "LIMIT 1")) {
            if (rs.next()) {
                topFuncionario.put("idFuncionario", rs.getInt("idFuncionario"));
                topFuncionario.put("nome", rs.getString("nome"));
                topFuncionario.put("totalVendas", rs.getInt("totalVendas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topFuncionario;
    }
}
