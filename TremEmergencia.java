import javax.swing.*;
import java.awt.*;

public class TremEmergencia extends JPanel {

    private Image imagemFundo;

    public TremEmergencia(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Botão Voltar para JogoEsquerda
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "TremPortaAberta");
        });
        add(botaoVoltar);

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
        
        // Carrega a imagem de fundo
        String caminho = "imagens/Fotos editadas/16 - Dispositivos de emergência.jpg";
        ImageIcon imagemOriginal = new ImageIcon(caminho);
        imagemFundo = imagemOriginal.getImage().getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, 1280, 856, this);
        }
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}

