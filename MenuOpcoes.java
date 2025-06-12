import java.awt.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class MenuOpcoes extends JPanel {
    private final JLabel lblVolume;
    private final JSlider sliderVolume;
    private final JLabel lblTela;
    private final JButton btnVoltar;
    private Image backgroundImage;
    private static float volume = 50.0f; // vol inicial 0-100
    private static Clip audioClip;

    public MenuOpcoes(CardLayout layout, JPanel painelPrincipal) {
        backgroundImage = new ImageIcon("imagens/metro_blur.jpg").getImage();
        setLayout(null);

        lblVolume = new JLabel("Volume:", SwingConstants.CENTER);
        lblVolume.setFont(new Font("Arial", Font.BOLD, 24));
        lblVolume.setForeground(Color.WHITE);
        lblVolume.setBounds(390, 100, 500, 40);
        add(lblVolume);

        sliderVolume = new JSlider(0, 100, (int)volume);
        sliderVolume.setBounds(390, 160, 500, 40);
        sliderVolume.setOpaque(false);
        sliderVolume.addChangeListener(e -> {
            volume = sliderVolume.getValue();
            atualizarVolume();
        });
        add(sliderVolume);

        lblTela = new JLabel("Tela:", SwingConstants.CENTER);
        lblTela.setFont(new Font("Arial", Font.BOLD, 24));
        lblTela.setForeground(Color.WHITE);
        lblTela.setBounds(390, 240, 500, 40);
        add(lblTela);

        btnVoltar = criarBotao("Voltar");
        btnVoltar.setBounds(490, 300, 300, 80);
        btnVoltar.addActionListener(e -> {
            System.out.println("Botão Voltar clicado!");
            layout.show(painelPrincipal, "Menu");
            SomUtils.tocarSom("/sons/click.wav");
        });
        add(btnVoltar);
    }

    class RoundedBorder implements javax.swing.border.Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(128, 128, 128, 128));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(true);
        botao.setOpaque(false);
        botao.setBorder(new RoundedBorder(20));
        return botao;
    }

    public static void setAudioClip(Clip clip) {
        audioClip = clip;
        atualizarVolume();
    }

    private static void atualizarVolume() {
        if (audioClip != null) {
            try {
                FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
                float gain = (volume / 100.0f) * (gainControl.getMaximum() - gainControl.getMinimum()) + gainControl.getMinimum();
                gainControl.setValue(gain);
            } catch (Exception e) {
                System.err.println("Erro ao ajustar volume: " + e.getMessage());
            }
        }
    }
}