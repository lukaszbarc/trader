package pl.agileit.trader.common.model;

import java.math.BigDecimal;

import static com.google.common.base.Objects.toStringHelper;

/**
 * @author lbarc
 */
public class StockData {

    private int identity;
    private Day day;
    private Stock stock;
    private BigDecimal open;
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal close;
    private BigDecimal turnover;

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("identity", identity)
                .add("day", day)
                .add("stock", stock)
                .add("open", open)
                .add("max", max)
                .add("min", min)
                .add("close", close)
                .add("turnover", turnover)
                .toString();
    }
}
