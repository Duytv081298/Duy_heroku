package until;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundLoader {
    public static Clip play(String name){
        try{
            File file = new File("src/sounds/" + name);
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            return clip;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
