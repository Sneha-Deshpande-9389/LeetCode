package Confluent;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Tail {
    public static void test() {
        try {
            //Simple reading of bytes
            FileInputStream fileInputStream = new FileInputStream("RepeatedSubStringLC459.java");
            byte[] arr = new byte[1024];
            int actualBytesRead = fileInputStream.read(arr, 0, arr.length);

            //Can read characters and lines now
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String lineRead = bufferedReader.readLine();
            char [] charArrr = new char[1024];
            int actulCharsRead = bufferedReader.read(charArrr, 0, charArrr.length);

            //File reader allows reading of characters from a file
            FileReader fileReader = new FileReader("RepeatedSubStringLC459.java");
            actulCharsRead = fileReader.read(charArrr, 0, charArrr.length);

            //It is a good idea to wrap a bufferedReader around a fileReader
            BufferedReader betterFileReader = new BufferedReader(new FileReader("RepeatedSubStringLC459.java"));
            lineRead = betterFileReader.readLine();
            actulCharsRead = betterFileReader.read(charArrr, 0, charArrr.length);


            List<String> lines = betterFileReader.lines().collect(Collectors.toList());




            //allows reading int, long, short, byte, line etc. Scanner tends to be very slow
            Scanner scanner = new Scanner("path to file");
            //can also give inputStream as source
            scanner = new Scanner(System.in);
            long valueRead = scanner.nextLong();

            //might wanna check out javadoc for more info

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useRandomAccess() {
        int linesToRead = 6;
        try {
            RandomAccessFile file =
                    new RandomAccessFile("/Users/snehadeshpande/Downloads/Lets-do-this-Krishna/Prep/MarketReady/src/LeetCode/Tail.java", "rw");
            long cur = file.length() - 1;
            StringBuilder result = new StringBuilder();
            int linesRead = 0;
            while (cur >= 0) {
                file.seek(cur);
                char c = (char) file.read();
                if (System.lineSeparator().equals(String.valueOf(c))) {
                    linesRead++;
                    if (linesRead > linesToRead) break;
                }
                result.append(c);
                cur--;
            }
            System.out.println(result.reverse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        useRandomAccess();
    }
}
