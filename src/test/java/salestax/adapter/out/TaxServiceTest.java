package salestax.adapter.out;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TaxServiceTest {
    @Test
    public void shouldReturnZeroTaxForExemptGoods() {
        BigDecimal tax = new TaxService().getTaxForProduct("chocolate");

        assertThat(tax).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    public void shouldReturnFivePercentForImportedGoods() {
        BigDecimal tax = new TaxService().getTaxForProduct("imported chocolate");

        assertThat(tax).isEqualByComparingTo(new BigDecimal("0.05"));
    }

    @Test
    public void shouldReturnFifteenPercent() {
        BigDecimal tax = new TaxService().getTaxForProduct("imported paper");

        assertThat(tax).isEqualByComparingTo(new BigDecimal("0.15"));
    }
}