import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JogoReversora extends BasePainelComBotao {

    private boolean imagemAlternada = false; // Controle para alternar entre as imagens
    private BufferedImage imagemNeutro; // Imagem inicial
    private BufferedImage imagemFrente; // Imagem alternada
    private BufferedImage imagemAtual; // Imagem atualmente exibida
    private String registroUsuario; // Registro do usuário para pontuação
    private SequenciaDecisoes sequencia;
    private int pontosAcumulados = 0;
    private static final String TAREFA_REVERSORA_INICIO = "REVERSORA_INICIO";
    private static final String TAREFA_REVERSORA_FINAL = "REVERSORA_FINAL";

    public JogoReversora(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Reversora em neutro.jpg", layout, painelPrincipal);

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

        System.out.println("=== Debug JogoReversora ===");
        System.out.println("Construtor - Registro do usuário: " + registroUsuario);
        System.out.println("========================");

        try {
            // Carrega as imagens apenas uma vez
            imagemNeutro = ImageIO.read(new File("imagens/Fotos editadas/Reversora em neutro.jpg"));
            imagemFrente = ImageIO.read(new File("imagens/Fotos editadas/08 - Reversora em frente.jpg"));
            imagemAtual = imagemNeutro; // Define a imagem inicial
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Botão Voltar para Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Remove o fundo visível
        botaoVoltar.setOpaque(true); // Garante que o botão seja transparente
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

        // Adiciona um MouseListener para alternar a imagem ao clicar
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Alterna o estado da imagem
                imagemAlternada = !imagemAlternada;

                // Atualiza a imagem com base no estado
                if (imagemAlternada) {
                    imagemAtual = imagemFrente;
                    adicionarPontuacao(1); // Adiciona pontuação ao trocar para frente
                } else {
                    imagemAtual = imagemNeutro;
                }
                repaint(); // Re-renderiza o painel para exibir a nova imagem
                mostrarPontuacaoFinal(); // Mostra pontuação ao alternar
            }
        });
    }

    private void adicionarPontuacao(int pontos) {
        System.out.println("=== Debug adicionarPontuacao ===");
        System.out.println("registroUsuario: " + registroUsuario);
        System.out.println("Pontos a adicionar: " + pontos);
        System.out.println("========================");

        if (pontos > 0 && registroUsuario != null && !registroUsuario.isEmpty()) {
            // Verifica se é a reversora inicial ou final
            String tarefa = sequencia.isTarefaCompletada("CBCT_RM") ? TAREFA_REVERSORA_FINAL : TAREFA_REVERSORA_INICIO;

            // Para a reversora final, verifica se a sequência está correta
            if (tarefa.equals(TAREFA_REVERSORA_FINAL)) {
                if (!sequencia.verificarSequencia(TAREFA_REVERSORA_FINAL, "CBCT_AM_FINAL")) {
                    System.out.println("Sequência incorreta para reversora final");
                    JOptionPane.showMessageDialog(this,
                            "Sequência incorreta para reversora final!",
                            "Erro",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Verifica se a tarefa já foi completada
            if (!sequencia.isTarefaCompletada(tarefa)) {
                sequencia.registrarPontuacao(tarefa, pontos);
                pontosAcumulados += pontos;

                // Mostra mensagem de sucesso
                String mensagem = tarefa.equals(TAREFA_REVERSORA_INICIO)
                        ? "Reversora posicionada corretamente em frente!"
                        : "Reversora final posicionada corretamente!";
                JOptionPane.showMessageDialog(this,
                        mensagem,
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                // Debug para verificar se a tarefa foi registrada
                System.out.println("Tarefa " + tarefa + " registrada com sucesso!");
                System.out.println("Verificação: tarefa completada = " + sequencia.isTarefaCompletada(tarefa));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Você já completou esta tarefa!",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
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
        } else if (sequencia.isTarefaCompletada(TAREFA_REVERSORA_INICIO) ||
                sequencia.isTarefaCompletada(TAREFA_REVERSORA_FINAL)) {
            JOptionPane.showMessageDialog(this,
                    "Você já completou esta tarefa anteriormente!",
                    "Tarefa Já Realizada",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
