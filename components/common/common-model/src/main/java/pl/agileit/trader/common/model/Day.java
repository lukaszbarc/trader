package pl.agileit.trader.common.model;

import java.util.Date;

import static com.google.common.base.Objects.toStringHelper;

/**
 * @author lbarc
 */
public class Day {

    private int identity;
    private Date date;

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("identity", identity)
                .add("date", date)
                .toString();
    }
}
