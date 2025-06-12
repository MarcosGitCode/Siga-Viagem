import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class JogoDDU extends BasePainelComBotao {

    private JLabel imagemLabel; // label p/ mostrar img
    private String caminhoImagemAtual = "imagens/Fotos editadas/Uma porta não fecha.jpg"; // caminho da img atual

    public JogoDDU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Uma porta não fecha.jpg", layout, painelPrincipal);
        setLayout(null); // usa layout absoluto p/ facilitar pos dos btns
        
        // btn p/ voltar pra tela Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // pos e tam do btn
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 20)); // fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // cor do btn
        botaoVoltar.setContentAreaFilled(true); // deixa fundo visível
        botaoVoltar.setOpaque(true); // garante opacidade
        botaoVoltar.setBorderPainted(true);
        botaoVoltar.setFocusPainted(false); // tira borda
        botaoVoltar.addActionListener(e -> {
            System.out.println("botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo"); // volta pra tela anterior
        });
        add(botaoVoltar); // add btn no painel
        
        // btn p/ ir pro menu principal
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
            System.out.println("botão menu clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);

        // label p/ exibir a img
        imagemLabel = new JLabel();
        imagemLabel.setBounds(0, 0, getWidth(), getHeight());
        add(imagemLabel);
        
        // inicializa a img
        atualizarImagemAtual();
        
        // cria btns com textos novos
        JButton btnPrepara = criarBotao("Prepara");
        JButton btnFe = criarBotao("Fe");
        JButton btnInfopass = criarBotao("Infopass");
        JButton btnManut = criarBotao("Infopass");

        // define pos dos btns
        btnPrepara.setBounds(342,597, 50, 50);
        btnFe.setBounds(510,603, 50, 50);
        btnInfopass.setBounds(562,612, 50, 50); // ajustado p/ não sobrepor
        btnManut.setBounds(740,603, 70, 70);
        add(btnPrepara);
        add(btnFe);
        add(btnInfopass);
        add(btnManut);

        // ações dos btns mudam a img
        btnPrepara.addActionListener(e -> alterarImagem("imagens/Fotos editadas/Uma porta não fecha.jpg"));
        btnFe.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela FE- lista de emergência.jpg"));
        btnInfopass.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela INFOPASS.jpg"));
        btnManut.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela Manut.png"));

        // tira fundo e borda dos btns
        btnPrepara.setContentAreaFilled(false);
        btnPrepara.setOpaque(false);
        btnPrepara.setBorderPainted(false);

        btnFe.setContentAreaFilled(false);
        btnFe.setOpaque(false);
        btnFe.setBorderPainted(false);

        btnInfopass.setContentAreaFilled(false);
        btnInfopass.setOpaque(false);
        btnInfopass.setBorderPainted(false);

        btnManut.setContentAreaFilled(false);
        btnManut.setOpaque(false);
        btnManut.setBorderPainted(false);

        // listener p/ redimensionar: atualiza limites e re-renderiza img
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // ajusta o tamanho do label da img p/ ocupar todo painel
                imagemLabel.setBounds(0, 0, getWidth(), getHeight());
                // recarrega e redimensiona a img conforme novo tamanho do painel
                atualizarImagemAtual();
            }
        });
    }

    // cria btn estilizado
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(70, 130, 180)); // azul claro
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return botao;
    }

    // muda a img usando o caminho informado
    private void alterarImagem(String caminhoImagem) {
        System.out.println("tentando carregar: " + caminhoImagem);
        
        // verifica se o arquivo existe
        File arquivo = new File(caminhoImagem);
        if (!arquivo.exists()) {
            System.out.println("arquivo não encontrado: " + caminhoImagem);
            JOptionPane.showMessageDialog(this, 
                "imagem não encontrada: " + caminhoImagem, 
                "erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.caminhoImagemAtual = caminhoImagem;
        atualizarImagemAtual();
    }

    // atualiza a img no label, redimensionando conforme o painel
    private void atualizarImagemAtual() {
        if (getWidth() <= 0 || getHeight() <= 0) {
            // evita redimensionar qdo painel ainda não tem tamanho
            return;
        }
        
        ImageIcon imagem = new ImageIcon(caminhoImagemAtual);

        if (imagem.getIconWidth() <= 0) {
            System.out.println("erro: img não carregada: " + caminhoImagemAtual);
            imagemLabel.setIcon(null);
            imagemLabel.setText("imagem não encontrada");
            imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
            return;
        }

        // redimensiona a img p/ ocupar toda área do painel
        Image imagemRedimensionada = imagem.getImage().getScaledInstance(
            getWidth(), getHeight(), Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(imagemRedimensionada));
        imagemLabel.setText(null); // tira texto, se tiver
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            // garante q a img seja atualizada qdo painel ficar visível
            atualizarImagemAtual();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpa antes de desenhar, evita sobreposição
        // desenha inventário no canto sup dir, usa largura p/ alinhar certinho
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }

}
