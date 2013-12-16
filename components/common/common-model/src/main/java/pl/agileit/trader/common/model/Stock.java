package pl.agileit.trader.common.model;

import static com.google.common.base.Objects.toStringHelper;

/**
 * @author lbarc
 */
public class Stock {

    private int identity;
    private String symbol;


    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("identity", identity)
                .add("symbol", symbol)
                .toString();
    }
}
