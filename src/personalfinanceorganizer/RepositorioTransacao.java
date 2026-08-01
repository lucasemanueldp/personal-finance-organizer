package personalfinanceorganizer;
public class RepositorioTransacao {
    //ATRIBUTOS
    private Transacao[] transacoes; 
    private int contador;
    
    //CONSTRUTOR
    public RepositorioTransacao(int capacidade) {
        this.transacoes = new Transacao[capacidade];
        this.contador = 0;
    }
    //GETTERS

    public int getContador() {
        return contador;
    }
    //MÉTODOS
    public void cadastrar(Transacao t) {
        if (this.contador < this.transacoes.length) {
        t.setId(this.contador + 1);
        this.transacoes[this.contador] = t;
        this.contador++;
        } else {
            System.out.println("Repositório Cheio! Não é possível cadastrar mais itens.");
        }
    }
    public Transacao[] listar() {
        return transacoes;
    }
    public Transacao consultar(int id) {
        for (int i = 0; i< this.contador;i++) {
            if(this.transacoes[i].getId() == id)
            return transacoes[i];
        }
        return null;
    }
    public boolean remover(int id) {
        for (int i=0; i< this.contador;i++) {
            if (this.transacoes[i].getId() == id) {
                for(int d=i;d<this.contador-1;d++){
                    transacoes[d] = transacoes[d+1];
                }
            this.contador--;
            return true;
            }
        }
        return false;
    }
}
