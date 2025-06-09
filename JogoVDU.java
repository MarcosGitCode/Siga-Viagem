import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JButton;

public class JogoVDU extends BasePainelComBotao {

    public JogoVDU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/04 - VDU.jpg", layout, painelPrincipal); // Define a imagem de fundo

        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

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

        JButton botaoMenu = new JButton("Menu");
        botaoMenu.setBounds(580, 10, 100, 60);
        botaoMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoMenu.setForeground(Color.white);
        botaoMenu.setBackground(Color.BLACK);
        botaoMenu.setContentAreaFilled(true);
        botaoMenu.setOpaque(true);
        botaoMenu.setBorderPainted(false);
        botaoMenu.setLayout(null);
        botaoMenu.addActionListener(e -> {
            System.out.println("Botão Menu clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);
    }
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Desenha o inventário no canto superior direito
    InventarioUI.desenhar((Graphics2D) g, getWidth());
}
}