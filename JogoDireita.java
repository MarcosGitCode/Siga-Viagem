import java.awt.*;
import javax.swing.*;

public class JogoDireita extends BasePainelComBotao {

    public JogoDireita(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita2.png", layout, painelPrincipal);
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
        // Torna o botão totalmente transparente/invisível
        botaoVisivel.setContentAreaFilled(false);
        botaoVisivel.setOpaque(false);
        botaoVisivel.setBorderPainted(false);
        botaoVisivel.setFocusPainted(false);
        botaoVisivel.setText(""); // Sem texto
        botaoVisivel.addActionListener(e -> {
            System.out.println("Botão visível clicado!");
            layout.show(painelPrincipal, "JogoDireitaChave");
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
        
        JButton chaveCBTC = new JButton();
        chaveCBTC.setBounds(550, 650, 75, 75); // Posição e tamanho do botão
        chaveCBTC.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTC.setOpaque(false); // Torna o botão transparente
        chaveCBTC.setBorderPainted(false); // Remove as bordas
        chaveCBTC.setFocusPainted(false); // Remove o foco visual
        chaveCBTC.setText(""); // Remove o texto do botão
        chaveCBTC.addActionListener(e -> {
            System.out.println("Novo botão clicado!");
            layout.show(painelPrincipal, "JogoDireitaChaveCBTC"); // Navega para a tela JogoDireitaChaveCBTC
        });
        add(chaveCBTC); // Adiciona o botão ao painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
