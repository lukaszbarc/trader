package pl.agileit.trader.quotes.fetcher.api;

import org.springframework.batch.item.ItemProcessor;
import pl.agileit.trader.common.model.StockData;

/**
 * @author lbarc
 */
public interface QuotesProcessor extends ItemProcessor<String[], StockData> {
}
