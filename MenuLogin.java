import java.awt.*;
import javax.swing.*;

public class MenuLogin extends PainelComImagem {
    private JLabel lblUsuario, lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnVoltar;

    public MenuLogin(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro_blur.jpg");
        setLayout(null);

        lblUsuario = new JLabel("Usuário:", SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 24));
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtUsuario);

        lblSenha = new JLabel("Senha:", SwingConstants.CENTER);
        lblSenha.setFont(new Font("Arial", Font.BOLD, 24));
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtSenha);

        btnEntrar = criarBotao("Entrar");
        add(btnEntrar);

        btnVoltar = criarBotao("Voltar");
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        btnEntrar.addActionListener(e -> layout.show(painelPrincipal, "Jogo"));

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

        lblUsuario.setBounds(x, h / 2 - 150, compW, compH);
        txtUsuario.setBounds(x, h / 2 - 100, compW, compH);
        lblSenha.setBounds(x, h / 2 - 40, compW, compH);
        txtSenha.setBounds(x, h / 2 + 10, compW, compH);
        btnEntrar.setBounds(x, h / 2 + 80, 220, 60);
        btnVoltar.setBounds(x + 280, h / 2 + 80, 220, 60);
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