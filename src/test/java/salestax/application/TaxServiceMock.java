package salestax.application;

import salestax.port.out.TaxPort;

import java.math.BigDecimal;

public class TaxServiceMock implements TaxPort {
    @Override
    public BigDecimal getTaxForProduct(String productName) {
        return new BigDecimal("0.1");
    }
}
