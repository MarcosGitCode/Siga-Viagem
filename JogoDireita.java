import java.awt.*;
import javax.swing.*;

public class JogoDireita extends BasePainelComBotao {

    public JogoDireita(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita2.png", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Adiciona o painel JogoItens ao painel principal
        painelPrincipal.add(new JogoItens(layout, painelPrincipal), "JogoItens");

        // Botão visível no painel
        JButton botaoSetaDireita = new JButton(">");
        botaoSetaDireita.setBounds(900, 300, 290, 300); // Define a posição e o tamanho do botão
        botaoSetaDireita.setContentAreaFilled(false); // Remove o fundo do botão
        botaoSetaDireita.setBorderPainted(false); // Remove as bordas do botão
        botaoSetaDireita.setFocusPainted(false); // Remove o destaque ao focar
        botaoSetaDireita.setOpaque(false); // Garante que o botão seja transparente
        botaoSetaDireita.setForeground(Color.WHITE); // Define a cor do texto
        botaoSetaDireita.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do texto
        botaoSetaDireita.addActionListener(e -> {
            System.out.println("Botão seta direita clicado!");
            layout.show(painelPrincipal, "JogoItens"); // Alterna para o painel JogoItens
        });

        add(botaoSetaDireita); // Adiciona o botão ao painel
    }
}
