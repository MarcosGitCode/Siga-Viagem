import java.awt.*;
import javax.swing.*;


public class MenuLogin extends PainelComImagem {
    public MenuLogin(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro_blur.jpg");
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário:", SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 24));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(390, 300, 500, 40);
        add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsuario.setBounds(390, 350, 500, 40);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:", SwingConstants.CENTER);
        lblSenha.setFont(new Font("Arial", Font.BOLD, 24));
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(390, 410, 500, 40);
        add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSenha.setBounds(390, 460, 500, 40);
        add(txtSenha);

        JButton btnEntrar = criarBotao("Entrar");
        btnEntrar.setBounds(390, 530, 220, 60);
        add(btnEntrar);

        JButton btnVoltar = criarBotao("Voltar");
        btnVoltar.setBounds(670, 530, 220, 60);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        btnEntrar.addActionListener(e -> layout.show(painelPrincipal, "Jogo"));
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