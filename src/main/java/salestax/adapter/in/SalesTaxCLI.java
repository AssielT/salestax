package salestax.adapter.in;

import salestax.adapter.out.TaxService;
import salestax.application.PurchaseService;
import salestax.application.ShoppingBasketService;
import salestax.domain.purchase.Receipt;
import salestax.domain.purchase.ReceiptItem;
import salestax.domain.shoppingbasket.ShoppingBasket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SalesTaxCLI {

    private final PurchaseService purchaseService;
    private final ShoppingBasketService shoppingBasketService;

    public SalesTaxCLI(PurchaseService purchaseService, ShoppingBasketService shoppingBasketService) {
        this.purchaseService = purchaseService;
        this.shoppingBasketService = shoppingBasketService;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: 1 Argument Expected (path to shopping list file) but provided " + args.length);
            System.exit(1);
        }
        try {
            new SalesTaxCLI(new PurchaseService(), new ShoppingBasketService(new TaxService())).printReceipt(args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public void printReceipt(String fileName) {
        ShoppingBasket shoppingBasket = getShoppingBasketFromFile(fileName);

        Receipt receipt = this.purchaseService.purchaseShoppingBasket(shoppingBasket);

        showReceiptOnConsole(receipt);
    }

    public ShoppingBasket getShoppingBasketFromFile(String fileName) {
        Path path = Paths.get(fileName);
        List<String> allLines;
        try {
            allLines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + fileName);
            throw new RuntimeException(e);
        }
        return shoppingBasketService.createShoppingBasket(allLines);
    }

    private void showReceiptOnConsole(Receipt receipt) {
        List<ReceiptItem> items = receipt.getItems();
        items.forEach(System.out::println);
        System.out.println("Sales Taxes: " + receipt.getTotalTax());
        System.out.println("Total: " + receipt.getTotalPrice());
    }
}
