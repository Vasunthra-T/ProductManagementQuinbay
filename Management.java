import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Management {
    static ArrayList<ProductDetails> products = new ArrayList<>();
    static ArrayList<WholesalerDetails> wholesaler_products = new ArrayList<>();
    static ArrayList<RetailerDetails> retailer_products = new ArrayList<>();


    public static void main(String[] args) {
        try {
            Scanner sc1 = new Scanner(new File("/Users/vasunthra/Downloads/product_data - Sheet1.csv"));
            sc1.useDelimiter(";");
            while (sc1.hasNext()) {
                //System.out.println(sc1.next());
                String[] productSplit = sc1.next().split(",", 4);
                products.add(new ProductDetails(productSplit[0], productSplit[1], productSplit[2], productSplit[3]));
            }
            sc1.close();


            Scanner sc2 = new Scanner(new File("/Users/vasunthra/Downloads/product_data - Sheet2.csv"));
            sc2.useDelimiter(";");
            while (sc2.hasNext()) {

                String[] wholesalerSplit = sc2.next().split(",", 2);
                wholesaler_products.add(new WholesalerDetails(wholesalerSplit[0], wholesalerSplit[1]));
            }
            sc2.close();

            Scanner sc3 = new Scanner(new File("/Users/vasunthra/Downloads/product_data - Sheet3.csv"));
            sc3.useDelimiter(";");
            while (sc3.hasNext()) {

                String[] retailerSplit = sc3.next().split(",", 2);
                retailer_products.add(new RetailerDetails(retailerSplit[0], retailerSplit[1]));
            }
            sc3.close();


            System.out.println("Enter the option: ");
            System.out.println("1. View products");
            System.out.println("2. View wholesalers");
            System.out.println("3. View retailers");
            System.out.println("4. Allocate product to wholesaler");
            Scanner sc = new Scanner(System.in);
            int user = sc.nextInt();

            switch (user) {
                case 1: {
                    //Product sp = new Product();
                    ProductDetails.showProduct();
                    break;
                }
                case 2: {
                    WholesalerDetails.showWholesaler();
                    break;

                }
                case 3: {
                    RetailerDetails.showRetailer();
                    break;

                }
                case 4: {
                    System.out.println("Enter name of the product to buy");
                    String name_w = sc.next();
                    System.out.println("Enter number of items to buy");
                    int stock_w = sc.nextInt();
                    WholesalerDetails.updateWholesaler(name_w,stock_w,wholesaler_products);
                    WholesalerDetails.showWholesaler();
                    //Wholesaler ws = new Wholesaler();
                    //ws.changeStockProduct(id,items);
                    //ws.changeStockProduct_Whole(id, items);
                    break;

                }/*
            case 5:
            {
                System.out.println("Enter id of the product to buy");
                int id = sc.nextInt();
                System.out.println("Enter number of items to buy");
                int items = sc.nextInt();
                //Retailer rs = new Retailer();
                rs.changeStockRetail(id,items);
                rs.changeStockRetail_Whole(id,items);
                break;

            }
            case 6:
            {


            }
            case 7:
            {

            }
            case 8:
            {

            }*/
            case 9:
            {
                System.exit(0);

            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
class ProductDetails{
    String id;
    String name;
    String price;
    String stock;
    ProductDetails(String id,String name, String price,String stock)
    {
        this.id=id;
        this.name=name;
        this.price=price;
        this.stock=stock;
    }
    ProductDetails() {
    }
    static void showProduct()
    {
        for (ProductDetails p : Management.products) {
            System.out.println(p.id + " " + p.name + " " + p.price + " " + p.stock);
        }

    }
}




class WholesalerDetails extends ProductDetails {
    //String id;
    String name;
    String stockWhole;

    WholesalerDetails(String name, String stockWhole) {
        this.name = name;
        this.stockWhole = stockWhole;
    }

    WholesalerDetails() {

    }

    static void showWholesaler() {
        for (WholesalerDetails w : Management.wholesaler_products) {
            System.out.print(w.name + " " + w.stockWhole + "\n");
        }
    }
    static void updateWholesaler(String name_w,int stock_w,ArrayList<WholesalerDetails> wholesaler_products )
    {
        for(WholesalerDetails w : Management.wholesaler_products){
            if(w.name.equals(name_w))
            {
                w.stockWhole = String.valueOf(Integer.parseInt(w.stockWhole) + stock_w);
                System.out.println(w.stockWhole);
                showWholesaler();


            }
            else{
                System.out.println("Enter other product");
            }
        }

    }
}

class RetailerDetails extends ProductDetails {
    String name;
    String stockR;

    RetailerDetails(String name, String stockR) {
        this.name = name;
        this.stockR = stockR;
    }

    static void showRetailer() {
        for (RetailerDetails r : Management.retailer_products) {
            System.out.println(r.name + " " + r.stockR + "\n");
        }
    }

}

