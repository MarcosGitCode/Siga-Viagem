import java.awt.*;
import javax.swing.*;

public class Trem1 extends BasePainelComBotao {

    public Trem1(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/13 - Visão geral do carro 5.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Criação do painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 1280, 856);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        // Botão para TremPortaAberta
        criarBotaoVisivel(painelBotoes, 1000, 200, 150, 300, Color.GREEN, e -> {
            System.out.println("Botão TremPortaAberta clicado!");
            layout.show(painelPrincipal, "TremPortaAberta");
        });

        // Botão Voltar para JogoEsquerda
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Remove o fundo visível
        botaoVoltar.setOpaque(true); // Garante que o botão seja transparente
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "JogoEsquerda"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel
    }

    private void criarBotaoVisivel(JPanel painel, int x, int y, int width, int height, Color color, java.awt.event.ActionListener action) {
        JButton botao = new JButton();
        botao.setBounds(x, y, width, height);
        botao.setOpaque(false); // Torna o botão transparente
        botao.setContentAreaFilled(false); // Remove a área de fundo
        botao.setBorderPainted(false); // Remove as bordas do botão
        botao.addActionListener(action);
        painel.add(botao);
    }
}
