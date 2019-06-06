/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquetrij.tf4j.utils;

import java.util.HashMap;
import java.util.Map;
import org.opencv.core.Scalar;

/**
 *
 * @author uniquetrij
 */
public class ColorUtils {

    public static final Map<String, Scalar> COLOR_LIST;

    static {
        COLOR_LIST = new HashMap<>();
        COLOR_LIST.put("AliceBlue", new Scalar(0xF0, 0xF8, 0xFF));
        COLOR_LIST.put("AntiqueWhite", new Scalar(0xFA, 0xEB, 0xD7));
        COLOR_LIST.put("Aqua", new Scalar(0x00, 0xFF, 0xFF));
        COLOR_LIST.put("Aquamarine", new Scalar(0x7F, 0xFF, 0xD4));
        COLOR_LIST.put("Azure", new Scalar(0xF0, 0xFF, 0xFF));
        COLOR_LIST.put("Beige", new Scalar(0xF5, 0xF5, 0xDC));
        COLOR_LIST.put("Bisque", new Scalar(0xFF, 0xE4, 0xC4));
        COLOR_LIST.put("Black", new Scalar(0x00, 0x00, 0x00));
        COLOR_LIST.put("BlanchedAlmond", new Scalar(0xFF, 0xEB, 0xCD));
        COLOR_LIST.put("Blue", new Scalar(0x00, 0x00, 0xFF));
        COLOR_LIST.put("BlueViolet", new Scalar(0x8A, 0x2B, 0xE2));
        COLOR_LIST.put("Brown", new Scalar(0xA5, 0x2A, 0x2A));
        COLOR_LIST.put("BurlyWood", new Scalar(0xDE, 0xB8, 0x87));
        COLOR_LIST.put("CadetBlue", new Scalar(0x5F, 0x9E, 0xA0));
        COLOR_LIST.put("Chartreuse", new Scalar(0x7F, 0xFF, 0x00));
        COLOR_LIST.put("Chocolate", new Scalar(0xD2, 0x69, 0x1E));
        COLOR_LIST.put("Coral", new Scalar(0xFF, 0x7F, 0x50));
        COLOR_LIST.put("CornflowerBlue", new Scalar(0x64, 0x95, 0xED));
        COLOR_LIST.put("Cornsilk", new Scalar(0xFF, 0xF8, 0xDC));
        COLOR_LIST.put("Crimson", new Scalar(0xDC, 0x14, 0x3C));
        COLOR_LIST.put("Cyan", new Scalar(0x00, 0xFF, 0xFF));
        COLOR_LIST.put("DarkBlue", new Scalar(0x00, 0x00, 0x8B));
        COLOR_LIST.put("DarkCyan", new Scalar(0x00, 0x8B, 0x8B));
        COLOR_LIST.put("DarkGoldenRod", new Scalar(0xB8, 0x86, 0x0B));
        COLOR_LIST.put("DarkGray", new Scalar(0xA9, 0xA9, 0xA9));
        COLOR_LIST.put("DarkGreen", new Scalar(0x00, 0x64, 0x00));
        COLOR_LIST.put("DarkKhaki", new Scalar(0xBD, 0xB7, 0x6B));
        COLOR_LIST.put("DarkMagenta", new Scalar(0x8B, 0x00, 0x8B));
        COLOR_LIST.put("DarkOliveGreen", new Scalar(0x55, 0x6B, 0x2F));
        COLOR_LIST.put("DarkOrange", new Scalar(0xFF, 0x8C, 0x00));
        COLOR_LIST.put("DarkOrchid", new Scalar(0x99, 0x32, 0xCC));
        COLOR_LIST.put("DarkRed", new Scalar(0x8B, 0x00, 0x00));
        COLOR_LIST.put("DarkSalmon", new Scalar(0xE9, 0x96, 0x7A));
        COLOR_LIST.put("DarkSeaGreen", new Scalar(0x8F, 0xBC, 0x8F));
        COLOR_LIST.put("DarkSlateBlue", new Scalar(0x48, 0x3D, 0x8B));
        COLOR_LIST.put("DarkSlateGray", new Scalar(0x2F, 0x4F, 0x4F));
        COLOR_LIST.put("DarkTurquoise", new Scalar(0x00, 0xCE, 0xD1));
        COLOR_LIST.put("DarkViolet", new Scalar(0x94, 0x00, 0xD3));
        COLOR_LIST.put("DeepPink", new Scalar(0xFF, 0x14, 0x93));
        COLOR_LIST.put("DeepSkyBlue", new Scalar(0x00, 0xBF, 0xFF));
        COLOR_LIST.put("DimGray", new Scalar(0x69, 0x69, 0x69));
        COLOR_LIST.put("DodgerBlue", new Scalar(0x1E, 0x90, 0xFF));
        COLOR_LIST.put("FireBrick", new Scalar(0xB2, 0x22, 0x22));
        COLOR_LIST.put("FloralWhite", new Scalar(0xFF, 0xFA, 0xF0));
        COLOR_LIST.put("ForestGreen", new Scalar(0x22, 0x8B, 0x22));
        COLOR_LIST.put("Fuchsia", new Scalar(0xFF, 0x00, 0xFF));
        COLOR_LIST.put("Gainsboro", new Scalar(0xDC, 0xDC, 0xDC));
        COLOR_LIST.put("GhostWhite", new Scalar(0xF8, 0xF8, 0xFF));
        COLOR_LIST.put("Gold", new Scalar(0xFF, 0xD7, 0x00));
        COLOR_LIST.put("GoldenRod", new Scalar(0xDA, 0xA5, 0x20));
        COLOR_LIST.put("Gray", new Scalar(0x80, 0x80, 0x80));
        COLOR_LIST.put("Green", new Scalar(0x00, 0x80, 0x00));
        COLOR_LIST.put("GreenYellow", new Scalar(0xAD, 0xFF, 0x2F));
        COLOR_LIST.put("HoneyDew", new Scalar(0xF0, 0xFF, 0xF0));
        COLOR_LIST.put("HotPink", new Scalar(0xFF, 0x69, 0xB4));
        COLOR_LIST.put("IndianRed", new Scalar(0xCD, 0x5C, 0x5C));
        COLOR_LIST.put("Indigo", new Scalar(0x4B, 0x00, 0x82));
        COLOR_LIST.put("Ivory", new Scalar(0xFF, 0xFF, 0xF0));
        COLOR_LIST.put("Khaki", new Scalar(0xF0, 0xE6, 0x8C));
        COLOR_LIST.put("Lavender", new Scalar(0xE6, 0xE6, 0xFA));
        COLOR_LIST.put("LavenderBlush", new Scalar(0xFF, 0xF0, 0xF5));
        COLOR_LIST.put("LawnGreen", new Scalar(0x7C, 0xFC, 0x00));
        COLOR_LIST.put("LemonChiffon", new Scalar(0xFF, 0xFA, 0xCD));
        COLOR_LIST.put("LightBlue", new Scalar(0xAD, 0xD8, 0xE6));
        COLOR_LIST.put("LightCoral", new Scalar(0xF0, 0x80, 0x80));
        COLOR_LIST.put("LightCyan", new Scalar(0xE0, 0xFF, 0xFF));
        COLOR_LIST.put("LightGoldenRodYellow", new Scalar(0xFA, 0xFA, 0xD2));
        COLOR_LIST.put("LightGray", new Scalar(0xD3, 0xD3, 0xD3));
        COLOR_LIST.put("LightGreen", new Scalar(0x90, 0xEE, 0x90));
        COLOR_LIST.put("LightPink", new Scalar(0xFF, 0xB6, 0xC1));
        COLOR_LIST.put("LightSalmon", new Scalar(0xFF, 0xA0, 0x7A));
        COLOR_LIST.put("LightSeaGreen", new Scalar(0x20, 0xB2, 0xAA));
        COLOR_LIST.put("LightSkyBlue", new Scalar(0x87, 0xCE, 0xFA));
        COLOR_LIST.put("LightSlateGray", new Scalar(0x77, 0x88, 0x99));
        COLOR_LIST.put("LightSteelBlue", new Scalar(0xB0, 0xC4, 0xDE));
        COLOR_LIST.put("LightYellow", new Scalar(0xFF, 0xFF, 0xE0));
        COLOR_LIST.put("Lime", new Scalar(0x00, 0xFF, 0x00));
        COLOR_LIST.put("LimeGreen", new Scalar(0x32, 0xCD, 0x32));
        COLOR_LIST.put("Linen", new Scalar(0xFA, 0xF0, 0xE6));
        COLOR_LIST.put("Magenta", new Scalar(0xFF, 0x00, 0xFF));
        COLOR_LIST.put("Maroon", new Scalar(0x80, 0x00, 0x00));
        COLOR_LIST.put("MediumAquaMarine", new Scalar(0x66, 0xCD, 0xAA));
        COLOR_LIST.put("MediumBlue", new Scalar(0x00, 0x00, 0xCD));
        COLOR_LIST.put("MediumOrchid", new Scalar(0xBA, 0x55, 0xD3));
        COLOR_LIST.put("MediumPurple", new Scalar(0x93, 0x70, 0xDB));
        COLOR_LIST.put("MediumSeaGreen", new Scalar(0x3C, 0xB3, 0x71));
        COLOR_LIST.put("MediumSlateBlue", new Scalar(0x7B, 0x68, 0xEE));
        COLOR_LIST.put("MediumSpringGreen", new Scalar(0x00, 0xFA, 0x9A));
        COLOR_LIST.put("MediumTurquoise", new Scalar(0x48, 0xD1, 0xCC));
        COLOR_LIST.put("MediumVioletRed", new Scalar(0xC7, 0x15, 0x85));
        COLOR_LIST.put("MidnightBlue", new Scalar(0x19, 0x19, 0x70));
        COLOR_LIST.put("MintCream", new Scalar(0xF5, 0xFF, 0xFA));
        COLOR_LIST.put("MistyRose", new Scalar(0xFF, 0xE4, 0xE1));
        COLOR_LIST.put("Moccasin", new Scalar(0xFF, 0xE4, 0xB5));
        COLOR_LIST.put("NavajoWhite", new Scalar(0xFF, 0xDE, 0xAD));
        COLOR_LIST.put("Navy", new Scalar(0x00, 0x00, 0x80));
        COLOR_LIST.put("OldLace", new Scalar(0xFD, 0xF5, 0xE6));
        COLOR_LIST.put("Olive", new Scalar(0x80, 0x80, 0x00));
        COLOR_LIST.put("OliveDrab", new Scalar(0x6B, 0x8E, 0x23));
        COLOR_LIST.put("Orange", new Scalar(0xFF, 0xA5, 0x00));
        COLOR_LIST.put("OrangeRed", new Scalar(0xFF, 0x45, 0x00));
        COLOR_LIST.put("Orchid", new Scalar(0xDA, 0x70, 0xD6));
        COLOR_LIST.put("PaleGoldenRod", new Scalar(0xEE, 0xE8, 0xAA));
        COLOR_LIST.put("PaleGreen", new Scalar(0x98, 0xFB, 0x98));
        COLOR_LIST.put("PaleTurquoise", new Scalar(0xAF, 0xEE, 0xEE));
        COLOR_LIST.put("PaleVioletRed", new Scalar(0xDB, 0x70, 0x93));
        COLOR_LIST.put("PapayaWhip", new Scalar(0xFF, 0xEF, 0xD5));
        COLOR_LIST.put("PeachPuff", new Scalar(0xFF, 0xDA, 0xB9));
        COLOR_LIST.put("Peru", new Scalar(0xCD, 0x85, 0x3F));
        COLOR_LIST.put("Pink", new Scalar(0xFF, 0xC0, 0xCB));
        COLOR_LIST.put("Plum", new Scalar(0xDD, 0xA0, 0xDD));
        COLOR_LIST.put("PowderBlue", new Scalar(0xB0, 0xE0, 0xE6));
        COLOR_LIST.put("Purple", new Scalar(0x80, 0x00, 0x80));
        COLOR_LIST.put("Red", new Scalar(0xFF, 0x00, 0x00));
        COLOR_LIST.put("RosyBrown", new Scalar(0xBC, 0x8F, 0x8F));
        COLOR_LIST.put("RoyalBlue", new Scalar(0x41, 0x69, 0xE1));
        COLOR_LIST.put("SputleBrown", new Scalar(0x8B, 0x45, 0x13));
        COLOR_LIST.put("Salmon", new Scalar(0xFA, 0x80, 0x72));
        COLOR_LIST.put("SandyBrown", new Scalar(0xF4, 0xA4, 0x60));
        COLOR_LIST.put("SeaGreen", new Scalar(0x2E, 0x8B, 0x57));
        COLOR_LIST.put("SeaShell", new Scalar(0xFF, 0xF5, 0xEE));
        COLOR_LIST.put("Sienna", new Scalar(0xA0, 0x52, 0x2D));
        COLOR_LIST.put("Silver", new Scalar(0xC0, 0xC0, 0xC0));
        COLOR_LIST.put("SkyBlue", new Scalar(0x87, 0xCE, 0xEB));
        COLOR_LIST.put("SlateBlue", new Scalar(0x6A, 0x5A, 0xCD));
        COLOR_LIST.put("SlateGray", new Scalar(0x70, 0x80, 0x90));
        COLOR_LIST.put("Snow", new Scalar(0xFF, 0xFA, 0xFA));
        COLOR_LIST.put("SpringGreen", new Scalar(0x00, 0xFF, 0x7F));
        COLOR_LIST.put("SteelBlue", new Scalar(0x46, 0x82, 0xB4));
        COLOR_LIST.put("Tan", new Scalar(0xD2, 0xB4, 0x8C));
        COLOR_LIST.put("Teal", new Scalar(0x00, 0x80, 0x80));
        COLOR_LIST.put("Thistle", new Scalar(0xD8, 0xBF, 0xD8));
        COLOR_LIST.put("Tomato", new Scalar(0xFF, 0x63, 0x47));
        COLOR_LIST.put("Turquoise", new Scalar(0x40, 0xE0, 0xD0));
        COLOR_LIST.put("Violet", new Scalar(0xEE, 0x82, 0xEE));
        COLOR_LIST.put("Wheat", new Scalar(0xF5, 0xDE, 0xB3));
        COLOR_LIST.put("White", new Scalar(0xFF, 0xFF, 0xFF));
        COLOR_LIST.put("WhiteSmoke", new Scalar(0xF5, 0xF5, 0xF5));
        COLOR_LIST.put("Yellow", new Scalar(0xFF, 0xFF, 0x00));
        COLOR_LIST.put("YellowGreen", new Scalar(0x9A, 0xCD, 0x32));
        COLOR_LIST.put("DarkGrey", new Scalar(0xA9, 0xA9, 0xA9));
        COLOR_LIST.put("LightGrey", new Scalar(0xD3, 0xD3, 0xD3));
        COLOR_LIST.put("LightSlateGrey", new Scalar(0x99, 0x88, 0x77));
        COLOR_LIST.put("SlateGrey", new Scalar(0x90, 0x80, 0x70));
        COLOR_LIST.put("SaddleBrown", new Scalar(0x8B, 0x45, 0x13));
    }
}
