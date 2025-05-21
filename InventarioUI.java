import java.awt.*;
import java.util.Set;
import javax.swing.ImageIcon;

public class InventarioUI {
    private static final Image imgCinturao = new ImageIcon("imagens\\Fotos editadas\\ItensCinturao.png").getImage();
    private static final Image imgFita = new ImageIcon("imagens\\Fotos editadas\\ItensFita.png").getImage();

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
        int iconSize = 60; // Novo tamanho do ícone
        int itemY = y + (60 - iconSize) / 2; // Centraliza o ícone verticalmente na caixa

        for (String item : itens) {
            if (item.equalsIgnoreCase("Cinturão")) {
                g2d.drawImage(imgCinturao, itemX, itemY, iconSize, iconSize, null);
            } else if (item.equalsIgnoreCase("Fita")) {
                g2d.drawImage(imgFita, itemX, itemY, iconSize, iconSize, null);
            } else {
                g2d.drawString(item, itemX, y + 38); // fallback para texto
            }
            itemX += 60;
        }
    }
}
