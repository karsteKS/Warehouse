package lt.warehouse.products;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import static lt.warehouse.products.CSVReadear.plList;

import static lt.warehouse.products.CSVReadear.readCSV;
import static lt.warehouse.products.First.formatter;
import static lt.warehouse.products.First.today;

public class First extends CSVReadear {

    public static Scanner sc = new Scanner(System.in);
    ;
    public static int quantitiestLeft;
    public static Date today = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {

        readCSV();
        start();
    }

    private static void start() throws ParseException {

        System.out.println("");
        System.out.println("Please choose option");
        System.out.println("Check how much left [ 1 ]");
        System.out.println("What is expired [ 2 ]");
        System.out.println("What is going be expired soon [ 3 ]");
        System.out.println("Print all the list [ 4 ]");
        System.out.println("");
        switch (sc.nextInt()) {
            case 1:
                quantitiestLeft();
                break;
            case 2:
                expiredProducts();
                break;
            case 3:
                willExpireSoon();
                break;
            case 4:
                System.out.println(plList);
                start();
                break;
            default:
                start();
                break;
        }
    }

    private static void quantitiestLeft() throws ParseException {

        System.out.println("Please enter a quantity to check");
        quantitiestLeft = sc.nextInt();
        System.out.println("Products whose quantity is equal to or less "
                + quantitiestLeft);
        for (Product plList1 : plList) {
            if (plList1.getQuantity() <= quantitiestLeft) {
                System.out.println("Name: " + plList1.getName() + ", Quantity: "
                        + plList1.getQuantity());
            }
        }
        start();
    }

    private static void expiredProducts() throws ParseException {
        System.out.println("Products that are expired: ");

        for (Product plList1 : plList) {
            if (formatter.parse(plList1.getDate()).before(today)) {
                System.out.println("Name: " + plList1.getName() + " Date: "
                        + plList1.getDate());
            }
        }
        start();
    }

    private static void willExpireSoon() throws ParseException {
        System.out.println("Products that will expire next week:");

        for (Product plList1 : plList) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 7);
            String plusSeven = formatter.format(cal.getTime());
            Date plusSevenDate = formatter.parse(plusSeven);
            if (formatter.parse(plList1.getDate()).before(plusSevenDate) && formatter.parse(plList1.getDate()).after(today)) {
                System.out.println("Name: " + plList1.getName() + " Date: "
                        + plList1.getDate());
                System.out.println(plusSeven);
            }
        }
         start();
    }
}
