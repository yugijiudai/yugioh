import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-22
 */
@Slf4j
public class Sfl4jTest {

    @Test
    public void testLog(){
        log.info("呵呵{}", 443);
    }
}
