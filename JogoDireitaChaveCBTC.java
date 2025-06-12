import java.awt.*;
import javax.swing.*;

public class JogoDireitaChaveCBTC extends BasePainelComBotao {

    private JLabel imagemChaveCBTCRM;
    private JLabel imagemChaveCBTCAM;
    private String registroUsuario;
    private int pontosAcumulados = 0;
    private SequenciaDecisoes sequencia;
    private static final String TAREFA_CBCT_RM = "CBCT_RM";
    private static final String TAREFA_CBCT_AM_FINAL = "CBCT_AM_FINAL";
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;
    private boolean pontosAdicionadosChaveCBTCAM = false;
    private boolean pontosAdicionadosChaveRM = false;
    private Image imagemAtual = null;

    public JogoDireitaChaveCBTC(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/09 - Chave do CBTC - MCS.jpg", layout, painelPrincipal);

        // verifica usuário logado
       
        registroUsuario = UsuarioLogado.getRegistro();
        sequencia = SequenciaDecisoes.getInstance();
        sequencia.setRegistroUsuario(registroUsuario);

        System.out.println("=== Debug JogoDireitaChaveCBTC ===");
        System.out.println("Construtor - Registro do usuário: " + registroUsuario);
        System.out.println("========================");

        // botao voltar  Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // fundo visível
        botaoVoltar.setOpaque(true); // garante que o fundo opaco
        botaoVoltar.setBorderPainted(false); // remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            mensagemTemporaria = "";
            mostrarPontuacaoFinal(); // mostra o resultado final
            layout.show(painelPrincipal, "JogoDireita"); // volta para o painel anterior
        });
        add(botaoVoltar); // adiciona o botão 

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
        
        // botao chaveCBTCRM
        JButton chaveCBTCRM = new JButton();
        chaveCBTCRM.setBounds(700, 385, 75, 75); 
        chaveCBTCRM.setContentAreaFilled(false); 
        chaveCBTCRM.setOpaque(false); 
        chaveCBTCRM.setBorderPainted(false); 
        chaveCBTCRM.setFocusPainted(false); 
        chaveCBTCRM.addActionListener(e -> {
            System.out.println("Botão chaveCBTCRM clicado!");
            mostrarImagemChaveCBTCRM();
            if (EstadoJogo.reversoraFrente == true && !pontosAdicionadosChaveRM) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosChaveRM = true;
                EstadoJogo.chaveCBTCRM = true; // marca que a chave foi inserida

                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
                repaint();
            }
        });
        add(chaveCBTCRM); // 

        // botao chaveCBTCAM
        JButton chaveCBTCAM = new JButton();
        chaveCBTCAM.setBounds(495, 385, 75, 75); 
        chaveCBTCAM.setContentAreaFilled(false); 
        chaveCBTCAM.setOpaque(false); 
        chaveCBTCAM.setBorderPainted(false); 
        chaveCBTCAM.setFocusPainted(false); 
        chaveCBTCAM.addActionListener(e -> {
            System.out.println("Botão chaveCBTCAM clicado!");
            mostrarImagemChaveCBTCAM();
            // verifica se é a chave AM final
            if(EstadoJogo.portaAdesivo == true && EstadoJogo.chaveInserida == true) {
            EstadoJogo.chaveInserida = true; // marca que a chave foi inserida
            if (EstadoJogo.chaveInserida && !pontosAdicionadosChaveCBTCAM) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosChaveCBTCAM = true;
                EstadoJogo.chaveCBTCAM = true; 

                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
                repaint();
            }
        }
        });
        add(chaveCBTCAM); // Adiciona o botão ao painel

        // Botão chaveCBTCMCS
        JButton chaveCBTCMCS = new JButton();
        chaveCBTCMCS.setBounds(600, 370, 75, 75); 
        chaveCBTCMCS.setContentAreaFilled(false); 
        chaveCBTCMCS.setOpaque(false); 
        chaveCBTCMCS.setBorderPainted(false); 
        chaveCBTCMCS.setFocusPainted(false); 
        chaveCBTCMCS.addActionListener(e -> {
            System.out.println("Botão chaveCBTCMCS clicado!");
            mostrarImagemPrincipal(); // Retorna para a imagem principal
            adicionarPontuacao(-1); 
        });
        add(chaveCBTCMCS); 

        // configura a imagem sobreposta para chaveCBTCRM
        imagemChaveCBTCRM = new JLabel();
        imagemChaveCBTCRM.setVisible(false); //  invisível
        add(imagemChaveCBTCRM); // adiciona a imagem 

        // configura a imagem sobreposta para chaveCBTCAM
        imagemChaveCBTCAM = new JLabel();
        imagemChaveCBTCAM.setVisible(false); //  invisível
        add(imagemChaveCBTCAM); // adiciona a imagem 

        setLayout(null); // Define layout absoluto para posicionamento manual
    }

    private void mostrarImagemChaveCBTCRM() {
        imagemAtual = new ImageIcon("imagens/Fotos editadas/Chave do CBTC - RM.jpg").getImage();
        repaint();
    }

    private void mostrarImagemChaveCBTCAM() {
        imagemAtual = new ImageIcon("imagens/Fotos editadas/Chave do CBTC - AM.jpg").getImage();
        repaint();
    }

    private void mostrarImagemPrincipal() {
        imagemAtual = new ImageIcon("imagens/Fotos editadas/09 - Chave do CBTC - MCS.jpg").getImage();
        repaint();
    }

    private void adicionarPontuacao(int pontos) {
        System.out.println("=== Debug adicionarPontuacao ===");
        System.out.println("registroUsuario: " + registroUsuario);
        System.out.println("Pontos a adicionar: " + pontos);
        System.out.println("========================");

        if (registroUsuario != null && !registroUsuario.isEmpty()) {
            // determina qual tarefa está sendo realizada
            String tarefa = sequencia.isTarefaCompletada("INSERIR_CHAVE") ? TAREFA_CBCT_AM_FINAL : TAREFA_CBCT_RM;

            // registra a pontuação apenas se for positiva ou se for a primeira tentativa
            if (pontos > 0 || !sequencia.isTarefaCompletada(tarefa)) {
                sequencia.registrarPontuacao(tarefa, pontos);
                pontosAcumulados += pontos;
            }
        }
    }

    private void mostrarPontuacaoFinal() {
        if (pontosAcumulados != 0) {
            String mensagem;
            String titulo;
            int tipoMensagem;

            if (pontosAcumulados > 0) {
                mensagem = "Parabéns! Você ganhou um total de " + pontosAcumulados + " ponto(s)!";
                titulo = "Pontos Ganhos";
                tipoMensagem = JOptionPane.INFORMATION_MESSAGE;
            } else {
                mensagem = "Atenção! Você perdeu um total de " + Math.abs(pontosAcumulados) + " ponto(s)!";
                titulo = "Pontos Perdidos";
                tipoMensagem = JOptionPane.WARNING_MESSAGE;
            }

            JOptionPane.showMessageDialog(this, mensagem, titulo, tipoMensagem);
        } else if (sequencia.isTarefaCompletada(TAREFA_CBCT_RM) ||
                sequencia.isTarefaCompletada(TAREFA_CBCT_AM_FINAL)) {
            JOptionPane.showMessageDialog(this,
                    "Você já completou esta tarefa anteriormente!",
                    "Tarefa Já Realizada",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // desenha a imagem atual, se houver
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }

        // exibe a mensagem temporária se estiver ativa
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            // desenha a borda preta 
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            // desenha o texto branco 
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}