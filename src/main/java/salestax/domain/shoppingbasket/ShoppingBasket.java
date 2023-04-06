package salestax.domain.shoppingbasket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private final List<BasketItem> items;

    public ShoppingBasket() {
        this.items = new ArrayList<>();
    }

    public void addItem(BasketItem newItem) {
        this.items.add(newItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return getItems().stream().map(BasketItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public TaxRate getTotalTaxRate() {
        return getItems().stream().map(BasketItem::getTaxrate).reduce(TaxRate.ZERO, TaxRate::add);
    }
}
