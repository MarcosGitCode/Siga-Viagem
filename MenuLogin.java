import java.awt.*;
import javax.swing.*;

public class MenuLogin extends JPanel {
    public MenuLogin(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(490, 300, 100, 30);
        add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsuario.setBounds(600, 300, 200, 30);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 18));
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(490, 350, 100, 30);
        add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSenha.setBounds(600, 350, 200, 30);
        add(txtSenha);

        JButton btnEntrar = criarBotao("Entrar");
        JButton btnVoltar = criarBotao("Voltar");

        btnEntrar.setBounds(600, 400, 200, 40);
        btnVoltar.setBounds(600, 450, 200, 40);

        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());
            if (usuario.equals("admin") && senha.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                layout.show(painelPrincipal, "Menu");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));

        add(btnEntrar);
        add(btnVoltar);
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