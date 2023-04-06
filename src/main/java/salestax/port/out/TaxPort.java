package salestax.port.out;

import java.math.BigDecimal;

public interface TaxPort {
    public BigDecimal getTaxForProduct(String productName);
}
