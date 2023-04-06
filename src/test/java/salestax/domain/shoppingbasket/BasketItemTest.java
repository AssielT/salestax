package salestax.domain.shoppingbasket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BasketItemTest {
    @Test
    public void shouldCreateBasketItem() {
        String productName = "food";
        int quantity = 2;
        new BasketItem(productName, quantity, BigDecimal.ONE, new TaxRate(BigDecimal.ONE));
    }

    @Test
    public void shouldCalculateTotalPrice() {
        String productName = "food";
        int quantity = 2;
        BasketItem basketItem = new BasketItem(productName, quantity, BigDecimal.ONE, new TaxRate(BigDecimal.ONE));

        assertThat(basketItem.getTotalPrice()).isEqualByComparingTo(new BigDecimal(4));
    }

    @Test
    public void shouldNotAllowEmptyProductName() {
        String productName = "";
        int quantity = 2;

        assertThatThrownBy(() -> new BasketItem(productName, quantity, BigDecimal.ONE, new TaxRate(BigDecimal.ONE))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotAllowNullAsProductName() {
        String productName = null;
        int quantity = 2;

        assertThatThrownBy(() -> new BasketItem(productName, quantity, BigDecimal.ONE, new TaxRate(BigDecimal.ONE))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotAllowNegativeQuantity() {
        String productName = "product";
        int quantity = -1;

        assertThatThrownBy(() -> new BasketItem(productName, quantity, BigDecimal.ONE, new TaxRate(BigDecimal.ONE))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotAllowZeroQuantity() {
        String productName = "product";
        int quantity = 0;

        assertThatThrownBy(() -> new BasketItem(productName, quantity, BigDecimal.ONE, new TaxRate(BigDecimal.ONE))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotAllowNegativePrice() {
        String productName = "product";
        int quantity = -1;
        BigDecimal price = new BigDecimal("-1");

        assertThatThrownBy(() -> new BasketItem(productName, quantity, price, new TaxRate(BigDecimal.ONE))).isInstanceOf(IllegalArgumentException.class);
    }
}