package Objects;

import Map.Tile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        name = "Chest";

            String filePath = "res/Objects/chest.png";
            File imageFile = new File(filePath);

            try (FileInputStream readimage = new FileInputStream(imageFile)) {
                image = ImageIO.read(readimage);
            } catch(IOException e) {
                e.printStackTrace();
            }

        // SET COLLISION
            collision = true;

    }
}
