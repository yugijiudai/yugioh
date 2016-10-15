import com.yugi.logger.LogType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by Administrator on 2016/5/20.
 */
public class LogTest {

    final Logger errorLog = LogManager.getLogger(LogType.ERROR);
    final Logger sqlLog = LogManager.getLogger(LogType.SQL);
    final Logger logger = LogManager.getLogger(this.getClass());


    @Test
    public void testLog(){
//        errorLog.error("error");
//        sqlLog.debug("sql");
        logger.trace("trace");
        logger.warn("warn");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        logger.fatal("fatal");
    }

}
