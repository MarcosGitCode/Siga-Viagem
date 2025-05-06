import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame {
    private CardLayout layout;
    private JPanel painelPrincipal;
    private JPanel painelCentral;
    private JButton btnJogar;
    private JButton btnOpcoes;
    private JButton btnPlacar;
    private JButton btnSair;
    private JLabel lblVolume;
    private JSlider sliderVolume;
    private JLabel lblTela;
    private JButton btnTelaCheia;
    private JButton btnModoJanela;
    private JButton btnVoltar;
    private JPanel painelOpcoes;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public Menu() {
        setTitle("Siga");
        setSize(1280, 853);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);

        JPanel painel = new PainelComImagem("C:\\Users\\User1\\Downloads\\metro.png");
        painel.setLayout(new BorderLayout());

        painelCentral = new JPanel(null);
        painelCentral.setOpaque(false);

        int buttonWidth = 300;
        int buttonHeight = 60;
        int spacing = 20;

        btnJogar = criarBotao("Jogar");
        btnOpcoes = criarBotao("Opções");
        btnPlacar = criarBotao("Placar");
        btnSair = criarBotao("Sair");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Color buttonBackground = new Color(128, 128, 128, 128);
        Color buttonForeground = Color.WHITE;

        JButton[] botoes = {btnJogar, btnOpcoes, btnPlacar, btnSair};
        for (JButton botao : botoes) {
            botao.setBackground(buttonBackground);
            botao.setForeground(buttonForeground);
            botao.setFont(buttonFont);
            botao.setBorder(new RoundedBorder(20));
            botao.setContentAreaFilled(false);
            botao.setFocusPainted(false);
        }

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(new Color(100, 100, 100, 128));
                button.setBounds(button.getX() - 5, button.getY() - 5, button.getWidth() + 10, button.getHeight() + 10);
                button.revalidate();
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(buttonBackground);
                button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                button.revalidate();
                button.repaint();
            }
        };

        for (JButton botao : botoes) {
            botao.addMouseListener(mouseAdapter);
        }

        btnSair.addActionListener(e -> System.exit(0));
        btnOpcoes.addActionListener(e -> layout.show(painelPrincipal, "Opções"));

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int startX = (panelWidth - buttonWidth) / 2;
        int startY = (panelHeight - (4 * buttonHeight + 3 * spacing)) / 2;

        btnJogar.setBounds(startX, startY, buttonWidth, buttonHeight);
        btnOpcoes.setBounds(startX, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        btnPlacar.setBounds(startX, startY + 2 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        btnSair.setBounds(startX, startY + 3 * (buttonHeight + spacing), buttonWidth, buttonHeight);

        painelCentral.add(btnJogar);
        painelCentral.add(btnOpcoes);
        painelCentral.add(btnPlacar);
        painelCentral.add(btnSair);

        painel.add(painelCentral, BorderLayout.CENTER);

        painelPrincipal.add(painel, "Menu");

        criarPainelOpcoes();
        criarPainelLogin();

        add(painelPrincipal);

        setVisible(true);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
                super.paintComponent(g);
            }

            @Override
            public void setContentAreaFilled(boolean b) {
            }
        };

        botao.setPressedIcon(null);

        return botao;
    }

    private void criarPainelOpcoes() {
        painelOpcoes = new PainelComImagem("imagens/metroBlur.png");
        painelOpcoes.setBackground(Color.WHITE);

        int panelWidth = 1280;
        int panelHeight = 853;

        lblVolume = new JLabel("Volume:", SwingConstants.CENTER);
        lblVolume.setFont(new Font("Arial", Font.BOLD, 24));
        lblVolume.setForeground(Color.WHITE);
        lblVolume.setBounds((panelWidth - 500) / 2, 100, 500, 40);
        painelOpcoes.add(lblVolume);

        sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setBounds((panelWidth - 500) / 2, 160, 500, 40);
        sliderVolume.setOpaque(false);
        sliderVolume.setBackground(new Color(0, 0, 0, 0));
        sliderVolume.setForeground(Color.WHITE);

        JLabel lblContador = new JLabel(String.valueOf(sliderVolume.getValue()), SwingConstants.CENTER);
        lblContador.setFont(new Font("Arial", Font.BOLD, 18));
        lblContador.setForeground(Color.WHITE);
        lblContador.setBounds(sliderVolume.getX() + sliderVolume.getValue() * sliderVolume.getWidth() / 100 - 15, 200, 50, 30);
        painelOpcoes.add(lblContador);

        sliderVolume.addChangeListener(e -> {
            int sliderValue = sliderVolume.getValue();
            lblContador.setText(String.valueOf(sliderValue));
            int contadorX = sliderVolume.getX() + (int) ((sliderValue / 100.0) * sliderVolume.getWidth()) - 25;
            lblContador.setBounds(contadorX, sliderVolume.getY() + sliderVolume.getHeight() + 5, 50, 30);
        });

        sliderVolume.setUI(new javax.swing.plaf.basic.BasicSliderUI(sliderVolume) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 128));
                g2d.fillRoundRect(trackRect.x, trackRect.y + trackRect.height / 3, trackRect.width, trackRect.height / 3, 10, 10);
                g2d.dispose();
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                int diameter = Math.min(thumbRect.width, thumbRect.height) + 8;
                int x = thumbRect.x + (thumbRect.width - diameter) / 2;
                int y = thumbRect.y + (thumbRect.height - diameter) / 2;
                g2d.fillOval(x, y, diameter, diameter);
                g2d.dispose();
            }
        });
        painelOpcoes.add(sliderVolume);

        lblTela = new JLabel("Tela:", SwingConstants.CENTER);
        lblTela.setFont(new Font("Arial", Font.BOLD, 24));
        lblTela.setForeground(Color.WHITE);
        lblTela.setBounds((panelWidth - 500) / 2, 240, 500, 40);
        painelOpcoes.add(lblTela);

        btnTelaCheia = criarBotao("Tela Cheia");
        btnTelaCheia.setFont(new Font("Arial", Font.BOLD, 20));
        btnTelaCheia.setBounds((panelWidth - 500) / 2, 300, 220, 60);
        btnTelaCheia.addActionListener(e -> alternarTelaCheia());
        painelOpcoes.add(btnTelaCheia);

        btnModoJanela = criarBotao("Modo Janela");
        btnModoJanela.setFont(new Font("Arial", Font.BOLD, 20));
        btnModoJanela.setBounds((panelWidth - 500) / 2 + 280, 300, 220, 60);
        btnModoJanela.addActionListener(e -> sairTelaCheia());
        painelOpcoes.add(btnModoJanela);

        btnVoltar = criarBotao("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 20));
        btnVoltar.setBounds((panelWidth - 300) / 2, panelHeight - 160, 300, 80);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        painelOpcoes.add(btnVoltar);

        JButton[] botoes = {btnTelaCheia, btnModoJanela, btnVoltar};
        adicionarAnimacoesBotoes(botoes);

        painelPrincipal.add(painelOpcoes, "Opções");
    }

    private void criarPainelLogin() {
        JPanel painelLogin = new PainelComImagem("imagens/metroBlur.png");
        painelLogin.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(490, 300, 100, 30);
        painelLogin.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsuario.setBounds(600, 300, 200, 30);
        painelLogin.add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 18));
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(490, 350, 100, 30);
        painelLogin.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSenha.setBounds(600, 350, 200, 30);
        painelLogin.add(txtSenha);

        JButton btnEntrar = criarBotao("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEntrar.setBounds(600, 400, 200, 40);
        painelLogin.add(btnEntrar);

        JButton btnVoltar = criarBotao("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setBounds(600, 450, 200, 40);
        painelLogin.add(btnVoltar);

        ActionListener loginAction = e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.equals("admin") && senha.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                layout.show(painelPrincipal, "Jogo");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        };

        btnEntrar.addActionListener(loginAction);
        txtSenha.addActionListener(loginAction);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));

        adicionarAnimacoesBotoes(new JButton[]{btnEntrar, btnVoltar});

        painelPrincipal.add(painelLogin, "Login");
    }

    private void adicionarAnimacoesBotoes(JButton[] botoes) {
        Color buttonBackground = new Color(128, 128, 128, 128);
        Color buttonHoverBackground = new Color(100, 100, 100, 128);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(buttonHoverBackground);
                button.setBounds(button.getX() - 5, button.getY() - 5, button.getWidth() + 10, button.getHeight() + 10);
                button.revalidate();
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(buttonBackground);
                button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                button.revalidate();
                button.repaint();
            }
        };

        for (JButton botao : botoes) {
            botao.setBackground(buttonBackground);
            botao.setForeground(Color.WHITE);
            botao.setFocusPainted(false);
            botao.setContentAreaFilled(false);
            botao.setBorder(new RoundedBorder(20));
            botao.addMouseListener(mouseAdapter);
        }
    }

    private void alternarTelaCheia() {
        dispose();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        centralizarComponentes();
    }

    private void sairTelaCheia() {
        dispose();
        setExtendedState(JFrame.NORMAL);
        setUndecorated(false);
        setSize(1280, 853);
        setLocationRelativeTo(null);
        setVisible(true);
        centralizarComponentes();
    }

    private void centralizarComponentes() {
        int windowWidth = getWidth();
        int windowHeight = getHeight();

        int buttonWidth = 300;
        int buttonHeight = 60;
        int spacing = 20;

        int startX = (windowWidth - buttonWidth) / 2;
        int startY = (windowHeight - (4 * buttonHeight + 3 * spacing)) / 2;

        btnJogar.setBounds(startX, startY, buttonWidth, buttonHeight);
        btnOpcoes.setBounds(startX, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        btnPlacar.setBounds(startX, startY + 2 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        btnSair.setBounds(startX, startY + 3 * (buttonHeight + spacing), buttonWidth, buttonHeight);

        painelCentral.revalidate();
        painelCentral.repaint();

        lblVolume.setBounds((windowWidth - 500) / 2, 100, 500, 40);
        sliderVolume.setBounds((windowWidth - 500) / 2, 160, 500, 40);
        lblTela.setBounds((windowWidth - 500) / 2, 240, 500, 40);
        btnTelaCheia.setBounds((windowWidth - 500) / 2, 300, 220, 60);
        btnModoJanela.setBounds((windowWidth - 500) / 2 + 280, 300, 220, 60);
        btnVoltar.setBounds((windowWidth - 300) / 2, windowHeight - 160, 300, 80);

        painelOpcoes.revalidate();
        painelOpcoes.repaint();
    }

    public static void main(String[] args) {
        new Menu();
    }
}
