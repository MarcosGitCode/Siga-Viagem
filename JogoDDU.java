import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class JogoDDU extends BasePainelComBotao {

    private JLabel imagemLabel;
    private String caminhoImagemAtual = "imagens/Fotos editadas/Uma porta não fecha.jpg";

    public JogoDDU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Uma porta não fecha.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto
        
        // Botão Voltar para Parte1
      JButton botaoVoltar = new JButton("<");
       botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
       botaoVoltar.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do texto
       botaoVoltar.setForeground(Color.BLACK);
       botaoVoltar.setBackground(Color.RED); // Define a cor do texto
       botaoVoltar.setContentAreaFilled(true); // Remove o fundo visível
       botaoVoltar.setOpaque(true); // Garante que o botão seja transparente
       botaoVoltar.setBorderPainted(true);
       botaoVoltar.setFocusPainted(false); // Remove as bordas do botão
       botaoVoltar.addActionListener(e -> {
           System.out.println("Botão voltar clicado!");
           layout.show(painelPrincipal, "Jogo"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel
        

        // Criação do JLabel para exibir a imagem
        imagemLabel = new JLabel();
        imagemLabel.setBounds(0, 0, getWidth(), getHeight());
        add(imagemLabel);
        
        // Inicializar a imagem
        atualizarImagemAtual();
        
        // Criação dos botões com novos textos
        JButton btnPrepara = criarBotao("Prepara");
        JButton btnFe = criarBotao("Fe");
        JButton btnInfopass = criarBotao("Infopass");
        JButton btnManut = criarBotao("Infopass");

        // Configuração das posições dos botões
        btnPrepara.setBounds(342,597, 50, 50);
        btnFe.setBounds(510,603, 50, 50);
        btnInfopass.setBounds(562,612, 50, 50); // Ajustado para evitar sobreposição
        btnManut.setBounds(740,603, 70, 70);
        add(btnPrepara);
        add(btnFe);
        add(btnInfopass);
        add(btnManut);

        // Ações dos botões com caminhos de imagem
        btnPrepara.addActionListener(e -> alterarImagem("imagens/Fotos editadas/Uma porta não fecha.jpg"));
        btnFe.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela FE- lista de emergência.jpg"));
        btnInfopass.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela INFOPASS.jpg"));
        btnManut.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela Manut.png"));
        // Remover fundo e bordas dos botões
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

        // Listener para redimensionamento: atualiza os limites e re-renderiza a imagem
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                imagemLabel.setBounds(0, 0, getWidth(), getHeight());
                atualizarImagemAtual();
            }
        });
    }

    // Criação padrão de botões estilizados
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(70, 130, 180)); // Azul claro
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return botao;
    }

    // Método para alterar a imagem utilizando o caminho informado
    private void alterarImagem(String caminhoImagem) {
        System.out.println("Tentando carregar: " + caminhoImagem);
        
        // Verifica se o arquivo existe
        File arquivo = new File(caminhoImagem);
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoImagem);
            JOptionPane.showMessageDialog(this, 
                "Imagem não encontrada: " + caminhoImagem, 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.caminhoImagemAtual = caminhoImagem;
        atualizarImagemAtual();
    }

    // Atualiza a imagem no JLabel, redimensionando-a conforme as dimensões atuais do painel
    private void atualizarImagemAtual() {
        if (getWidth() <= 0 || getHeight() <= 0) {
            // Evita redimensionamento quando o painel ainda não tem tamanho definido
            return;
        }
        
        ImageIcon imagem = new ImageIcon(caminhoImagemAtual);

        if (imagem.getIconWidth() <= 0) {
            System.out.println("Erro: imagem não carregada corretamente: " + caminhoImagemAtual);
            imagemLabel.setIcon(null);
            imagemLabel.setText("Imagem não encontrada");
            imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
            return;
        }

        // Redimensiona a imagem para ocupar toda a área do painel
        Image imagemRedimensionada = imagem.getImage().getScaledInstance(
            getWidth(), getHeight(), Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(imagemRedimensionada));
        imagemLabel.setText(null); // Remove o texto, se houver
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            // Garante que a imagem seja atualizada quando o painel ficar visível
            atualizarImagemAtual();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }

}
