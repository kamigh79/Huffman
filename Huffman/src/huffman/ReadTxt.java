package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadTxt {

    String test() {
        String test = "";
        try {
            File check = new File("String.txt");

            FileReader filereader = new FileReader(check);

            BufferedReader reader = new BufferedReader(filereader);
            ArrayList<String> lines = new ArrayList<>();
            String st;
            while ((st = reader.readLine()) != null) {
                lines.add(st);
            }
            for (int i = 0; i < lines.size(); i++) {
                test = test + lines.get(i) + "\n";

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return test;

    }
}
