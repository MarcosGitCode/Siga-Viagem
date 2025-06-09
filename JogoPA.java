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
    private boolean pontosAdicionadosPortaNaoFechada = false; // Add this field at the top of the class
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
            });
            add(botaoInformarIsolamento);
            revalidate(); // <- Adicione esta linha
            repaint();
        });
        add(botaoCCO);

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
        
        // Botão PA
        JButton botaoPA = new JButton("PA");
        botaoPA.setBounds(325, 175, 175, 100); // Define posição e tamanho
        botaoPA.setContentAreaFilled(false); // Remove o preenchimento
        botaoPA.setOpaque(false); // Torna o botão transparente
        botaoPA.setBorderPainted(false); // Remove a borda
        botaoPA.setFocusPainted(false); // Remove o foco visual
        botaoPA.setText(""); // Remove o texto visível
        botaoPA.addActionListener(e -> {
            if (imagemAtual == imagemAtivado) {
                // Despinta a tela ao clicar novamente
                imagemAtual = imagemNeutro; // Redefine para a imagem neutra
            } else {
                System.out.println("Botão PA clicado!");
                imagemAtual = imagemAtivado; // Altera a imagem atual para ativado
            }
            repaint(); // Atualiza a tela
        });
        add(botaoPA);
    }

    private void removerBotaoCCO() {
        if (botaoCCO != null) {
            remove(botaoCCO); // Remove the button from the panel
            botaoCCO = null; // Clear the reference
            repaint(); // Redraw the panel
        }
    }

    private void removerBotaoInformarPortaNaoFechada() {
        if (botaoInformarPortaNaoFechada != null) {
            remove(botaoInformarPortaNaoFechada); // Remove the button from the panel
            botaoInformarPortaNaoFechada = null; // Clear the reference
            repaint(); // Redraw the panel
        }
    }

    private void removerBotaoInformarVerificacoes() {
        if (botaoInformarVerificacoes != null) {
            remove(botaoInformarVerificacoes); // Remove the button from the panel
            botaoInformarVerificacoes = null; // Clear the reference
            repaint(); // Redraw the panel
        }
    }

    private void removerBotaoInformarIsolamento() {
        if (botaoInformarIsolamento != null) {
            remove(botaoInformarIsolamento); // Remove the button from the panel
            botaoInformarIsolamento = null; // Clear the reference
            repaint(); // Redraw the panel
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
        if (!mensagemTemporaria.isEmpty() && System.currentTimeMillis() < mensagemFim) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            int msgWidth = fm.stringWidth(mensagemTemporaria);
            int x = (getWidth() - msgWidth) / 2;
            g.drawString(mensagemTemporaria, x, 100);
        }

        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
