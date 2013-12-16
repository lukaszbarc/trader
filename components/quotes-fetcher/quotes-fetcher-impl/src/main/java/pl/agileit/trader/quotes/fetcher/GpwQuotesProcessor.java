package pl.agileit.trader.quotes.fetcher;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.agileit.trader.common.database.tables.records.DayRecord;
import pl.agileit.trader.common.database.tables.records.StockRecord;
import pl.agileit.trader.common.model.Day;
import pl.agileit.trader.common.model.Stock;
import pl.agileit.trader.common.model.StockData;
import pl.agileit.trader.quotes.fetcher.api.QuotesProcessor;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static pl.agileit.trader.common.database.tables.Day.DAY;
import static pl.agileit.trader.common.database.tables.Stock.STOCK;

/**
 * @author lbarc
 */
public class GpwQuotesProcessor implements QuotesProcessor, InitializingBean {

    private static final Logger LOG = Logger.getLogger(GpwQuotesProcessor.class);

    private JdbcTemplate jdbcTemplate;
    private DateFormat dateFormat;

    @Override
    public StockData process(String[] item) throws Exception {
        LOG.debug("incoming array: " + Arrays.toString(item));

        StockData stockData = new StockData();
        stockData.setStock(getStock(item[1]));
        stockData.setDay(getDay(item[0]));
        stockData.setOpen(new BigDecimal(item[4].replaceAll(",", ".")));
        stockData.setMax(new BigDecimal(item[5].replaceAll(",", ".")));
        stockData.setMin(new BigDecimal(item[6].replaceAll(",", ".")));
        stockData.setClose(new BigDecimal(item[7].replaceAll(",", ".")));
        stockData.setTurnover(new BigDecimal(item[11].replaceAll(",", ".")));

        LOG.debug(stockData);
        return stockData;
    }

    private Stock getStock(String symbol) {
        DSLContext context = DSL.using(jdbcTemplate.getDataSource(), SQLDialect.MYSQL);
        StockRecord stockRecord = context.selectFrom(STOCK).where(STOCK.SYMBOL.eq(symbol)).fetchOneInto(STOCK);
        LOG.debug("stockRecord: " + stockRecord);
        if (stockRecord == null) {
            stockRecord = context.insertInto(STOCK).set(STOCK.SYMBOL, symbol).returning(STOCK.IDENTITY).fetchOne();
        }
        LOG.debug("stockRecord: " + stockRecord);
        Stock stock = new Stock();
        stock.setIdentity(stockRecord.getIdentity());
        stock.setSymbol(symbol);
        return stock;
    }

    private Day getDay(String date) {
        DSLContext context = DSL.using(jdbcTemplate.getDataSource(), SQLDialect.MYSQL);
        try {
            Date dt = new Date(dateFormat.parse(date).getTime());
            DayRecord dayRecord = context.selectFrom(DAY).where(DAY.DATE.eq(dt)).fetchOneInto(DAY);
            LOG.debug("dayRecord: " + dayRecord);
            if (dayRecord == null) {
                dayRecord = context.insertInto(DAY).set(DAY.DATE, dt).returning(DAY.IDENTITY).fetchOne();
            }
            LOG.debug("dayRecord: " + dayRecord);
            Day day = new Day();
            day.setIdentity(dayRecord.getIdentity());
            return day;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dateFormat = new SimpleDateFormat("yyy-mm-dd");
    }
}
