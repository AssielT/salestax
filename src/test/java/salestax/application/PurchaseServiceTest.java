package salestax.application;

import org.junit.jupiter.api.Test;
import salestax.domain.purchase.Receipt;
import salestax.domain.shoppingbasket.BasketItem;
import salestax.domain.shoppingbasket.ShoppingBasket;
import salestax.domain.shoppingbasket.TaxRate;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseServiceTest {
    @Test
    public void shouldCreatePurchaseFromShoppingBasket() {
        // given
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.addItem(new BasketItem("food", 1, BigDecimal.ONE, TaxRate.ZERO));

        // when
        Receipt receipt = new PurchaseService().purchaseShoppingBasket(shoppingBasket);

        // then
        assertThat(receipt.getItems()).hasSize(shoppingBasket.getItems().size());
        assertThat(receipt.getTotalPrice()).isEqualByComparingTo(shoppingBasket.getTotalPrice());
        assertThat(receipt.getTotalTax()).isEqualByComparingTo(shoppingBasket.getTotalTaxRate().rate());
    }

}