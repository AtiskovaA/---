import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб ", "Яблоки ", "Молоко "};
        int[] prices = {50, 14, 80};

        Basket basket = new Basket(products, prices);

        System.out.println("Список возможных товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println("Продукт: " + products[i] + " " + prices[i] + " руб./шт.");
        }
        int sumProducts = 0;

        while (true) {
            System.out.println("Введи два числа: номер товара и количество товара или введи end: ");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            sumProducts += productCount * prices[productNumber];

            basket.addToCart(productNumber, productCount);
            basket.saveTxt(new File("basket.txt"));
        }

        //Basket basket1 = Basket.loadFromTxtFile(new File("basket.txt"));

        System.out.println();
        System.out.println("Ваша корзина: ");
        basket.printCart();
        //System.out.println("К оплате: " + sumProducts + " руб.");
    }
}

