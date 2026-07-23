import java.util.ArrayList;

public class SistemaFinanceiro {
    private ArrayList<Transacao> transacoes = new ArrayList<>();
    
     public void adicionarTransacao(double valor, Categoria categoria, TipoTransacao tipo, String descricao){
        Transacao t = new Transacao(categoria, valor, tipo, descricao);
        transacoes.add(t);
     }

    public Transacao removerTransacao(int id){
        for(int i = 0; i < transacoes.size(); i++){
            if(id == transacoes.get(i).getId()){
                Transacao removida = transacoes.get(i);
                transacoes.remove(i);
                return removida;
            }
        }
        System.out.println("Transação inexistente");
        return null;
        }

     public void listarTransacoes(){
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação cadastrada.");
            return;
        }
        
        for(Transacao transacao : transacoes){
            System.out.println(transacao);
        }
     }

     public double calcularSaldo(){
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

    public void buscarPorCategoria(Categoria categoria){
        boolean encontrou = false;
        for(Transacao transacao : transacoes){
            if(transacao.getCategoria() == categoria){
                System.out.println(transacao);
                encontrou = true;
            }

            if (!encontrou) {
                System.out.println("Nenhuma transação encontrada.");
            }
        }
    }

    public Transacao buscarPorId(int id) throws TransacaoNaoEncontradaException {
    for (Transacao transacao : transacoes) {
        if (transacao.getId() == id) {
            return transacao;
        }
    }

    throw new TransacaoNaoEncontradaException("Nenhuma transação encontrada.");
}

    public void buscarPorDescricao(String descricao){
        boolean encontrou = false;
        String pesquisa = descricao.toLowerCase();
        for(Transacao transacao : transacoes){
            if(transacao.getDescricao().toLowerCase().contains(pesquisa)){
                System.out.println(transacao);
                encontrou = true;
            }
        }

        if (!encontrou) {
                System.out.println("Nenhuma transação encontrada.");
            }
    }
}
