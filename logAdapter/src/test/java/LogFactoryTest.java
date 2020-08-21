import com.ninjutsu.loggingAdapter.ILogAdapter;
import com.ninjutsu.loggingAdapter.LogException;
import com.ninjutsu.loggingAdapter.LogFactory;
import com.ninjutsu.loggingAdapter.commons.JakartaCommonsLoggingImpl;
import com.ninjutsu.loggingAdapter.jdk14.Jdk14LoggingImpl;
import com.ninjutsu.loggingAdapter.log4j.Log4jImpl;
import com.ninjutsu.loggingAdapter.stdout.StdOutImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogFactoryTest {

    @Test
    public void shouldUseCommonsLogging() throws LogException {
        ILogAdapter log = LogFactory.getLog(JakartaCommonsLoggingImpl.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), JakartaCommonsLoggingImpl.class.getName());
    }

    @Test
    public void shouldUseJDK14Logging() throws LogException {
        ILogAdapter log = LogFactory.getLog(Jdk14LoggingImpl.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), Jdk14LoggingImpl.class.getName());
    }

    @Test
    public void shouldUseLog4jImplLogging() throws LogException {
        ILogAdapter log = LogFactory.getLog(Log4jImpl.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), Log4jImpl.class.getName());
    }

    @Test
    public void shouldStdOutImplImplLogging() throws LogException {
        ILogAdapter log = LogFactory.getLog(StdOutImpl.class);
        logSomething(log);
        assertEquals(log.getClass().getName(), StdOutImpl.class.getName());
    }

    private void logSomething(ILogAdapter log) {
        log.warn("Warning message.");
        log.debug("Debug message.");
        log.error("Error message.");
        log.error("Error with Exception.", new Exception("Test exception."));
    }
}
