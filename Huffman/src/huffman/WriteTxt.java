package huffman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTxt {

    public void writer(String txt) {
        try {
            File file = new File("OG.txt");
            file.createNewFile();

            FileWriter myWriter = new FileWriter(file);
            myWriter.write(txt);
            myWriter.close();
            
            

        } catch (IOException e) {
            //show error on Log screen
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

}
