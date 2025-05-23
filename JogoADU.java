import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JogoADU extends BasePainelComBotao {

    public JogoADU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/02 - ADU e sinaleiras.jpg", layout, painelPrincipal);
        setLayout(null);

        // Botão Voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            SomUtils.tocarSom("/sons/click.wav"); // Caminho relativo ao classpath
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo");
        });
        add(botaoVoltar);

        // Outros componentes do jogo aqui...
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
