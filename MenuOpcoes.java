import java.awt.*;
import javax.swing.*;

public class MenuOpcoes extends JPanel {
    public MenuOpcoes(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        JLabel lblVolume = new JLabel("Volume:", SwingConstants.CENTER);
        lblVolume.setFont(new Font("Arial", Font.BOLD, 24));
        lblVolume.setForeground(Color.WHITE);
        lblVolume.setBounds(390, 100, 500, 40);
        add(lblVolume);

        JSlider sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setBounds(390, 160, 500, 40);
        sliderVolume.setOpaque(false);
        add(sliderVolume);

        JButton btnTelaCheia = criarBotao("Tela Cheia");
        JButton btnModoJanela = criarBotao("Modo Janela");
        JButton btnVoltar = criarBotao("Voltar");

        btnTelaCheia.setBounds(390, 240, 220, 60);
        btnModoJanela.setBounds(670, 240, 220, 60);
        btnVoltar.setBounds(490, 320, 300, 80);

        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));

        add(btnTelaCheia);
        add(btnModoJanela);
        add(btnVoltar);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(128, 128, 128, 128));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(new RoundedBorder(20));
        return botao;
    }
}