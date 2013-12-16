package pl.agileit.trader.quotes.fetcher;

import org.apache.log4j.Logger;
import pl.agileit.trader.quotes.fetcher.api.QuotesDownloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;
import static org.apache.commons.io.FileUtils.copyURLToFile;

/**
 * @author lbarc
 */
public class GpwQuotesDownloader implements QuotesDownloader {

    private static final Logger LOG = Logger.getLogger(GpwQuotesDownloader.class);
    private String url;
    private String filePath;

    public void downloadData() {
        LOG.debug(">> downloadData");
        File destination = new File(filePath);
        try {
            LOG.debug(format("Download stock quotes from %s to %s", url, destination));
            copyURLToFile(new URL(url), destination);
            LOG.debug("Download completed.");
            LOG.debug(format("%s downloaded", byteCountToDisplaySize(destination.length())));
        } catch (IOException e) {
            LOG.error(e, e);
        }
        LOG.debug("<< downloadData");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
