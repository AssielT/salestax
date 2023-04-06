package salestax.domain.shoppingbasket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class TaxRateTest {

    @Test
    public void shouldRoundToNearestFiveCent() {
        TaxRate taxRate = new TaxRate(new BigDecimal("0.011"));

        assertThat(taxRate.rate()).isEqualByComparingTo(new BigDecimal("0.05"));
    }

    @Test
    public void shouldNotAllowNegativeTax() {
        assertThatThrownBy(() -> new TaxRate(new BigDecimal("-0.01"))).isInstanceOf(IllegalArgumentException.class);
    }
}