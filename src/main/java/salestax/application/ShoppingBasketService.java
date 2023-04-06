package salestax.application;

import salestax.domain.shoppingbasket.BasketItem;
import salestax.domain.shoppingbasket.ShoppingBasket;
import salestax.domain.shoppingbasket.TaxRate;
import salestax.port.in.ShoppingBasketPort;
import salestax.port.out.TaxPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingBasketService implements ShoppingBasketPort {
    private final TaxPort taxPort;

    public ShoppingBasketService(TaxPort taxPort) {
        this.taxPort = taxPort;
    }

    public ShoppingBasket createShoppingBasket(List<String> shoppingBasketItems) {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasketItems.stream().map(this::toBasketItem).forEach(shoppingBasket::addItem);
        return shoppingBasket;
    }

    private BasketItem toBasketItem(String line) {
        Pattern pattern = Pattern.compile("(\\d*?) ([\\w\\s]*?) at (\\d*\\.\\d*)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find() ) {
            if (matcher.group(1) == null || matcher.group(2) == null || matcher.group(3) == null) {
                throw new IllegalArgumentException("ShoppingBasket not in right format");
            }
            int quantity = Integer.parseInt(matcher.group(1));
            String productName = matcher.group(2);
            BigDecimal price = new BigDecimal(matcher.group(3));
            BigDecimal taxForProduct = taxPort.getTaxForProduct(productName);
            return new BasketItem(productName, quantity, price, new TaxRate(price.multiply(taxForProduct)));
        }
        throw new IllegalArgumentException("ShoppingBasket not in right format");
    }

}
