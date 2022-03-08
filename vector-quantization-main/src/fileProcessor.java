import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileProcessor {
    public static String writeToFile(String data, String dir) {
        try {
            File f = new File(dir);
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedWriter w = new BufferedWriter(new FileWriter(f));
            w.write(data);
            w.close();
        } catch (IOException e) {
            System.out.println("Writing failed");
        }
        return dir;
    }

    protected static String binaryStringToBits(String s, int extraLen) {
        //complete the string to multiple of 8
        for (int i = 0; i < extraLen; i++)
            s += '0';

        //Pack into bytes
        byte[] arr = new byte[s.length() / 8];

        for (int i = 0; i <= s.length() - 8; i += 8) {
            arr[i / 8] = (byte) Integer.parseInt(s.substring(i, i + 8), 2);
        }
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            int c = arr[i];
            if (c < 0) c = c & 0xFF;
            res += (char) c;
        }
        return res;
    }

    protected static String bitsToBinaryString(String s, int extraLen) {

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            result += toBin(s.charAt(i), 8);
        }
        result = result.substring(0, result.length() - extraLen);
        return result;

    }

    protected static String toBin(int x, int numOfBits) {
        if (x < 0) x *= -1;
        String res = Integer.toBinaryString(x);
        while (res.length() < numOfBits)
            res = "0" + res;
        return res;
    }

    protected static int log2(int x) {
        return (int) Math.ceil(Math.log(x) / Math.log(2));
    }

    public static String fileToString(String dir) {
        String res = "";
        try {
            File ogFile = new File(dir);
            Scanner reader = new Scanner(ogFile);
            String tmp;
            while (reader.hasNext()) {
                tmp = reader.nextLine();
                res += '\n';
                res += tmp;
            }
            res = res.substring(1); //remove the first \n
        } catch (IOException e) {
            System.out.println("File Doesn't exist !");
            System.exit(1);
        }
        return res;
    }
}
