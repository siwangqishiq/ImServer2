package xyz.panyi.imserver.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void log(final String msg){
        logger.info(msg);
    }
}
