import java.awt.*;
import java.util.Set;
import javax.swing.ImageIcon;

public class InventarioUI {
    private static final Image imgCinturao = new ImageIcon("imagens\\Fotos editadas\\ItensCinturao2.png").getImage();
    private static final Image imgFita = new ImageIcon("imagens\\Fotos editadas\\ItensFita2.png").getImage();
    private static final Image imgChave = new ImageIcon("imagens/Fotos editadas/ItensChave.png").getImage();

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
        int iconSize = 60; // tamanho do icon
        int itemY = y + (60 - iconSize) / 2; // centraliza na tela

        for (String item : itens) {
            if (item.equalsIgnoreCase("Cinturão")) {
                g2d.drawImage(imgCinturao, itemX, itemY, iconSize, iconSize, null);
            } else if (item.equalsIgnoreCase("Fita")) {
                g2d.drawImage(imgFita, itemX, itemY, iconSize, iconSize, null);
            } else if (item.equalsIgnoreCase("Chave")) {
                g2d.drawImage(imgChave, itemX, itemY, iconSize, iconSize, null);
            } else {
                g2d.drawString(item, itemX, y + 38);
            }
            itemX += 60;
        }
    }

    // metodos pra pegar itens
    public static boolean isPegouCinturao() {
        return pegouCinturao;
    }

    public static boolean isPegouFita() {
        return pegouFita;
    }
}
