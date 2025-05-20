import java.awt.*;
import javax.swing.*;

public class JogoDireitaChave extends BasePainelComBotao {

    public JogoDireitaChave(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita3.png", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Remova/adicione painéis apenas no Menu.java para evitar loops!

        // JLabel para a imagem da chave (inicialmente invisível)
        JLabel chaveLabel = new JLabel();
        chaveLabel.setBounds(546, 403, 100, 100); // Mesma posição/tamanho do botão
        chaveLabel.setOpaque(false);
        chaveLabel.setVisible(false); // Só aparece quando clicar no botão
        add(chaveLabel);

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

        // Botão visível de 100x100px na posição (546, 403)
        JButton botaoVisivel = new JButton();
        botaoVisivel.setBounds(546, 390, 100, 100);
        // Torna o botão totalmente transparente
        botaoVisivel.setContentAreaFilled(false);
        botaoVisivel.setOpaque(false);
        botaoVisivel.setBorderPainted(false);
        botaoVisivel.addActionListener(e -> {
            System.out.println("Botão visível clicado!");
            chaveLabel.setIcon(new ImageIcon("imagens/Fotos editadas/chave.png"));
            chaveLabel.setVisible(true);
        });
        add(botaoVisivel);

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
