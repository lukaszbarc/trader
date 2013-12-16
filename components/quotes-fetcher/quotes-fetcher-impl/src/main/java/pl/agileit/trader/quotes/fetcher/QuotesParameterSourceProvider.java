package pl.agileit.trader.quotes.fetcher;

import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl.agileit.trader.common.database.tables.Stockdata;
import pl.agileit.trader.common.model.StockData;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lbarc
 */
public class QuotesParameterSourceProvider implements ItemSqlParameterSourceProvider<StockData> {
    @Override
    public SqlParameterSource createSqlParameterSource(StockData item) {
        return new MapSqlParameterSource(getParametersMap(item));
    }

    private Map<String, ?> getParametersMap(StockData item) {
        Map<String,Object> map = newHashMap();
        map.put(Stockdata.STOCKDATA.DAY_ID.getName(), item.getDay().getIdentity());
        map.put(Stockdata.STOCKDATA.STOCK_ID.getName(), item.getStock().getIdentity());
        map.put(Stockdata.STOCKDATA.OPEN.getName(), item.getOpen());
        map.put(Stockdata.STOCKDATA.CLOSE.getName(), item.getClose());
        map.put(Stockdata.STOCKDATA.MAX.getName(), item.getMax());
        map.put(Stockdata.STOCKDATA.MIN.getName(), item.getMin());
        map.put(Stockdata.STOCKDATA.TURNOVER.getName(), item.getTurnover());
        return map;
    }
}
