import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Parte1 extends PainelComImagem {
    private JPanel painelBotoes;

    public Parte1(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/01 - Painel.jpg"); // Define a imagem de fundo

        // Layout geral com BorderLayout
        var bl = new BorderLayout();
        setLayout(bl);

        // Label de boas-vindas no topo
        JLabel lblBemVindo = new JLabel("Bem-vindo ao Jogo!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 32));
        lblBemVindo.setForeground(Color.WHITE);
        add(lblBemVindo, BorderLayout.NORTH); // Adiciona a label no topo

        // Painel para os botões
        painelBotoes = new JPanel();
        painelBotoes.setLayout(null); // Layout absoluto para posicionar os botões
        painelBotoes.setOpaque(false); // Garantir que o fundo do painel seja transparente
        add(painelBotoes, BorderLayout.CENTER);

        // Criar botões
        criarBotaoVisivel(painelBotoes, 40, 260, 300, 220, Color.RED, e -> {
            System.out.println("Botão vermelho clicado!");
            layout.show(painelPrincipal, "Login");
        });

        criarBotaoVisivel(painelBotoes, 300, 440, 50, 60, Color.BLUE, e -> {
            System.out.println("Botão azul escuro clicado!");
        });

        criarBotaoVisivel(painelBotoes, 360, 240, 330, 220, Color.PINK, e -> {
            System.out.println("Botão rosa clicado!");
            layout.show(painelPrincipal, "Opções");
        });

        criarBotaoVisivel(painelBotoes, 520, 470, 70, 130, Color.GREEN, e -> {
            System.out.println("Botão verde clicado!");
        });

        criarBotaoVisivel(painelBotoes, 720, 230, 290, 250, Color.YELLOW, e -> {
            System.out.println("Botão amarelo clicado!");
        });

        // Adicionar listener para redimensionamento da janela
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reposicionarBotoes();
            }
        });
    }

    // Método para criar botões visíveis
    private void criarBotaoVisivel(JPanel painel, int x, int y, int largura, int altura, Color cor, ActionListener acao) {
        JButton botao = new JButton();
        botao.setBounds(x, y, largura, altura); // Posicionar o botão
        botao.setBackground(cor); // Define a cor de fundo do botão
        botao.setOpaque(true); // Torna o botão visível
        botao.setBorderPainted(true); // Exibe as bordas do botão
        botao.addActionListener(acao); // Define a ação para o botão
        painel.add(botao); // Adiciona o botão ao painel
    }

    // Método para reposicionar os botões ao redimensionar a janela
    private void reposicionarBotoes() {
        int largura = painelBotoes.getWidth();
        int altura = painelBotoes.getHeight();

        // Reposicionar os botões proporcionalmente
        for (Component comp : painelBotoes.getComponents()) {
            if (comp instanceof JButton) {
                Rectangle bounds = comp.getBounds();
                comp.setBounds(
                    (int) (bounds.x * largura / 1280.0), // Proporção horizontal
                    (int) (bounds.y * altura / 853.0),   // Proporção vertical
                    (int) (bounds.width * largura / 1280.0),
                    (int) (bounds.height * altura / 853.0)
                );
            }
        }
    }
}
