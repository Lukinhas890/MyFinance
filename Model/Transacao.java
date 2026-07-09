public class Transacao {
    private int id;
    private Categoria categoria;
    private double valor;
    private TipoTransacao tipo;
    private String descricao;
    private static int proximoId = 1;

    public Transacao(Categoria categoria, double valor, TipoTransacao tipo, String descricao) {
        this.id = proximoId;
        this.categoria = categoria;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        proximoId++;

    }
    public Categoria getCategoria() {
        return categoria;
    }

    public double getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    

}