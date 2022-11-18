import java.io.*;
import java.sql.Struct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] counts;

    private Basket() {
    }

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.counts = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        counts[productNum] += amount;
    }

    public void printCart() {
        int sumProducts = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
            }
            int priceProduct = counts[i] * prices[i];

            System.out.println(products[i] + counts[i] + "шт. - "
                    + prices[i] + " руб./шт., " + "сумма: " + priceProduct + " руб.");
            sumProducts += prices[i] * counts[i];
        }
        System.out.println("К оплате: " + sumProducts + " руб.");
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(textFile);) {
            writer.println(products.length);

            StringJoiner joiner = new StringJoiner(" ");
            for (String product : products) {
                joiner.add(product);
            }
            String productsLine = joiner.toString();
            writer.println(productsLine);

            for (int price : prices) {
                writer.print(price + " ");
            }
            writer.println();

            for (int count : counts) {
                writer.print(count + " ");
            }
            writer.println();
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        try (Scanner scanner = new Scanner(textFile);) {
            int size = Integer.parseInt(scanner.nextLine());

            String[] products = new String[size];
            String[] ProdParts = scanner.nextLine().trim().split(" ");
            for (int i = 0; i < ProdParts.length; i++) {
                products[i] = ProdParts[i];
            }

            int[] prices = new int[size];
            String[] priParts = scanner.nextLine().trim().split(" ");
            for (int i = 0; i < priParts.length; i++) {
                prices[i] = Integer.parseInt(priParts[i]);
            }

            int[] counts = new int[size];
            String[] couParts = scanner.nextLine().trim().split(" ");
            for (int i = 0; i < couParts.length; i++) {
                counts[i] = Integer.parseInt(couParts[i]);
            }

            Basket basket = new Basket();
            basket.products = products;
            basket.prices = prices;
            basket.counts = counts;
            return basket;
        }
    }
    public void saveBin(File binFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFile))) {
            out.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File binFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFile))) {
            return (Basket) in.readObject();
        }
    }

}

