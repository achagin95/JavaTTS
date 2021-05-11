package net.thumbtack.school.colors.v3;

public enum Color {
    RED, GREEN, BLUE;


    public static void checkColorFromColor(Color color) throws ColorException {

        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }

    }

    public static Color colorFromString(String colorString) throws ColorException {

        if (colorString == null || colorString.length() == 0) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }

        try {
            return Color.valueOf(colorString);
        } catch (IllegalArgumentException ex) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }



    }


}
