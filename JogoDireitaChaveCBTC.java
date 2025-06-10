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

        // Verifica se há um usuário logado
        if (!UsuarioLogado.isLogado()) {
            JOptionPane.showMessageDialog(this,
                    "Erro: Usuário não está logado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            layout.show(painelPrincipal, "Menu");
            return;
        }

        registroUsuario = UsuarioLogado.getRegistro();
        sequencia = SequenciaDecisoes.getInstance();
        sequencia.setRegistroUsuario(registroUsuario);

        System.out.println("=== Debug JogoDireitaChaveCBTC ===");
        System.out.println("Construtor - Registro do usuário: " + registroUsuario);
        System.out.println("========================");

        // Botão Voltar para Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Fundo visível
        botaoVoltar.setOpaque(true); // Garante que o fundo seja opaco
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            mensagemTemporaria = "";
            mostrarPontuacaoFinal(); // Mostra o resultado final
            layout.show(painelPrincipal, "JogoDireita"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

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
        
        // Botão chaveCBTCRM
        JButton chaveCBTCRM = new JButton();
        chaveCBTCRM.setBounds(700, 385, 75, 75); // Posição e tamanho do botão
        chaveCBTCRM.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTCRM.setOpaque(false); // Torna o botão completamente transparente
        chaveCBTCRM.setBorderPainted(false); // Remove as bordas do botão
        chaveCBTCRM.setFocusPainted(false); // Remove o foco visual
        chaveCBTCRM.addActionListener(e -> {
            System.out.println("Botão chaveCBTCRM clicado!");
            mostrarImagemChaveCBTCRM();
            if (EstadoJogo.reversoraFrente == true && !pontosAdicionadosChaveRM) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosChaveRM = true;

                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
                repaint();
            }
        });
        add(chaveCBTCRM); // Adiciona o botão ao painel

        // Botão chaveCBTCAM
        JButton chaveCBTCAM = new JButton();
        chaveCBTCAM.setBounds(495, 385, 75, 75); // Posição e tamanho do botão
        chaveCBTCAM.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTCAM.setOpaque(false); // Torna o botão completamente transparente
        chaveCBTCAM.setBorderPainted(false); // Remove as bordas do botão
        chaveCBTCAM.setFocusPainted(false); // Remove o foco visual
        chaveCBTCAM.addActionListener(e -> {
            System.out.println("Botão chaveCBTCAM clicado!");
            mostrarImagemChaveCBTCAM();
            // Verifica se é a chave AM final
            if(EstadoJogo.portaFechada == true && EstadoJogo.chaveInserida == true) {
            EstadoJogo.chaveInserida = true; // Marca que a chave foi inserida
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
        chaveCBTCMCS.setBounds(600, 370, 75, 75); // Posição e tamanho do botão
        chaveCBTCMCS.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTCMCS.setOpaque(false); // Torna o botão completamente transparente
        chaveCBTCMCS.setBorderPainted(false); // Remove as bordas do botão
        chaveCBTCMCS.setFocusPainted(false); // Remove o foco visual
        chaveCBTCMCS.addActionListener(e -> {
            System.out.println("Botão chaveCBTCMCS clicado!");
            mostrarImagemPrincipal(); // Retorna para a imagem principal
            adicionarPontuacao(-1); // Remove 1 ponto
        });
        add(chaveCBTCMCS); // Adiciona o botão ao painel

        // Configura a imagem sobreposta para chaveCBTCRM
        imagemChaveCBTCRM = new JLabel();
        imagemChaveCBTCRM.setVisible(false); // Inicialmente invisível
        add(imagemChaveCBTCRM); // Adiciona a imagem ao painel

        // Configura a imagem sobreposta para chaveCBTCAM
        imagemChaveCBTCAM = new JLabel();
        imagemChaveCBTCAM.setVisible(false); // Inicialmente invisível
        add(imagemChaveCBTCAM); // Adiciona a imagem ao painel

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
            // Determina qual tarefa está sendo realizada
            String tarefa = sequencia.isTarefaCompletada("INSERIR_CHAVE") ? TAREFA_CBCT_AM_FINAL : TAREFA_CBCT_RM;

            // Registra a pontuação apenas se for positiva ou se for a primeira tentativa
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

        // Desenha a imagem atual, se houver
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }

        // Exibe a mensagem temporária se estiver ativa
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}