import javax.swing.*;
import java.awt.*;

public class TremPortaFechada extends JPanel {

    private Image imagemFundo;
    private String mensagemTemporaria = "";
    public TremPortaFechada() {
        try {
            imagemFundo = new ImageIcon("imagens\\Fotos editadas\\20 - Porta fechada.jpg").getImage();
        } catch (Exception e) {
            System.err.println("Não foi possível carregar a imagem da porta fechada.");
        }
        setLayout(null);
        mensagemTemporaria = "Você ganhou 2 pontos!";
        
        JButton botaoConferir = new JButton("Conferir porta");
        botaoConferir.setBounds(730, 50, 200, 40); // Ajuste a posição/tamanho conforme necessário
        botaoConferir.setFont(new Font("Arial", Font.BOLD, 22));
        botaoConferir.setForeground(Color.WHITE);
        botaoConferir.setBackground(Color.RED);
        botaoConferir.setFocusPainted(false);
        botaoConferir.setBorderPainted(true);
        botaoConferir.setOpaque(true);
        botaoConferir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "A porta está trancada");
        });
        add(botaoConferir);

        
        JButton botaoUsarItens = new JButton("Usar Itens");
        botaoUsarItens.setBounds(330, 50, 200, 40); // Ajuste a posição/tamanho conforme necessário
        botaoUsarItens.setFont(new Font("Arial", Font.BOLD, 22));
        botaoUsarItens.setForeground(Color.WHITE);
        botaoUsarItens.setBackground(Color.RED);
        botaoUsarItens.setFocusPainted(false);
        botaoUsarItens.setBorderPainted(true);
        botaoUsarItens.setOpaque(true);
        botaoUsarItens.addActionListener(e -> {
            
            mensagemTemporaria = "";
            Container parent = this.getParent();
            if (parent instanceof JPanel) {
                LayoutManager layout = parent.getLayout();
                if (layout instanceof CardLayout) {
                    ((CardLayout) layout).show(parent, "TremDecisao");
                }
            }
        });
        add(botaoUsarItens);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
        
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            int x = 470;
            int y = 150;
            g.setFont(new Font("Arial", Font.BOLD, 32));
            
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}
