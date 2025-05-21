public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private int valorPontuacao;

    public Tarefa(int id, String titulo, String descricao, int valorPontuacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valorPontuacao = valorPontuacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValorPontuacao() {
        return valorPontuacao;
    }

    public void setValorPontuacao(int valorPontuacao) {
        this.valorPontuacao = valorPontuacao;
    }
}
