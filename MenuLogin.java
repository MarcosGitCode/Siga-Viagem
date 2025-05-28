import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MenuLogin extends PainelComImagem {
    private JLabel lblUsuario, lblSenha, lblNome;
    private JTextField txtUsuario, txtNome;
    private JPasswordField txtSenha;
    private JCheckBox chkMostrarSenha;
    private JButton btnEntrar, btnVoltar;
    private MetroviarioDAO dao = new MetroviarioDAO();
    private int tempoDecorrido = 0; // Tempo em segundos

    public MenuLogin(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro_blur.jpg");

        // Teste de conexão
        Connection conn = Conexao.conectar();
        if (conn != null) {
            System.out.println("Conexão bem sucedida!");
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Falha na conexão!");
        }

        setLayout(null);

        lblNome = new JLabel("Registro:"); // Troca para Registro
        lblNome.setFont(new Font("Arial", Font.BOLD, 24));
        lblNome.setBounds(390, 140, 100, 30);
        lblNome.setForeground(Color.WHITE);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtNome);

        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 24));
        lblSenha.setBounds(390, 140, 100, 30);
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtSenha);

        chkMostrarSenha = new JCheckBox("Mostrar senha");
        chkMostrarSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        chkMostrarSenha.setForeground(Color.WHITE);
        chkMostrarSenha.setOpaque(false);
        chkMostrarSenha.addActionListener(e -> {
            if (chkMostrarSenha.isSelected()) {
                txtSenha.setEchoChar((char) 0);
            } else {
                txtSenha.setEchoChar('•');
            }
        });
        add(chkMostrarSenha);

        btnEntrar = criarBotao("Entrar");
        add(btnEntrar);

        btnVoltar = criarBotao("Voltar");
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            SomUtils.tocarSom("/sons/click.wav");
        });
        btnEntrar.addActionListener(e -> {
            String registro = txtNome.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            MetroviarioDAO dao = new MetroviarioDAO();
            boolean isAdmin = dao.verificarAdmin(registro, senha);
            boolean isMetroviario = dao.verificarMetroviario(registro, senha);

            // Login de teste para facilitar o desenvolvimento
            if (registro.equals("a") && senha.equals("a")) {
                UsuarioLogado.setRegistro("R12345-6"); // Registro fictício para testes
                layout.show(painelPrincipal, "Jogo"); // Redireciona para o jogo
                return;
            }

            if (isAdmin || isMetroviario) {
                UsuarioLogado.setRegistro(registro); // Armazena o registro do usuário logado
                layout.show(painelPrincipal, "Jogo"); // Redireciona para o jogo
            } else {
                JOptionPane.showMessageDialog(this,
                        "Registro ou senha inválidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Centraliza ao iniciar e ao redimensionar
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                centralizarComponentes();
            }
        });
        centralizarComponentes();
    }

    private void centralizarComponentes() {
        int w = getWidth();
        int h = getHeight();
        int compW = 500;
        int compH = 40;
        int x = (w - compW) / 2;

        lblNome.setBounds(x, h / 2 - 150, compW, compH);
        txtNome.setBounds(x, h / 2 - 100, compW, compH);
        lblSenha.setBounds(x, h / 2 - 40, compW, compH);
        txtSenha.setBounds(x, h / 2 + 10, compW, compH);
        chkMostrarSenha.setBounds(x, h / 2 + 55, 200, 30);
        btnEntrar.setBounds(x, h / 2 + 100, 220, 60);
        btnVoltar.setBounds(x + 280, h / 2 + 100, 220, 60);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(128, 128, 128, 128));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(new RoundedBorder(20));
        return botao;
    }

    private void iniciarTimer() {
        tempoDecorrido = 0; // Reseta o tempo ao iniciar o timer
        Timer timer = new Timer(1000, e -> {
            tempoDecorrido++; // Incrementa o tempo a cada segundo
            // System.out.println("Tempo decorrido: " + tempoDecorrido + " segundos.");
            // Adicione aqui a lógica que deseja executar periodicamente
        });
        timer.start();
    }
}