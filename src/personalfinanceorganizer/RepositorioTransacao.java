package personalfinanceorganizer;

import java.util.ArrayList;

public class RepositorioTransacao {
    //ATRIBUTOS
    private ArrayList<Transacao> transacoes; 
    private int proximoId;
    
    //CONSTRUTOR
    public RepositorioTransacao() {
        this.transacoes = new ArrayList<Transacao>();
        this.proximoId = 1;
    }

    //MÉTODOS
    public void cadastrar(Transacao t) {
        t.setId(this.proximoId);
        this.proximoId++;
        this.transacoes.add(t);
        }
    
    public ArrayList<Transacao> listar() {
        return this.transacoes;
    }
    public Transacao consultar(int id) {
        for (int i = 0; i< this.transacoes.size();i++) {
            if(this.transacoes.get(i).getId() == id)
            return this.transacoes.get(i);
        }
        return null;
    }
    public void remover(int id) {

        for (int i=0; i< this.transacoes.size();i++) {
            if (this.transacoes.get(i).getId() == id) {
                this.transacoes.remove(i);
                return;
            }
        }
        throw new TransacaoNaoEncontradaException("Transação não encontrada para o ID informado.");


    }
    
}
