import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuJogo extends PainelComImagem {
    public MenuJogo(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/01 - Painel.jpg"); // Define a imagem de fundo
        
        // Layout geral com BorderLayout
        var bl = new BorderLayout();
        setLayout(bl);

        // Label de boas-vindas no topo
        JLabel lblBemVindo = new JLabel("Bem-vindo ao Jogo!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 32));
        lblBemVindo.setForeground(Color.WHITE);
        add(lblBemVindo, BorderLayout.NORTH); // Adiciona a label no topo

        // Painel para os botões visíveis
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(null); // Layout absoluto para posicionar os botões
        painelBotoes.setOpaque(false); // Garantir que o fundo do painel seja transparente

        // Criar botões visíveis com base nas áreas coloridas da nova imagem
        // vermelho
        criarBotaoVisivel(painelBotoes, 40, 260, 300, 220, Color.RED, e -> {
            System.out.println("Botão vermelho clicado!");
            layout.show(painelPrincipal, "Login");
        });

        // azul escuro
        criarBotaoVisivel(painelBotoes, 300, 440, 50, 60, Color.BLUE, e -> {
            System.out.println("Botão azul escuro clicado!");
            // Ação alternativa
        });

        // rosa
        criarBotaoVisivel(painelBotoes, 360, 240, 330, 220, Color.PINK, e -> {
            System.out.println("Botão rosa clicado!");
            layout.show(painelPrincipal, "Opções");
        });

        // verde
        criarBotaoVisivel(painelBotoes, 520, 470, 70, 130, Color.GREEN, e -> {
            System.out.println("Botão verde clicado!");
            // Ação alternativa
        });

        // amarelo
        criarBotaoVisivel(painelBotoes, 720, 230, 290, 250, Color.YELLOW, e -> {
            System.out.println("Botão amarelo clicado!");
            // Ação alternativa
        });

        // Adicionar o painel com os botões visíveis no centro
        add(painelBotoes, BorderLayout.CENTER);
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
}
