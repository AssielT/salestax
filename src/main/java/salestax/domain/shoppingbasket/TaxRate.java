package salestax.domain.shoppingbasket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record TaxRate(BigDecimal rate) {

    public static final TaxRate ZERO = new TaxRate(BigDecimal.ZERO);

    public TaxRate(BigDecimal rate) {
        if (rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tax rate cannot be negative");
        }
        this.rate = roundUpToNearestFiveCent(rate);
    }

    public TaxRate add(TaxRate rate) {
        return new TaxRate(rate().add(rate.rate()));
    }

    private BigDecimal roundUpToNearestFiveCent(BigDecimal x) {
        return x.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP).divide(BigDecimal.valueOf(20)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxRate taxRate = (TaxRate) o;
        return rate.compareTo(taxRate.rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }
}
