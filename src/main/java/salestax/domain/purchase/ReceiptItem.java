package salestax.domain.purchase;

import java.math.BigDecimal;

public class ReceiptItem {
    private final String productName;

    private final int quantity;

    private final BigDecimal price;


    public ReceiptItem(String productName, int quantity, BigDecimal price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return quantity + " " + productName + ": " + getPrice();
    }
}
