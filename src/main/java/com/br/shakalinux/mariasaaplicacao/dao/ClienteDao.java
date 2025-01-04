package com.br.shakalinux.mariasaaplicacao.dao;

import com.br.shakalinux.mariasaaplicacao.model.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDao {

    public void cadastrarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement pstPessoa = null;
        PreparedStatement pstCliente = null;
        try {
            conn = BaseConexao.getConexao();
            conn.setAutoCommit(false);

            pstPessoa = conn.prepareStatement("INSERT INTO pessoa (nome, cpf, telefone) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstPessoa.setString(1, cliente.getNome());
            pstPessoa.setString(2, cliente.getCpf());
            pstPessoa.setString(3, cliente.getTelefone());
            pstPessoa.executeUpdate();

            ResultSet rs = pstPessoa.getGeneratedKeys();
            Long idPessoa = null;
            if (rs.next()) {
                idPessoa = rs.getLong(1);
            }

            pstCliente = conn.prepareStatement("INSERT INTO clientes (idPessoa, email) VALUES (?, ?)");
            pstCliente.setLong(1, idPessoa);
            pstCliente.setString(2, cliente.getEmail());
            pstCliente.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstCliente != null) {
                    pstCliente.close();
                }
                if (pstPessoa != null) {
                    pstPessoa.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cliente> listarClientes() {
        Connection conn = null;
        PreparedStatement pstPessoa = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = BaseConexao.getConexao();
            pstPessoa = conn.prepareStatement("SELECT " +
                    "    c.idCliente, " +
                    "    c.email, " +
                    "    p.nome, " +
                    "    p.cpf, " +
                    "    p.telefone " +
                    "FROM " +
                    "    clientes c " +
                    "JOIN " +
                    "    pessoa p ON c.idPessoa = p.idPessoa;");

            rs = pstPessoa.executeQuery();

            while (rs.next()) {
                Long idCliente = rs.getLong("idCliente");
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                Long idPessoa = null;
                Cliente cliente = new Cliente(idPessoa, nome, cpf, telefone, idCliente, email);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) rs.close();
                if (pstPessoa != null) pstPessoa.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    public Cliente buscarClientePorId(Long id) {
        Cliente cliente = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = BaseConexao.getConexao();
            String sql = "SELECT c.idCliente, c.email, p.idPessoa, p.nome, p.cpf, p.telefone "
                    + "FROM clientes c "
                    + "JOIN pessoa p ON c.idPessoa = p.idPessoa "
                    + "WHERE c.idCliente = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("idCliente"));
                cliente.setEmail(rs.getString("email"));
                cliente.setIdPessoa(rs.getLong("idPessoa"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }


    public void atualizarCliente(Long idCliente, Cliente cliente) {
        Connection conn = null;
        PreparedStatement pstPessoa = null;
        PreparedStatement pstCliente = null;

        try {
            conn = BaseConexao.getConexao();
            conn.setAutoCommit(false);


            pstPessoa = conn.prepareStatement("UPDATE pessoa SET nome = ?, cpf = ?, telefone = ? WHERE idPessoa = ?");
            pstPessoa.setString(1, cliente.getNome());
            pstPessoa.setString(2, cliente.getCpf());
            pstPessoa.setString(3, cliente.getTelefone());
            pstPessoa.setLong(4, cliente.getIdPessoa());
            pstPessoa.executeUpdate();


            pstCliente = conn.prepareStatement("UPDATE clientes SET email = ? WHERE idCliente = ?");
            pstCliente.setString(1, cliente.getEmail());
            pstCliente.setLong(2, idCliente);
            pstCliente.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstCliente != null) {
                    pstCliente.close();
                }
                if (pstPessoa != null) {
                    pstPessoa.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void excluirCliente(Long idCliente) {
        Connection conn = null;
        PreparedStatement pstCliente = null;
        PreparedStatement pstPessoa = null;
        ResultSet rs = null;

        try {
            conn = BaseConexao.getConexao();
            conn.setAutoCommit(false);

            Long idPessoa = null;


            pstCliente = conn.prepareStatement("SELECT idPessoa FROM clientes WHERE idCliente = ?");
            pstCliente.setLong(1, idCliente);
            rs = pstCliente.executeQuery();

            if (rs.next()) {
                idPessoa = rs.getLong("idPessoa");
            } else {
                throw new SQLException("Cliente não encontrado com id: " + idCliente);
            }

            pstCliente = conn.prepareStatement("DELETE FROM clientes WHERE idCliente = ?");
            pstCliente.setLong(1, idCliente);
            pstCliente.executeUpdate();


            pstPessoa = conn.prepareStatement("DELETE FROM pessoa WHERE idPessoa = ?");
            pstPessoa.setLong(1, idPessoa);
            pstPessoa.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstCliente != null) pstCliente.close();
                if (pstPessoa != null) pstPessoa.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }










}


