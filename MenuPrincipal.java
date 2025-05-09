import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPrincipal extends PainelComImagem {
    public MenuPrincipal(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro.jpg");
        setLayout(null);
        setOpaque(false);

        JButton btnJogar = criarBotao("Jogar");
        JButton btnOpcoes = criarBotao("Opções");
        JButton btnPlacar = criarBotao("Placar");
        JButton btnSair = criarBotao("Sair");

        btnJogar.setBounds(490, 200, 300, 60);
        btnOpcoes.setBounds(490, 280, 300, 60);
        btnPlacar.setBounds(490, 360, 300, 60);
        btnSair.setBounds(490, 440, 300, 60);

        btnJogar.addActionListener(e -> layout.show(painelPrincipal, "Login"));
        btnOpcoes.addActionListener(e -> layout.show(painelPrincipal, "Opções"));
        btnSair.addActionListener(e -> System.exit(0));

        add(btnJogar);
        add(btnOpcoes);
        add(btnPlacar);
        add(btnSair);

        // Adiciona animações aos botões
        adicionarAnimacoesBotoes(new JButton[]{btnJogar, btnOpcoes, btnPlacar, btnSair});
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 30));
        botao.setBackground(new Color(255, 255, 255, 128)); // Cor azul com transparência
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(true); // Permite que a cor de fundo seja exibida
        botao.setBorder(new RoundedBorder(20)); // Borda arredondada
        return botao;
    }

    private void adicionarAnimacoesBotoes(JButton[] botoes) {
        // Cor de fundo padrão com transparência
        Color buttonBackground = new Color(128, 128, 128, 128); // Cinza com 50% de transparência
        // Cor de fundo ao passar o mouse com transparência
        Color buttonHoverBackground = new Color(100, 100, 100, 180); // Cinza escuro com 70% de transparência

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(buttonHoverBackground);
                button.setBounds(button.getX() - 5, button.getY() - 5, button.getWidth() + 10, button.getHeight() + 10);
                button.revalidate();
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(buttonBackground);
                button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                button.revalidate();
                button.repaint();
            }
        };

        for (JButton botao : botoes) {
            botao.setBackground(buttonBackground);
            botao.setForeground(Color.WHITE);
            botao.setFocusPainted(false);
            botao.setContentAreaFilled(true);
            botao.setBorder(new RoundedBorder(20));
            botao.addMouseListener(mouseAdapter);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}