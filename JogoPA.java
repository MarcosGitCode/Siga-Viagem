import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class JogoPA extends BasePainelComBotao {
    private boolean imagemAlternada = false;
    private BufferedImage imagemNeutro;
    private BufferedImage imagemAtivado;
    private BufferedImage imagemAtual;
    private String registroUsuario;
    private int pontosAcumulados = 0;
    private SequenciaDecisoes sequencia;
    private static final String TAREFA_PA_INICIAL = "PA_INICIAL";
    private static final String TAREFA_PA_MEIO = "PA_MEIO";
    private static final String TAREFA_PA_FINAL = "PA_FINAL";
    private JButton botaoCCO;
    private JButton botaoInformarPortaNaoFechada;
    private JButton botaoInformarVerificacoes;
    private JButton botaoInformarIsolamento;
    private boolean pontosAdicionadosPortaNaoFechada = false;
    private boolean pontosAdicionadosInformarVerificacoes = false;
    private boolean pontosAdicionadosInformarIsolamento = false;
    private boolean pontosAdicionadosPA = false;
    private boolean pontosAdicionadosPAaposCCO = false;
    private boolean pontosAdicionadosPAaposIsolamento = false;
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;

    public JogoPA(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg", layout, painelPrincipal);

        registroUsuario = UsuarioLogado.getRegistro();
        sequencia = SequenciaDecisoes.getInstance();
        sequencia.setRegistroUsuario(registroUsuario);

        try {
            imagemNeutro = ImageIO
                    .read(new File("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg"));
            imagemAtivado = ImageIO.read(
                    new File("imagens/Fotos editadas/05B Módulo de Comunicação - Microfone aberto PA ao vivo.jpg"));
            imagemAtual = imagemNeutro;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar imagens do PA. Verifique se os arquivos existem no diretório correto.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        botaoCCO = new JButton("");
        botaoCCO.setBounds(200, 350, 300, 180);
        botaoCCO.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoCCO.setForeground(Color.BLACK);
        botaoCCO.setBackground(Color.RED);
        botaoCCO.setContentAreaFilled(false);
        botaoCCO.setOpaque(false);
        botaoCCO.setBorderPainted(false);
        botaoCCO.addActionListener(e -> {
            System.out.println("Botão CCO clicado!");
            botaoInformarPortaNaoFechada = new JButton("Informar porta não fechada");
            botaoInformarPortaNaoFechada.setBounds(200, 300, 250, 60);
            botaoInformarPortaNaoFechada.setFont(new Font("Arial", Font.BOLD, 15));
            botaoInformarPortaNaoFechada.setForeground(Color.WHITE);
            botaoInformarPortaNaoFechada.setBackground(Color.RED);
            botaoInformarPortaNaoFechada.setContentAreaFilled(true);
            botaoInformarPortaNaoFechada.setOpaque(true);
            botaoInformarPortaNaoFechada.setBorderPainted(false);
            botaoInformarPortaNaoFechada.addActionListener(ev -> {
                System.out.println("Botão InformarPortaNaoFechada clicado!");
                if (!pontosAdicionadosPortaNaoFechada) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                    pontosAdicionadosPortaNaoFechada = true;
                    EstadoJogo.CCOPortaFechada = true;
                    mensagemTemporaria = "Você ganhou 2 pontos!";
                    mensagemFim = System.currentTimeMillis() + 3000; // 3 segundos

                    System.out.println("2 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }
            });
            add(botaoInformarPortaNaoFechada);

            botaoInformarVerificacoes = new JButton("Informar verificações");
            botaoInformarVerificacoes.setBounds(500, 300, 250, 60);
            botaoInformarVerificacoes.setFont(new Font("Arial", Font.BOLD, 15));
            botaoInformarVerificacoes.setForeground(Color.WHITE);
            botaoInformarVerificacoes.setBackground(Color.red);
            botaoInformarVerificacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            botaoInformarVerificacoes.setContentAreaFilled(true);
            botaoInformarVerificacoes.setOpaque(true);
            botaoInformarVerificacoes.setBorderPainted(false);
            botaoInformarVerificacoes.addActionListener(ev -> {
                System.out.println("Botão InformarVerificacoes clicado!");
                if (EstadoJogo.verificarEmergencia == true && !pontosAdicionadosInformarVerificacoes) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                    pontosAdicionadosInformarVerificacoes = true;

                    mensagemTemporaria = "Você ganhou 2 pontos!";
                    mensagemFim = System.currentTimeMillis() + 3000; 

                    System.out.println("2 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }
            });
            add(botaoInformarVerificacoes);

            botaoInformarIsolamento = new JButton("Informar isolamento");
            botaoInformarIsolamento.setBounds(800, 300, 250, 60);
            botaoInformarIsolamento.setFont(new Font("Arial", Font.BOLD, 15));
            botaoInformarIsolamento.setForeground(Color.WHITE);
            botaoInformarIsolamento.setBackground(Color.RED);
            botaoInformarIsolamento.setContentAreaFilled(true);
            botaoInformarIsolamento.setOpaque(true);
            botaoInformarIsolamento.setBorderPainted(false);
            botaoInformarIsolamento.addActionListener(ev -> {
                System.out.println("Botão InformarIsolamento clicado!");
                if (EstadoJogo.portaAdesivo == true && EstadoJogo.luzesApagadas == true && !pontosAdicionadosInformarIsolamento) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                    pontosAdicionadosInformarIsolamento = true;
                    EstadoJogo.informarIsolamento = true;
                    mensagemTemporaria = "Você ganhou 2 pontos!";
                    mensagemFim = System.currentTimeMillis() + 3000; 

                    System.out.println("2 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }
            });
            add(botaoInformarIsolamento);
            revalidate(); 
            repaint();
        });
        add(botaoCCO);

        
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            removerBotaoInformarPortaNaoFechada();
            removerBotaoInformarVerificacoes();
            removerBotaoInformarIsolamento();
            mostrarPontuacaoFinal();
            layout.show(painelPrincipal, "Jogo");
        });
        add(botaoVoltar);

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
        
      
        JButton botaoPA = new JButton("PA");
        botaoPA.setBounds(325, 175, 175, 100); 
        botaoPA.setContentAreaFilled(false); 
        botaoPA.setOpaque(false); 
        botaoPA.setBorderPainted(false); 
        botaoPA.setFocusPainted(false); 
        botaoPA.setText(""); 
        botaoPA.addActionListener(e -> {
            if (imagemAtual == imagemAtivado) {
                imagemAtual = imagemNeutro;
            } else {
                System.out.println("Botão PA clicado!");
                imagemAtual = imagemAtivado; 
            }
            if (EstadoJogo.chaveCBTCRM == false || EstadoJogo.reversoraFrente == false) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), -2);
                EstadoJogo.pontosPerdidos += 2;
                mensagemTemporaria = "Você perdeu 2 pontos!";
                repaint();
            }
            if (EstadoJogo.chaveCBTCRM == true && !pontosAdicionadosPA) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                    pontosAdicionadosPA = true;

                    mensagemTemporaria = "Você ganhou 1 ponto!";
                    mensagemFim = System.currentTimeMillis() + 3000; 

                    System.out.println("1 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }
            repaint(); 

            if (EstadoJogo.CCOPortaFechada == true && !pontosAdicionadosPAaposCCO) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                    pontosAdicionadosPAaposCCO = true;

                    mensagemTemporaria = "Você ganhou 1 ponto!";
                    mensagemFim = System.currentTimeMillis() + 3000; 

                    System.out.println("1 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }

            if (EstadoJogo.informarIsolamento == true && !pontosAdicionadosPAaposIsolamento) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                    pontosAdicionadosPAaposIsolamento = true;

                    mensagemTemporaria = "Você ganhou 1 ponto!";
                    mensagemFim = System.currentTimeMillis() + 3000; 

                    System.out.println("1 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }

            repaint();
        });
        add(botaoPA);
    }

    private void removerBotaoInformarPortaNaoFechada() {
        if (botaoInformarPortaNaoFechada != null) {
            remove(botaoInformarPortaNaoFechada); 
            botaoInformarPortaNaoFechada = null; 
            repaint(); 
        }
    }

    private void removerBotaoInformarVerificacoes() {
        if (botaoInformarVerificacoes != null) {
            remove(botaoInformarVerificacoes); 
            botaoInformarVerificacoes = null; 
            repaint(); 
        }
    }

    private void removerBotaoInformarIsolamento() {
        if (botaoInformarIsolamento != null) {
            remove(botaoInformarIsolamento); 
            botaoInformarIsolamento = null; 
            repaint(); 
        }
    }

    private void verificarEAdicionarPontuacao() {
        // Determina qual PA está sendo acionado baseado na sequência
        if (!sequencia.isTarefaCompletada(TAREFA_PA_INICIAL) &&
                sequencia.verificarSequencia(TAREFA_PA_INICIAL, "REVERSORA_INICIO", "CBCT_RM")) {
            // PA inicial
            adicionarPontuacao(1, TAREFA_PA_INICIAL);
        } else if (!sequencia.isTarefaCompletada(TAREFA_PA_MEIO) &&
                sequencia.verificarSequencia(TAREFA_PA_MEIO, "INFORMAR_CCO_INICIAL")) {
            // PA do meio
            adicionarPontuacao(1, TAREFA_PA_MEIO);
        } else if (!sequencia.isTarefaCompletada(TAREFA_PA_FINAL) &&
                sequencia.verificarSequencia(TAREFA_PA_FINAL, "INFORMAR_CCO_FINAL", "INSERIR_CHAVE")) {
            // PA final
            adicionarPontuacao(1, TAREFA_PA_FINAL);
        }
    }

    private void adicionarPontuacao(int pontos, String tarefa) {
        if (registroUsuario != null && !registroUsuario.isEmpty()) {
            sequencia.registrarPontuacao(tarefa, pontos);
            pontosAcumulados += pontos;
        }
    }

    private void mostrarPontuacaoFinal() {
        if (pontosAcumulados > 0) {
            JOptionPane.showMessageDialog(this,
                    "Parabéns! Você ganhou " + pontosAcumulados + " ponto(s)!",
                    "Pontos Ganhos",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw temporary message if it exists
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            // Desenha a borda preta (várias vezes ao redor)
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            // Desenha o texto branco por cima
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }

        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
