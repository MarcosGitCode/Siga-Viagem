import java.awt.*;
import javax.swing.*;

public class JogoEsquerda extends BasePainelComBotao {

    public JogoEsquerda(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Botoeiras e sinaleira lateral - detalhe.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        painelPrincipal.add(new Trem1(layout, painelPrincipal), "Trem1");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 800, 600);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        criarBotaoVisivel(painelBotoes, 10, 200, 200, 200, Color.GREEN, e -> {
            System.out.println("Botão Trem1 clicado!");
            layout.show(painelPrincipal, "Trem1"); // Alterna para o painel JogoTrem1
        });
    }

    private void criarBotaoVisivel(JPanel painel, int x, int y, int width, int height, Color color, java.awt.event.ActionListener action) {
        JButton botao = new JButton();
        botao.setBounds(x, y, width, height);
        botao.setBackground(color); // Define a cor de fundo como verde
        botao.setContentAreaFilled(false); // Remove o fundo visível
        botao.setOpaque(false); // Garante que o botão seja transparente
        botao.setBorderPainted(false); // Remove as bordas do botão
        botao.addActionListener(action);
        painel.add(botao);
    }
}
