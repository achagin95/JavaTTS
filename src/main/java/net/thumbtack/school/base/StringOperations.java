package net.thumbtack.school.base;

public class StringOperations {


    public static int getSummaryLength(String[] strings) {

        StringBuilder sb = new StringBuilder();
        for (String elem : strings) {
            sb.append(elem);
        }

        return sb.toString().length();
    }

    public static String getFirstAndLastLetterString(String string) {
        int len = string.length();

        return string.substring(0,1).concat(string.substring(len-1,len));
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        String s1 = string1.substring(index, index + 1);
        String s2 = string2.substring(index, index + 1);
        return s1.equals(s2);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        int n1 = string1.indexOf(character);
        int n2 = string2.indexOf(character);
        return n1 == n2;
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        int n1 = string1.lastIndexOf(character);
        int n2 = string2.lastIndexOf(character);
        return n1 == n2;
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        int n1 = string1.indexOf(str);
        int n2 = string2.indexOf(str);
        return n1 == n2;

    }


    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        int n1 = string1.lastIndexOf(str);
        int n2 = string2.lastIndexOf(str);
        return n1 == n2;

    }


    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);

    }


    public static boolean isEqualIgnoreCase(String string1, String string2) {
        String s1 = string1.toLowerCase();
        String s2 = string2.toLowerCase();
        return s1.equals(s2);

    }


    public static boolean isLess(String string1, String string2) {

        return string1.compareTo(string2) < 0;


    }


    public static boolean isLessIgnoreCase(String string1, String string2) {

        return  string1.toLowerCase().compareTo(string2.toLowerCase()) < 0;

    }


    public static String concat(String string1, String string2) {
        //12
        return string1.concat(string2);

    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {

        return string1.startsWith(prefix) && string2.startsWith(prefix);

    }


    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) && string2.endsWith(suffix);

    }


    public static String getCommonPrefix(String string1, String string2) {
        //15
        int len1 = string1.length();
        int len2 = string2.length();
        int rez = 0;
        String sn = "";
        if (len1 >= len2) {
            for (int i = 0; i <= len2; i++) {
                if (string1.regionMatches(true, 0, string2, 0, i + 1)) {
                    rez++;
                } else break;
            }
        } else {
            for (int i = 0; i <= len1; i++) {
                if (string1.regionMatches(true, 0, string2, 0, i + 1)) {
                    rez++;
                } else break;
            }
        }

        return string1.substring(0, rez);


    }


    public static String reverse(String string) {
        StringBuilder s1 = new StringBuilder(string);
        s1.reverse();

        return s1.toString();

    }


    public static boolean isPalindrome(String string) {
        StringBuilder s1 = new StringBuilder(string);

        return s1.reverse().toString().equals(string);
    }


    public static boolean isPalindromeIgnoreCase(String string) {
        // 18
        String sLowCase = string.toLowerCase();

        return isPalindrome(sLowCase);
    }


    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        int maxLen = 0;
        String rez = null;

        for (String str : strings) {
            if (isPalindromeIgnoreCase(str)) {
                if (str.length() > maxLen) {
                    rez = str;
                    maxLen = str.length();
                }
            }
        }
        return rez;


    }


    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        //20
        if (string1.length() >= index + length && string2.length() >= index + length) {
            String s1 = string1.substring(index, length + index);
            String s2 = string2.substring(index, length + index);
            return s1.equals(s2);
        } else return false;
    }


    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2) {
        String s1 = string1.replace(replaceInStr1, replaceByInStr1);
        String s2 = string2.replace(replaceInStr2, replaceByInStr2);
        return s1.equals(s2);

    }


    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2) {
        //22
        String s1 = string1.replace(replaceInStr1, replaceByInStr1);
        String s2 = string2.replace(replaceInStr2, replaceByInStr2);
        return s1.equals(s2);

    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {

        String s1 = string.replaceAll(" ", "").toLowerCase();

        StringBuilder s2 = new StringBuilder(s1);
        String s = s2.reverse().toString();
        return s.equals(s1);

    }


    public static boolean isEqualAfterTrimming(String string1, String string2) {
        String s1 = string1.trim();
        String s2 = string2.trim();
        return s1.equals(s2);

    }

    public static String makeCsvStringFromInts(int[] array) {
        //25

        return makeCsvStringBuilderFromInts(array).toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        //26

        return makeCsvStringBuilderFromDoubles(array).toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        StringBuilder s = new StringBuilder();
        int len = array.length;
        if (len > 0) {
            for (int i = 0; i < len - 1; i++) {
                s.append(array[i]).append(",");
            }
            s.append(array[len - 1]);
            return s;
        }
        return s;
    }


    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {

        StringBuilder s = new StringBuilder();

        if (array.length > 0) {

            for (int i = 0; i < array.length - 1; i++) {
                s.append(String.format("%.2f", array[i])).append(",");
            }
            s.append(String.format("%.2f", array[array.length - 1]));
            return s;

        } else {
            return s;
        }
    }


    public static StringBuilder removeCharacters(String string, int[] positions) {
        //29
        StringBuilder sb1 = new StringBuilder(string);
        int n = 0;
        for (int i = 0; i < positions.length; i++) {
            sb1 = sb1.delete(positions[i] - n, positions[i] - n + 1);
            n++;
        }
        return sb1;
    }


    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        //30
        StringBuilder sb1 = new StringBuilder(string);
        int n = 0;
        for (int i = 0; i < positions.length; i++) {
            sb1 = sb1.insert(positions[i] + n, characters[i]);
            n++;
        }
        return sb1;

    }


}
