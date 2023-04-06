package salestax.port.in;

import salestax.domain.purchase.Receipt;
import salestax.domain.shoppingbasket.ShoppingBasket;

public interface PurchasePort {
    Receipt purchaseShoppingBasket(ShoppingBasket basket);
}
