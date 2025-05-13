import java.awt.*;
import javax.swing.*;

public class JogoEsquerda extends BasePainelComBotao {

    public JogoEsquerda(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/06 - Botoeiras e sinaleira lateral.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        painelPrincipal.add(new Trem1(layout, painelPrincipal), "Trem1");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 856, 800);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        criarBotaoVisivel(painelBotoes, 0, 0, 500, 800, Color.GREEN, e -> {
            System.out.println("Botão Trem1 clicado!");
            layout.show(painelPrincipal, "Trem1"); // Alterna para o painel JogoTrem1
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