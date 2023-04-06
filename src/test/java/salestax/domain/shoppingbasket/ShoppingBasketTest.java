package salestax.domain.shoppingbasket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingBasketTest {
    @Test
    public void shouldAddItem() {
        // given
        ShoppingBasket shoppingBasket = new ShoppingBasket();

        // when
        shoppingBasket.addItem(new BasketItem("food", 1, BigDecimal.ONE, TaxRate.ZERO));

        // then
        assertThat(shoppingBasket.getItems()).hasSize(1);
    }

    @Test
    public void shouldCalculateTotalPrice() {
        // given
        ShoppingBasket shoppingBasket = new ShoppingBasket();

        // when
        String productName1 = "food";
        String productName2 = "drink";
        int quantity1 = 2;
        int quantity2 = 3;
        shoppingBasket.addItem(new BasketItem(productName1, quantity1, BigDecimal.ONE, TaxRate.ZERO));
        shoppingBasket.addItem(new BasketItem(productName2, quantity2, BigDecimal.TEN, new TaxRate(BigDecimal.ONE)));

        // then
        assertThat(shoppingBasket.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(35));
    }

    @Test
    public void shouldCalculateTaxRate() {
        // given
        ShoppingBasket shoppingBasket = new ShoppingBasket();

        // when
        String productName1 = "food";
        String productName2 = "drink";
        int quantity1 = 2;
        int quantity2 = 3;
        shoppingBasket.addItem(new BasketItem(productName1, quantity1, BigDecimal.ONE, new TaxRate(BigDecimal.ONE)));
        shoppingBasket.addItem(new BasketItem(productName2, quantity2, BigDecimal.ONE, new TaxRate(BigDecimal.TEN)));

        // then
        assertThat(shoppingBasket.getTotalTaxRate()).isEqualTo(new TaxRate(BigDecimal.valueOf(11)));
    }
}