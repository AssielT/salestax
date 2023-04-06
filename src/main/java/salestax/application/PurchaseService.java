package salestax.application;

import salestax.domain.purchase.Receipt;
import salestax.domain.purchase.ReceiptItem;
import salestax.domain.shoppingbasket.BasketItem;
import salestax.domain.shoppingbasket.ShoppingBasket;
import salestax.port.in.PurchasePort;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseService implements PurchasePort {

    public Receipt purchaseShoppingBasket(ShoppingBasket basket) {
        List<ReceiptItem> receiptItems = basket.getItems().stream().map(this::toPurchasedItems).collect(Collectors.toList());
        return new Receipt(receiptItems, basket.getTotalPrice(), basket.getTotalTaxRate().rate());
    }

    private ReceiptItem toPurchasedItems(BasketItem basketItem) {
        return new ReceiptItem(basketItem.getProductName(), basketItem.getQuantity(), basketItem.getTotalPrice());
    }
}
