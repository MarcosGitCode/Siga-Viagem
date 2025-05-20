import java.awt.*;
import javax.swing.*;

public class JogoEsquerdaOficial extends BasePainelComBotao {

    public JogoEsquerdaOficial(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/JogoEsquerdaOficial.png", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Remova/adicione painéis apenas no Menu.java para evitar loops!

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 856, 800);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        

        // Botão Voltar para Parte1
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
            layout.show(painelPrincipal, "Jogo"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

        // Botão transparente de 500x500px na metade esquerda da tela
        JButton botaoEsquerda = new JButton();
        botaoEsquerda.setBounds(0, 150, 500, 500); // Posição: do topo (150px) até 500px de altura
        botaoEsquerda.setOpaque(false);
        botaoEsquerda.setContentAreaFilled(false);
        botaoEsquerda.setBorderPainted(false);
        botaoEsquerda.addActionListener(e -> {
            layout.show(painelPrincipal, "JogoItens");
        });
        add(botaoEsquerda);
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