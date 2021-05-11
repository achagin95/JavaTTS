package net.thumbtack.school.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.*;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

public class FileService {


    //1.
    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)))) {
            bos.write(array, 0, array.length);
        }


    }

    //2.
    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            bos.write(array, 0, array.length);
        }

    }

    //3.
    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {

        byte[] array = null;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)))) {

            array = new byte[bis.available()];
            bis.read(array, 0, array.length);


        }
        return array;


    }

    //4.
    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {

        byte[] array = null;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {

            array = new byte[bis.available()];
            bis.read(array, 0, array.length);

        }

        return array;
    }

    //5.
    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        byte[] finalArray = null;
        byte[] byteArray = null;
        final int SIZE = array.length;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            for (byte b : array) baos.write(b);
            byteArray = baos.toByteArray();
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(byteArray)) {
            byte[] array1 = new byte[SIZE];
            finalArray = new byte[SIZE / 2];
            int j = 0;
            bais.read(array1);
            for (int i = 0; i < SIZE; i = i + 2) {
                finalArray[j] = array1[i];
                j++;
            }

        }
        return finalArray;

    }

    //6.
    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(fileName, array);
        //изначально взял буферизованый метод, не стал переписывать
    }

    //7.
    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(file, array);
    }

    //8.
    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(fileName);
    }

    //9.
    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        return readByteArrayFromBinaryFile(file);
    }


    //10.
    public static void writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            int xLeft = rect.getTopLeft().getX();
            int xRight = rect.getBottomRight().getX();
            int yTop = rect.getTopLeft().getY();
            int yBottom = rect.getBottomRight().getY();
            dos.writeInt(xLeft);
            dos.writeInt(xRight);
            dos.writeInt(yTop);
            dos.writeInt(yBottom);

        }


    }

    //11.
    public static Rectangle readRectangleFromBinaryFile(File file) throws IOException {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        try (DataInputStream dis = new DataInputStream((new FileInputStream(file)))) {
            x1 = dis.readInt();
            x2 = dis.readInt();
            y1 = dis.readInt();
            y2 = dis.readInt();
        }
        return new Rectangle(x1, y1, x2, y2);
        //чтоб не вводить проверку начальных параметров на !=null, присвоил им значение ноль
        //т.к. в задании про проверку ничео не сказано

    }

    //12.
    public static void writeColoredRectangleToBinaryFile(File file, ColoredRectangle rect) throws IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            int xLeft = rect.getTopLeft().getX();
            int xRight = rect.getBottomRight().getX();
            int yTop = rect.getTopLeft().getY();
            int yBottom = rect.getBottomRight().getY();
            String color = rect.getColor().toString();
            dos.writeInt(xLeft);
            dos.writeInt(xRight);
            dos.writeInt(yTop);
            dos.writeInt(yBottom);
            dos.writeUTF(color);
        }


    }

    //13.
    public static ColoredRectangle readColoredRectangleFromBinaryFile(File file) throws ColorException, IOException {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        String color = "QWE";

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            x1 = dis.readInt();
            x2 = dis.readInt();
            y1 = dis.readInt();
            y2 = dis.readInt();
            color = dis.readUTF();
        }
        return new ColoredRectangle(x1, y1, x2, y2, color);

    }

    //14.
    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rects) throws IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (Rectangle rect : rects) {
                int xLeft = rect.getTopLeft().getX();
                int xRight = rect.getBottomRight().getX();
                int yTop = rect.getTopLeft().getY();
                int yBottom = rect.getBottomRight().getY();
                dos.writeInt(xLeft);
                dos.writeInt(xRight);
                dos.writeInt(yTop);
                dos.writeInt(yBottom);
            }
        }

    }

    //15.
    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws IOException {
        int x1, x2, y1, y2;
        Rectangle[] newRect = new Rectangle[(int) file.length() / 16];

        int j = newRect.length * 16;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {

            for (int i = 0; i < newRect.length; i++) {
                j = j - 16;
                raf.seek(j);
                x1 = raf.readInt();
                x2 = raf.readInt();
                y1 = raf.readInt();
                y2 = raf.readInt();
                newRect[i] = new Rectangle(x1, y1, x2, y2);
            }

        }
        return newRect;
    }


    //16.
    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {

        int x1 = rect.getTopLeft().getX();
        int x2 = rect.getBottomRight().getX();
        int y1 = rect.getTopLeft().getY();
        int y2 = rect.getBottomRight().getY();
        String s = Integer.toString(x1).concat(" ").concat(Integer.toString(y1)).concat(" ").concat(Integer.toString(x2)).concat(" ").concat(Integer.toString(y2));
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            osw.write(s);
        }

    }

    //17.
    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException {

        int[] intArr = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {

            String str = br.readLine();
            String[] strArr = str.split(" ");
            intArr = new int[strArr.length];
            for (int i = 0; i < intArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }

        }

        return new Rectangle(intArr[0], intArr[1], intArr[2], intArr[3]);

    }

    //18.
    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {

        int x1 = rect.getTopLeft().getX();
        int x2 = rect.getBottomRight().getX();
        int y1 = rect.getTopLeft().getY();
        int y2 = rect.getBottomRight().getY();

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            bw.write(Integer.toString(x1));
            bw.newLine();
            bw.write(Integer.toString(y1));
            bw.newLine();
            bw.write(Integer.toString(x2));
            bw.newLine();
            bw.write(Integer.toString(y2));
            //bw.newLine();
        }
    }

    //19.
    public static Rectangle readRectangleFromTextFileFourLines(File file) throws IOException {


        int x1, y1, x2, y2;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String str = br.readLine();
            x1 = Integer.parseInt(str);
            str = br.readLine();
            y1 = Integer.parseInt(str);
            str = br.readLine();
            x2 = Integer.parseInt(str);
            str = br.readLine();
            y2 = Integer.parseInt(str);
        }

        return new Rectangle(x1, y1, x2, y2);


    }

    //20.
    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {

        String s = trainee.getFullName().concat(" ").concat(Integer.toString(trainee.getRating()));
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            bw.write(s);
        }

    }

    //21.
    public static Trainee readTraineeFromTextFileOneLine(File file) throws TrainingException, IOException {

        String s1, s2;
        int rait;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String str = br.readLine();
            String[] strArr = str.split(" ");
            s1 = strArr[0];
            s2 = strArr[1];
            rait = Integer.parseInt(strArr[2]);
        }

        return new Trainee(s1, s2, rait);

    }

    //22.
    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {

        String[] s = new String[]{trainee.getFirstName(), trainee.getLastName(), Integer.toString(trainee.getRating())};

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            String str;
            for (int i = 0; i < s.length; i++) {
                str = s[i];
                bw.write(str);
                bw.newLine();
            }

        }

    }

    //23.
    public static Trainee readTraineeFromTextFileThreeLines(File file) throws TrainingException, IOException {
        String s1, s2;
        int rait;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            s1 = br.readLine();
            s2 = br.readLine();
            rait = Integer.parseInt(br.readLine());
        }
        return new Trainee(s1, s2, rait);
    }


    //24.
    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(trainee);
        }

    }


    //25.
    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException {
        Trainee t = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            t = (Trainee) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return t;

    }

    //26.
    public static String serializeTraineeToJsonString(Trainee trainee) {
        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    //27.
    public static Trainee deserializeTraineeFromJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);

    }


    //28.
    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            Gson gs = new Gson();
            gs.toJson(trainee, bw);
        }
    }


    //29.
    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        Trainee t;// = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Gson gs = new Gson();
            t = gs.fromJson(br, Trainee.class);
        }


        return t;

    }


}
