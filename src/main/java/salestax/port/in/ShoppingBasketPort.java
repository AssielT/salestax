package salestax.port.in;

import salestax.domain.shoppingbasket.ShoppingBasket;

import java.util.List;

public interface ShoppingBasketPort {

    public ShoppingBasket createShoppingBasket(List<String> shoppingBasketItems);
}
