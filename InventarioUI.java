import java.awt.*;
import java.util.Set;
import javax.swing.ImageIcon;

public class InventarioUI {
    private static final Image imgCinturao = new ImageIcon("imagens\\Fotos editadas\\ItensCinturao2.png").getImage();
    private static final Image imgFita = new ImageIcon("imagens\\Fotos editadas\\ItensFita2.png").getImage();

    // Variáveis para rastrear se o jogador pegou os itens
    private static boolean pegouCinturao = false;
    private static boolean pegouFita = false;

    public static void desenhar(Graphics2D g2d, int larguraPainel) {
        Set<String> itens = Inventario.getItens();
        int larguraBox = 180 + (itens.size() * 60);
        int x = larguraPainel - larguraBox - 20;
        int y = 10;

        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRoundRect(x, y, larguraBox, 60, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Inventário:", x + 15, y + 38);

        int itemX = x + 120;
        int iconSize = 60; // Tamanho padrão do ícone
        int itemY = y + (60 - iconSize) / 2; // Centraliza o ícone verticalmente na caixa

        for (String item : itens) {
            if (item.equalsIgnoreCase("Cinturão")) {
                pegouCinturao = true; // Marca que o cinturão foi pego
                int larguraCinturao = 50; // Aumenta a largura do ícone do cinturão
                g2d.drawImage(imgCinturao, itemX, itemY, larguraCinturao, iconSize, null);
                itemX += larguraCinturao; // Ajusta o espaçamento para o próximo item
            } else if (item.equalsIgnoreCase("Fita")) {
                pegouFita = true; // Marca que a fita foi pega
                int larguraFita = 120; // Aumenta a largura do ícone da fita
                g2d.drawImage(imgFita, itemX, itemY, larguraFita, iconSize, null);
                itemX += larguraFita; // Ajusta o espaçamento para o próximo item
            } else {
                g2d.drawString(item, itemX, y + 38); // fallback para texto
                itemX += 60;
            }
        }
    }

    // Métodos para verificar se os itens foram pegos
    public static boolean isPegouCinturao() {
        return pegouCinturao;
    }

    public static boolean isPegouFita() {
        return pegouFita;
    }
}
