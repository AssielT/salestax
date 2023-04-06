package salestax.application;

import org.junit.jupiter.api.Test;
import salestax.domain.shoppingbasket.BasketItem;
import salestax.domain.shoppingbasket.ShoppingBasket;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ShoppingBasketServiceTest {
    @Test
    public void shouldCreateShoppingBasket() {
        ShoppingBasketService service = new ShoppingBasketService(new TaxServiceMock());

        // given
        List<String> shoppingList = List.of(
                "1 book at 10.00",
                "1 music CD at 20.00",
                "1 chocolate bar at 30.00");

        // when
        ShoppingBasket shoppingBasket = service.createShoppingBasket(shoppingList);

        // then
        assertThat(shoppingBasket.getItems()).hasSize(3);
        assertThat(shoppingBasket.getItems()).extracting(BasketItem::getProductName).containsExactlyInAnyOrder("book", "music CD", "chocolate bar");
        assertThat(shoppingBasket.getTotalPrice()).isEqualByComparingTo("66");
        assertThat(shoppingBasket.getTotalTaxRate().rate()).isEqualByComparingTo("6");

    }

}