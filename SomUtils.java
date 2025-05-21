import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SomUtils {
    public static void tocarSom(String caminhoSom) {
        try {
            URL somURL = SomUtils.class.getResource(caminhoSom);
            if (somURL == null) {
                System.err.println("Som não encontrado: " + caminhoSom);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(somURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}