import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

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
            mostrarPontuacaoFinal();
            layout.show(painelPrincipal, "Jogo");
        });
        add(botaoVoltar);

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
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
