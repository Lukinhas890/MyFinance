import java.util.ArrayList;

public class SistemaFinanceiro {
    private ArrayList<Transacao> transacoes = new ArrayList<>();
    
     public void adicionarTransacao(double valor, Categoria categoria, TipoTransacao tipo, String descricao){
        Transacao t = new Transacao(categoria, valor, tipo, descricao);
        transacoes.add(t);
     }

    public Transacao removerTransacao(int id) {
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
        for(Transacao transacao : transacoes){
            if(transacao.getCategoria() == categoria){
                System.out.println(transacao);
            }
        }
    }
}
