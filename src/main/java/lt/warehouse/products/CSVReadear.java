package lt.warehouse.products;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReadear {

    public static List<Product> plList;

    protected static List<Product> readCSV() throws ParseException {

        plList = new ArrayList<>();
        String csvFile = "sample.csv";
        try (final BufferedReader reader = new BufferedReader(
                new FileReader(csvFile))) {

            String line;
            line = reader.readLine();
            Scanner scanner;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                Product pl = new Product();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    switch (index) {
                        case 0:
                            pl.setName(data);
                            break;
                        case 1:
                            pl.setCode(data);
                            break;
                        case 2:
                            pl.setQuantity(Integer.valueOf(data));
                            break;
                        case 3:
                            pl.setDate(data);
                            break;
                        default:
                            System.out.println("invalid data:" + data);
                            break;
                    }
                    index++;
                }
                index = 0;
                if (plList.contains(pl)) {
                    plList.get(plList.indexOf(pl)).
                            setQuantity(plList.get(plList.indexOf(pl))
                                    .getQuantity() + pl.getQuantity());
                } else {
                    plList.add(pl);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(plList);
        return plList;
    }

}
