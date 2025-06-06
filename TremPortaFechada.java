import javax.swing.*;
import java.awt.*;

public class TremPortaFechada extends JPanel {

    private Image imagemFundo;

    public TremPortaFechada() {
        try {
            imagemFundo = new ImageIcon("imagens\\Fotos editadas\\20 - Porta fechada.jpg").getImage();
        } catch (Exception e) {
            System.err.println("Não foi possível carregar a imagem da porta fechada.");
        }
        setLayout(null);

        // Botão "Conferir porta"
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

        // Botão "Usar Itens"
        JButton botaoUsarItens = new JButton("Usar Itens");
        botaoUsarItens.setBounds(330, 50, 200, 40); // Ajuste a posição/tamanho conforme necessário
        botaoUsarItens.setFont(new Font("Arial", Font.BOLD, 22));
        botaoUsarItens.setForeground(Color.WHITE);
        botaoUsarItens.setBackground(Color.RED);
        botaoUsarItens.setFocusPainted(false);
        botaoUsarItens.setBorderPainted(true);
        botaoUsarItens.setOpaque(true);
        botaoUsarItens.addActionListener(e -> {
            // Troca para o painel TremDecisao
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
    }
}
