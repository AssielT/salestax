package salestax.adapter.out;

import salestax.port.out.TaxPort;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TaxService implements TaxPort {
    private final List<String> exemptGoods = Arrays.asList("chocolate", "book", "pills");

    public BigDecimal getTaxForProduct(String productName) {
        BigDecimal resultRate;
        if (exemptGoods.stream().anyMatch(exemptGood -> productName.toLowerCase().contains(exemptGood))) {
            resultRate = BigDecimal.ZERO;
        } else {
            resultRate = new BigDecimal("0.10");
        }

        if (productName.toLowerCase().contains("imported")) {
            resultRate = resultRate.add(new BigDecimal("0.05"));
        }

        return resultRate;
    }
}
