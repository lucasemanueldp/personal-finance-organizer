package personalfinanceorganizer;

import java.util.ArrayList;
import java.util.List;

public class ServicoTransacao {
    //ATRIBUTO 
    private RepositorioTransacao repositorio;
    
    //CONSTRUTOR
    public ServicoTransacao(RepositorioTransacao repositorio) {
        this.repositorio = repositorio;
    }
    
    //MÉTODOS
    public void verificaCadastro(Transacao t) {
        if (!t.getCategoria().equalsIgnoreCase("Receita") &&
               !t.getCategoria().equalsIgnoreCase("Despesa")) {
            throw new IllegalArgumentException("Categoria inválida. " +
                    "Digite Receita ou Despesa.");
        }
        if (t.getValor() <= 0) {
            throw new IllegalArgumentException("O valor precisa ser maior que zero " +
                    "(ex.: 150,00).");
        }
        if(t.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição não pode ficar em branco. " +
                    "Informe um nome para a transação (ex.: Aluguel, Mercado).");
        }
        repositorio.cadastrar(t);

    }
    public double calcularSaldo() {
        ArrayList <Transacao> transacoes = this.repositorio.listar();
        double saldo= 0;
        for (int i=0; i< transacoes.size(); i++) {
            if(transacoes.get(i).getCategoria().equalsIgnoreCase("Receita")) {
                saldo = saldo + transacoes.get(i).getValor();
            }else {
                saldo = saldo - transacoes.get(i).getValor();
            }
        }
        return saldo;
    }
    public void listarFormatado(List<Transacao> transacoes) {
        if (transacoes.size()== 0) {
            System.out.println("Não possui nenhum item cadastrado no momento.");
        }else {
            for (int i=0;i < transacoes.size();i++) {
                System.out.println(transacoes.get(i));
            }
        }
        }
    public List<Transacao> listarTodas() {
        return this.repositorio.listar();
        }

    public List<Transacao> listarReceitas() {
        return this.repositorio.listar().stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase("Receita"))
                .toList();

    }
    public List<Transacao> listarDespesas() {
        return this.repositorio.listar().stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase("Despesa"))
                .toList();

    }
    public List<Transacao> listarMaiorValor() {
        return this.repositorio.listar().stream()
                .sorted((t1,t2) -> Double.compare(t2.getValor(),t1.getValor()))
                .toList();
    }
    public List<Transacao> listarMenorValor() {
        return this.repositorio.listar().stream()
                .sorted((t1,t2) -> Double.compare(t1.getValor(),t2.getValor()))
                .toList();
    }

    public void consultarFormatado(int id) {
        Transacao t = this.repositorio.consultar(id);
        if (t == null) {
            System.out.println("Nenhuma transação encontrada para o ID informado.");
        }else {
            System.out.println(t);
        }
    }
    public void removerFormatado(int id) {
        Transacao t = this.repositorio.consultar(id);
        this.repositorio.remover(id);
        System.out.println("O item "+ t.getDescricao() +" foi removido com sucesso.");
    }

    public void analisarSaude() {
        double saldo = calcularSaldo();
        double totalReceita = 0;
        double totalDespesas= 0;
        ArrayList<Transacao> transacoes = this.repositorio.listar();
        for (int i=0;i< transacoes.size();i++) {
            if (transacoes.get(i).getCategoria().equalsIgnoreCase("Receita")) {
                totalReceita += transacoes.get(i).getValor();
            }else {
                totalDespesas += transacoes.get(i).getValor();
            }
        }
        System.out.println("----------------------------");
        System.out.println("     SAÚDE FINANCEIRA       ");
        System.out.println("----------------------------");
        System.out.println("Total Receitas: R$ " + String.format("%.2f", totalReceita));
        System.out.println("Total Despesas: R$ " + String.format("%.2f", totalDespesas));
        System.out.println("Saldo Atual:    R$ " + String.format("%.2f", saldo));
        System.out.println("----------------------------");
        
        if (saldo > 0) {
        System.out.println("""
        =============================================================================
        🟢 PARABÉNS! Seu saldo está positivo.

        Você terminou o período gastando menos do que ganhou.
        Agora seu objetivo é fazer esse dinheiro trabalhar por você.

        O QUE FAZER AGORA?

        ✔ Invista entre 20% e 50% deste saldo.
        ✔ Se já possui uma reserva de emergência, invista 100% do excedente.
        ✔ Evite aumentar seu padrão de vida apenas porque sobrou dinheiro.
        ✔ Analise quais foram seus maiores gastos e veja onde ainda pode economizar.

        OBJETIVO

        Transforme esse resultado em um hábito.
        Pequenas sobras recorrentes geram grandes patrimônios.

        "A riqueza não vem apenas de ganhar mais.
         Ela nasce da diferença entre o que você ganha e o que você gasta."

        =============================================================================
        """);
        } else if (saldo < 0) {
        System.out.println("""
        🔴 ATENÇÃO! Seu saldo está negativo.
        """);

        System.out.println("Suas despesas superam suas receitas em R$ "
        + String.format("%.2f", Math.abs(saldo)));

        System.out.println("""

        Neste período você gastou mais do que recebeu.
        A boa notícia é que isso pode ser corrigido rapidamente com algumas ações.

        O QUE FAZER AGORA?

        ✔ Reduza seus gastos em 10% a 20% no próximo mês.
        ✔ Identifique seus 3 maiores gastos.
        ✔ Corte pelo menos 1 despesa não essencial.
        ✔ Suspenda compras por impulso.
        ✔ Evite novos parcelamentos.
        ✔ Priorize quitar dívidas com juros mais altos.
        ✔ Se necessário, busque negociações de maior parcelamento da dívida, para
          ganhar tempo.

        OBJETIVO

        Primeiro volte ao saldo positivo.
        Depois pense em investir.

        "Livre-se das dívidas antes de buscar multiplicar seu dinheiro."

        =============================================================================
        """);

        } else {
        System.out.println("""
        =============================================================================                   
        🟡 Seu saldo terminou exatamente em zero. Terreno Perigoso!

        Você conseguiu manter o equilíbrio financeiro.
        Isso é melhor do que terminar no negativo, mas ainda não houve sobra para construir patrimônio.

        O QUE FAZER AGORA?

        ✔ Economize entre 5% e 10% da próxima renda.
        ✔ Revise seus 3 maiores gastos.
        ✔ Corte pelo menos 1 gasto não essencial.
        ✔ Planeje suas despesas antes do próximo período.

        OBJETIVO

        Seu foco agora é transformar um saldo zerado em um saldo positivo todos os meses.

        "Quem consegue guardar um pouco hoje,
        conquista muito mais liberdade amanhã."
        =============================================================================                                                                      
        """);
        }
    }
}
