package com.wherepeople.spring.mvc.util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by yuriy on 02.04.15.
 */
public class ImageUtil {
    public static BufferedImage makeRounded(Image image, Rounder rounder) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(rounder.getShape(width, height));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    static public interface Rounder{
        Shape getShape(int width, int height);
    }

    static public class CornerRounder implements Rounder{
        final int cornerRadius;

        public CornerRounder(int cornerRadius) {
            this.cornerRadius = cornerRadius;
        }

        @Override
        public Shape getShape(int width, int height) {
            return new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);
        }
    }

    static public class EllipseRounder implements Rounder{

        @Override
        public Shape getShape(int width, int height) {
            return new Ellipse2D.Float(0, 0, width, height);
        }
    }
}
