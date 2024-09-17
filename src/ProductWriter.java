import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {

    public static void main(String[] args)
    {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;

        String prodRec = "";
        String ID = "";
        String prodName = "";
        String prodDescription = "";
        double cost = 0;


        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            prodName = SafeInput.getNonZeroLenString(in, "Enter the product name: ");
            prodDescription = SafeInput.getNonZeroLenString(in, "Enter the product description: ");
            cost = SafeInput.getDouble(in, "Enter the product cost: ");

            prodRec = ID + "," + prodName +  "," + prodDescription+ "," + cost;
            products.add(prodRec);

            done = SafeInput.getYNConfirm(in, "Are you done?" );

        }while(!done);

        for( String p : products)
            System.out.println(p);
        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : products)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
