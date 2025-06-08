public class Metroviario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String registro;
    private int pontuacaoTotal;

    public Metroviario(int id, String nome, String email, String senha, String registro, int pontuacaoTotal) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.registro = registro;
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public Metroviario(String nome, String email, String senha, String registro, int pontuacaoTotal) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.registro = registro;
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getRegistro() { return registro; }
    public int getPontuacaoTotal() { return pontuacaoTotal; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setRegistro(String registro) { this.registro = registro; }
    public void setPontuacaoTotal(int pontuacaoTotal) { this.pontuacaoTotal = pontuacaoTotal; }
}