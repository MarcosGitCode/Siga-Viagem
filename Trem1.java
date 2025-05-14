import java.awt.*;
import javax.swing.*;

public class Trem1 extends BasePainelComBotao {

    public Trem1(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/13 - Visão geral do carro 5.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Adiciona o painel TremPortaAberta ao painel principal
        painelPrincipal.add(new TremPortaAberta(), "TremPortaAberta");

        // Criação do painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 1280, 856);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        // Adiciona um botão ao painel
        criarBotaoVisivel(painelBotoes, 1000, 200, 150, 300, Color.GREEN, e -> {
            System.out.println("Botão TremPortaAberta clicado!");
            layout.show(painelPrincipal, "TremPortaAberta"); // Alterna para o painel TremPortaAberta
        });
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
