public class TransacaoNaoEncontradaException extends Exception{
    
    public TransacaoNaoEncontradaException(){
        super("Transação não encontrada");
    }

    public TransacaoNaoEncontradaException(String message){
        super(message);
    }
}
