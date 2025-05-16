import java.awt.*;
import javax.swing.*;

public class JogoDireita extends BasePainelComBotao {

    public JogoDireita(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita2.png", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Remova/adicione painéis apenas no Menu.java para evitar loops!

        // Botão visível no painel
        JButton botaoSetaDireita = new JButton("");
        botaoSetaDireita.setBounds(900, 300, 290, 300);
        botaoSetaDireita.setContentAreaFilled(false);
        botaoSetaDireita.setBorderPainted(false);
        botaoSetaDireita.setFocusPainted(false);
        botaoSetaDireita.setOpaque(false);
        botaoSetaDireita.setForeground(Color.WHITE);
        botaoSetaDireita.setFont(new Font("Arial", Font.BOLD, 20));
        botaoSetaDireita.addActionListener(e -> {
            System.out.println("Botão seta direita clicado!");
            layout.show(painelPrincipal, "JogoEsquerda");
        });
        add(botaoSetaDireita);

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
    }
}
