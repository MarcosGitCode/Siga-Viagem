import java.awt.*;
import java.util.List;
import javax.swing.*;

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
        setLayout(null);

        lblNome = new JLabel("Registro:", SwingConstants.CENTER); // Troca para Registro
        lblNome.setFont(new Font("Arial", Font.BOLD, 24));
        lblNome.setForeground(Color.WHITE);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtNome);

        lblSenha = new JLabel("Senha:", SwingConstants.CENTER);
        lblSenha.setFont(new Font("Arial", Font.BOLD, 24));
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

        btnEntrar.addActionListener(e -> {
            String registro = txtNome.getText().trim(); // Agora recebe o registro
            String senha = new String(txtSenha.getPassword()).trim();

            // Login temporário para testes
            if (registro.equals("a") && senha.equals("a")) {
                iniciarTimer(); // Inicia o timer após login bem-sucedido
                layout.show(painelPrincipal, "Jogo");
                return;
            }

            if (!registro.isEmpty() && !senha.isEmpty()) {
                // Verifica se é administrador no banco de dados
                boolean adminEncontrado = false;
                try (java.sql.Connection conn = Conexao.conectar();
                     java.sql.PreparedStatement stmt = conn.prepareStatement(
                         "SELECT * FROM administradores WHERE registro = ? AND senha = ?")) {
                    stmt.setString(1, registro);
                    stmt.setString(2, senha);
                    try (java.sql.ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            adminEncontrado = true;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (adminEncontrado) {
                    iniciarTimer(); // Inicia o timer após login bem-sucedido
                    layout.show(painelPrincipal, "Admin");
                    return;
                }

                // Verifica se é metroviário
                boolean encontrado = false;
                List<Metroviario> lista = dao.listarTodos();
                for (Metroviario m : lista) {
                    if (m.getRegistro().equals(registro) && m.getSenha().equals(senha)) {
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    iniciarTimer(); // Inicia o timer após login bem-sucedido
                    layout.show(painelPrincipal, "Jogo");
                } else {
                    JOptionPane.showMessageDialog(this, "Registro ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Registro ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
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
            //System.out.println("Tempo decorrido: " + tempoDecorrido + " segundos.");
            // Adicione aqui a lógica que deseja executar periodicamente
        });
        timer.start();
    }
}