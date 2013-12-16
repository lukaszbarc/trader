package com.agileit.trader.quote.fetcher.batch.job;

import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Map;

/**
 * @author lbarc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:quotes-fetcher-batch-config.xml",
        "classpath:quotes-fetcher-batch-test-context.xml"})
public class TestJobs {

    private static final Logger LOG = Logger.getLogger(TestJobs.class);

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void launchJob() throws Exception {
        LOG.debug("Start test");
        Job job = jobLauncherTestUtils.getJob();

        Map<String, JobParameter> map = Maps.newHashMap();
        String date = "2013-12-04";
        map.put("url", new JobParameter("http://www.gpw.pl/notowania_archiwalne?type=10&date=" + date + "&fetch.x=19&fetch.y=15"));
        map.put("xls.file", new JobParameter(System.getProperty("java.io.tmpdir") + "trader" + File.separator + "xls" + File.separator + date + ".xls"));
        map.put("xls.resource", new JobParameter("file:" + System.getProperty("java.io.tmpdir") + "trader" + File.separator + "xls" + File.separator + date + ".xls"));
        JobParameters parameters = new JobParameters(map);
        JobExecution run = jobLauncherTestUtils.getJobLauncher().run(job, parameters);
    }
}
