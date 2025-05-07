import java.awt.*;
import javax.swing.*;

public class MenuJogo extends PainelComImagem {
    public MenuJogo(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/01 - Painel.jpg");
        //setLayout(null);
        var bl = new BorderLayout();
        setLayout(bl);
        add(new JButton("OK"), BorderLayout.CENTER);
        JLabel lblBemVindo = new JLabel("Bem-vindo ao Jogo!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 32));
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setBounds(390, 50, 500, 50);
        add(lblBemVindo, BorderLayout.NORTH);
    }
}