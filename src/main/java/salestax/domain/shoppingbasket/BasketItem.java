package salestax.domain.shoppingbasket;

import java.math.BigDecimal;

public class BasketItem {
    private final String productName;

    private final int quantity;

    private final BigDecimal basePrice;

    private final TaxRate taxrate;

    public BasketItem(String productName, int quantity, BigDecimal basePrice, TaxRate taxrate) {
        if(productName == null || productName.isEmpty()){
            throw new IllegalArgumentException("productname must not be empty or null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }

        if (basePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        this.productName = productName;
        this.quantity = quantity;
        this.basePrice = basePrice;
        this.taxrate = taxrate;
    }

    public BigDecimal getTotalPrice() {
        return getBasePrice().
                add(getTaxrate().rate())
                .multiply(BigDecimal.valueOf(quantity));
    }

    public String getProductName() {
        return productName;
    }


    public int getQuantity() {
        return quantity;
    }


    public BigDecimal getBasePrice() {
        return basePrice;
    }


    public TaxRate getTaxrate() {
        return taxrate;
    }

}
