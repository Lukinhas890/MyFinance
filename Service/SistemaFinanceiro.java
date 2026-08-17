package Service;

import Model.Transacao;
import Model.Categoria;
import Model.TipoTransacao;
import Model.TransacaoNaoEncontradaException;

import java.util.List;

import DAO.TransacaoDAO;

public class SistemaFinanceiro {
    private TransacaoDAO transacaoDAO = new TransacaoDAO();
    
     public void adicionarTransacao(double valor, Categoria categoria, TipoTransacao tipo, String descricao){
        Transacao t = new Transacao(categoria, valor, tipo, descricao);
        transacaoDAO.salvar(t);
     }

    public void atualizarTransacao(int id, double valor, Categoria categoria,
        TipoTransacao tipo, String descricao)
        throws TransacaoNaoEncontradaException {

        Transacao transacao = transacaoDAO.buscarPorId(id);

        if (transacao == null) {
            throw new TransacaoNaoEncontradaException("Nenhuma transação encontrada com o ID: " + id);
        }

        Transacao atualizada = new Transacao(
                id,
                categoria,
                valor,
                tipo,
                descricao
        );

        transacaoDAO.atualizar(atualizada);
    }

    public Transacao removerTransacao(int id) {

        Transacao removida = transacaoDAO.buscarPorId(id);

        if (removida != null) {
            transacaoDAO.deletar(id);
            return removida;
        }

        return null;
    }

     public void listarTransacoes(){
        List<Transacao> transacoes = transacaoDAO.listar();
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação cadastrada.");
            return;
        }
        
        for(Transacao transacao : transacoes){
            System.out.println(transacao);
        }
     }

     public double calcularSaldo(){
        List<Transacao> transacoes = transacaoDAO.listar();
        double saldo = 0;
        
        for(Transacao transacao : transacoes){
            if(transacao.getTipo() == TipoTransacao.RECEITA) {
                saldo += transacao.getValor();
            } else if(transacao.getTipo() == TipoTransacao.DESPESA) {
                saldo -= transacao.getValor();
            }
        }
        return saldo;
    }

    public List<Transacao> buscarPorCategoria(Categoria categoria)
            throws TransacaoNaoEncontradaException {

        List<Transacao> transacoes =
                transacaoDAO.buscarPorCategoria(categoria);

        if (transacoes.isEmpty()) {
            throw new TransacaoNaoEncontradaException(
                    "Nenhuma transação encontrada com a categoria: " + categoria
            );
        }

        return transacoes;
    }

    public Transacao buscarPorId(int id) throws TransacaoNaoEncontradaException {
        Transacao transacao = transacaoDAO.buscarPorId(id);

        if (transacao == null) {
            throw new TransacaoNaoEncontradaException(
            "Nenhuma transação encontrada com o ID: " + id
        );

        }   
        System.out.println(transacao);
        return transacao;
    }

    public List<Transacao> buscarPorDescricao(String descricao)
            throws TransacaoNaoEncontradaException {

        List<Transacao> transacoes =
                transacaoDAO.buscarPorDescricao(descricao);

        if (transacoes.isEmpty()) {
            throw new TransacaoNaoEncontradaException(
                    "Nenhuma transação encontrada para: " + descricao
            );
        }

        return transacoes;
    }

}
