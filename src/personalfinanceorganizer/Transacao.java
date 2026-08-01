package personalfinanceorganizer;
public class Transacao {
    //ATRIBUTOS
    private int id;
    private String categoria;
    private String descricao;
    private double valor;
    private String data;
    
    //CONSTRUTOR
    public Transacao(String categoria, String descricao, double valor, String data) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
    
    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "\n { " + "ID = " + id + "| Categoria = " + categoria + "| Descrição = " + descricao + "| Valor = " + valor + "| Data = " + data + " }";
    }
    
    
}
