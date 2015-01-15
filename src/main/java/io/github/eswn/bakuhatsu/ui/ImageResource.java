package io.github.eswn.bakuhatsu.ui;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public final class ImageResource{

    private static final Logger LOG = Logger.getLogger(ImageResource.class.getName());
    private static final Map<String, Image> imageMap = new HashMap<String, Image>();
    private static final ResourceBundle imageConfig = ResourceBundle.getBundle("io.github.eswn.bakuhatsu.ui.res.ImageConfig");

    private ImageResource(){
    }

    public static Image getImage(String key){
        if (!imageMap.containsKey(key)){
            try{
                String filename = imageConfig.getString(key);
                imageMap.put(key, ImageIO.read(ImageResource.class.getResourceAsStream("res/" + filename)));
            }catch (Exception ex){
                LOG.log(Level.SEVERE, "", ex);
                imageMap.put(key, null);
            }
        }
        return imageMap.get(key);
    }
}
