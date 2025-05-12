import java.awt.*;
import javax.swing.*;
import java.util.List;

public class MenuLogin extends PainelComImagem {
    private JLabel lblUsuario, lblSenha, lblNome;
    private JTextField txtUsuario, txtNome;
    private JPasswordField txtSenha;
    private JCheckBox chkMostrarSenha;
    private JButton btnEntrar, btnVoltar;
    private MetroviarioDAO dao = new MetroviarioDAO();

    public MenuLogin(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro_blur.jpg");
        setLayout(null);

        lblNome = new JLabel("Email:", SwingConstants.CENTER);
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
            String nome = txtNome.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            if ("admin".equals(nome) && "admin".equals(senha)) {
                layout.show(painelPrincipal, "Admin");
            } else if (!nome.isEmpty() && !senha.isEmpty()) {
                boolean encontrado = false;
                List<Metroviario> lista = dao.listarTodos();
                for (Metroviario m : lista) {
                    if (m.getNome().equals(nome) && m.getSenha().equals(senha)) {
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    layout.show(painelPrincipal, "Jogo");
                } else {
                    JOptionPane.showMessageDialog(this, "Nome ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
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
}