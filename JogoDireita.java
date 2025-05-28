import java.awt.*;
import javax.swing.*;

public class JogoDireita extends BasePainelComBotao {

    public JogoDireita(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita2.png", layout, painelPrincipal);
        setLayout(null); // Absolute layout for precise positioning

        // JLabel for the key image (initially visible)
        JLabel chaveLabel = new JLabel();
        chaveLabel.setBounds(546, 403, 100, 100); // Position and size of the key
        chaveLabel.setOpaque(false);
        chaveLabel.setIcon(new ImageIcon("imagens/Fotos editadas/chave.png")); // Set the key image
        chaveLabel.setVisible(true); // Start with the key visible
        add(chaveLabel); // Add the key label on top of the background

        // Button to navigate to the next panel
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

        // Button to hide the key image
        JButton botaoVisivel = new JButton();
        botaoVisivel.setBounds(546, 390, 100, 100); // Same position as the key
        botaoVisivel.setContentAreaFilled(false);
        botaoVisivel.setOpaque(false);
        botaoVisivel.setBorderPainted(false);
        botaoVisivel.addActionListener(e -> {
            System.out.println("Botão visível clicado!");
            chaveLabel.setIcon(null); // Remove the key image
            chaveLabel.setVisible(false); // Hide the key label
        });
        add(botaoVisivel);

        // Back button to return to the previous panel
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Position and size of the button
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Font settings
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Button background color
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo"); // Go back to the previous panel
        });
        add(botaoVoltar);

        // Additional button for navigation
        JButton chaveCBTC = new JButton();
        chaveCBTC.setBounds(550, 650, 75, 75); // Position and size of the button
        chaveCBTC.setContentAreaFilled(false); // Remove the fill area
        chaveCBTC.setOpaque(false); // Make the button transparent
        chaveCBTC.setBorderPainted(false); // Remove the border
        chaveCBTC.setFocusPainted(false); // Remove the focus visual
        chaveCBTC.setText(""); // Remove the button text
        chaveCBTC.addActionListener(e -> {
            System.out.println("Novo botão clicado!");
            layout.show(painelPrincipal, "JogoDireitaChaveCBTC"); // Navigate to the next screen
        });
        add(chaveCBTC);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the inventory in the top-right corner
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
