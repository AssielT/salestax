package salestax.domain.purchase;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items;

    private final BigDecimal totalPrice;
    private final BigDecimal totalTax;

    public Receipt(List<ReceiptItem> items, BigDecimal totalPrice, BigDecimal totalTax) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }
    public BigDecimal getTotalTax() {
        return this.totalTax;
    }

}
