/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j.objectdetection;

import com.uniquetrij.tf4j.objectdetection.Models;
import com.uniquetrij.tf4j.objectdetection.SimpleObjectDetector;
import com.uniquetrij.tf4j.utils.ColorUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author uniquetrij
 */
public class VisualizationUtils {

    private static final List<Scalar> COLORS;
    private static final Map<Integer, String> LABELS;

    static {
        String[] colorNames = new String[]{
            "AliceBlue", "Chartreuse", "Aqua", "Aquamarine", "Azure", "Beige", "Bisque",
            "BlanchedAlmond", "BlueViolet", "BurlyWood", "CadetBlue", "AntiqueWhite",
            "Chocolate", "Coral", "CornflowerBlue", "Cornsilk", "Crimson", "Cyan",
            "DarkCyan", "DarkGoldenRod", "DarkGrey", "DarkKhaki", "DarkOrange",
            "DarkOrchid", "DarkSalmon", "DarkSeaGreen", "DarkTurquoise", "DarkViolet",
            "DeepPink", "DeepSkyBlue", "DodgerBlue", "FireBrick", "FloralWhite",
            "ForestGreen", "Fuchsia", "Gainsboro", "GhostWhite", "Gold", "GoldenRod",
            "Salmon", "Tan", "HoneyDew", "HotPink", "IndianRed", "Ivory", "Khaki",
            "Lavender", "LavenderBlush", "LawnGreen", "LemonChiffon", "LightBlue",
            "LightCoral", "LightCyan", "LightGoldenRodYellow", "LightGray", "LightGrey",
            "LightGreen", "LightPink", "LightSalmon", "LightSeaGreen", "LightSkyBlue",
            "LightSlateGray", "LightSlateGrey", "LightSteelBlue", "LightYellow", "Lime",
            "LimeGreen", "Linen", "Magenta", "MediumAquaMarine", "MediumOrchid",
            "MediumPurple", "MediumSeaGreen", "MediumSlateBlue", "MediumSpringGreen",
            "MediumTurquoise", "MediumVioletRed", "MintCream", "MistyRose", "Moccasin",
            "NavajoWhite", "OldLace", "Olive", "OliveDrab", "Orange", "OrangeRed",
            "Orchid", "PaleGoldenRod", "PaleGreen", "PaleTurquoise", "PaleVioletRed",
            "PapayaWhip", "PeachPuff", "Peru", "Pink", "Plum", "PowderBlue", "Purple",
            "Red", "RosyBrown", "RoyalBlue", "SaddleBrown", "Green", "SandyBrown",
            "SeaGreen", "SeaShell", "Sienna", "Silver", "SkyBlue", "SlateBlue",
            "SlateGray", "SlateGrey", "Snow", "SpringGreen", "SteelBlue", "GreenYellow",
            "Teal", "Thistle", "Tomato", "Turquoise", "Violet", "Wheat", "White",
            "WhiteSmoke", "Yellow", "YellowGreen"
        };

        COLORS = new ArrayList<>();
        for (String colorName : colorNames) {
            COLORS.add(ColorUtils.COLOR_LIST.get(colorName));
        }
        LABELS = new LinkedHashMap();
        StringBuilder key = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(Models.class.getClassLoader().getResource("tf-graphs/" + "mscoco_label_map.pbtxt").toURI()));
            lines.forEach(l -> {
                if (l.contains("id")) {
                    key.append(l.split(" ")[3].trim());
                }
                if (l.contains("display_name")) {
                    LABELS.put(Integer.parseInt(key.toString()), l.split(" ")[3].replace("\"", ""));
                    key.delete(0, key.length());
                }
            });
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(SimpleObjectDetector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void annotate(Mat cvImage, float[] normalizedBoundingBox, float classID, float normalizedScore) {
        Point bbTL = new Point(normalizedBoundingBox[1] * cvImage.width(), normalizedBoundingBox[0] * cvImage.height());
        Point bbBR = new Point(normalizedBoundingBox[3] * cvImage.width(), normalizedBoundingBox[2] * cvImage.height());
        int confidence = (int) Math.round(normalizedScore * 100);
        int clazzIndex = (int) classID;
        String clazzName = LABELS.get(clazzIndex).toLowerCase();
        final Scalar color = COLORS.get(clazzIndex);
        Imgproc.rectangle(cvImage, bbTL, bbBR, color, 1);

        double stickerPosition = -1;
        if (bbTL.y < 20) {
            stickerPosition = 1;
        }
        String label = String.format("%s %s%%", clazzName, confidence);
        Point stkrP = new Point(normalizedBoundingBox[1] * cvImage.width() + 8 * label.length(),
                normalizedBoundingBox[0] * cvImage.height() + 16 * stickerPosition);
        Point lablP = new Point(normalizedBoundingBox[1] * cvImage.width() + 5,
                normalizedBoundingBox[0] * cvImage.height() + 8 * stickerPosition + 2);

        Imgproc.rectangle(cvImage, bbTL, stkrP, color, -1);

        Imgproc.putText(cvImage, label, lablP, Core.FONT_HERSHEY_COMPLEX_SMALL, 0.5, new Scalar(0, 0, 0), 1);
    }

}
