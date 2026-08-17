package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Categoria;
import Model.TipoTransacao;
import Model.Transacao;
import Util.ConnectionFactory;

public class TransacaoDAO {
    public void salvar(Transacao transacao) {
         String sql = "INSERT INTO transacao (categoria, valor, tipo, descricao) VALUES (?, ?, ?, ?)";
        
         try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, transacao.getCategoria().name());
            ps.setDouble(2, transacao.getValor());
            ps.setString(3, transacao.getTipo().name());
            ps.setString(4, transacao.getDescricao());

            ps.executeUpdate();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void atualizar(Transacao transacao) {
         String sql = "UPDATE transacao SET categoria = ?, valor = ?, tipo = ?, descricao = ? WHERE id = ?";
        
         try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, transacao.getCategoria().name());
            ps.setDouble(2, transacao.getValor());
            ps.setString(3, transacao.getTipo().name());
            ps.setString(4, transacao.getDescricao());
            ps.setInt(5, transacao.getId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Transação atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma transação encontrada com o ID informado.");
            }

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public List<Transacao> listar() {
         List<Transacao> lista = new ArrayList<>();
         String sql = "SELECT * FROM transacao";
        
         try (Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(sql)) {
            
            while(resultado.next()){
            int id = resultado.getInt("id");

            Categoria categoria =
                    Categoria.valueOf(resultado.getString("categoria"));

            double valor =
                    resultado.getDouble("valor");

            TipoTransacao tipo =
                    TipoTransacao.valueOf(resultado.getString("tipo"));

            String descricao =
                    resultado.getString("descricao");

            Transacao transacao =
                    new Transacao(id, categoria, valor, tipo, descricao);

            lista.add(transacao);
        }

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return lista;
    }

    public void deletar(int id) {
         String sql = "DELETE FROM transacao WHERE id = ?";
        
         try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Transação deletada com sucesso!");
            } else {
                System.out.println("Nenhuma transação encontrada com o ID informado.");
            }

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public Transacao buscarPorId(int id) {

    String sql = "SELECT * FROM transacao WHERE id = ?";

    try (
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {

        ps.setInt(1, id);

        try (ResultSet resultado = ps.executeQuery()) {

            if (resultado.next()) {

                Categoria categoria =
                        Categoria.valueOf(resultado.getString("categoria"));

                double valor =
                        resultado.getDouble("valor");

                TipoTransacao tipo =
                        TipoTransacao.valueOf(resultado.getString("tipo"));

                String descricao =
                        resultado.getString("descricao");

                return new Transacao(
                        resultado.getInt("id"),
                        categoria,
                        valor,
                        tipo,
                        descricao
                );
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

    public List<Transacao> buscarPorCategoria(Categoria categoria) {
         String sql = "SELECT * FROM transacao WHERE categoria = ?";
         List<Transacao> lista = new ArrayList<>();

    try (
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {

        ps.setString(1, categoria.name());

        try (ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {
                int id = 
                    resultado.getInt("id");
                double valor =
                        resultado.getDouble("valor");

                TipoTransacao tipo =
                        TipoTransacao.valueOf(resultado.getString("tipo"));

                String descricao =
                        resultado.getString("descricao");

                 lista.add(new Transacao(
                        id,
                        categoria,
                        valor,
                        tipo,
                        descricao
                ));
            }
        } 

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
    }

    public List<Transacao> buscarPorDescricao(String descricao) {
    String sql = "SELECT * FROM transacao WHERE descricao LIKE ?";
    List<Transacao> lista = new ArrayList<>();

    try (
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
    ) {

        ps.setString(1, "%" + descricao + "%");

        try (ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id");

                Categoria categoria =
                        Categoria.valueOf(resultado.getString("categoria"));

                double valor =
                        resultado.getDouble("valor");

                TipoTransacao tipo =
                        TipoTransacao.valueOf(resultado.getString("tipo"));

                String descricaoBanco =
                        resultado.getString("descricao");

                lista.add(new Transacao(
                        id,
                        categoria,
                        valor,
                        tipo,
                        descricaoBanco
                ));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
}


