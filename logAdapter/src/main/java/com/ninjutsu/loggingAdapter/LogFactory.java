package com.ninjutsu.loggingAdapter;

import com.ninjutsu.loggingAdapter.commons.JakartaCommonsLoggingImpl;
import com.ninjutsu.loggingAdapter.jdk14.Jdk14LoggingImpl;
import com.ninjutsu.loggingAdapter.log4j.Log4jImpl;
import com.ninjutsu.loggingAdapter.nologging.NoLoggingImpl;
import com.ninjutsu.loggingAdapter.stdout.StdOutImpl;

import java.util.HashMap;
import java.util.Map;

public class LogFactory {
    private static Map<String,ILogAdapter> adapterMap = new HashMap<String,ILogAdapter>();

    static {
        adapterMap.put(JakartaCommonsLoggingImpl.class.getName(),new JakartaCommonsLoggingImpl(JakartaCommonsLoggingImpl.class.getName()));
        adapterMap.put(Jdk14LoggingImpl.class.getName(),new Jdk14LoggingImpl(Jdk14LoggingImpl.class.getName()));
        adapterMap.put(Log4jImpl.class.getName(),new Log4jImpl(Log4jImpl.class.getName()));
        adapterMap.put(NoLoggingImpl.class.getName(),new NoLoggingImpl(NoLoggingImpl.class.getName()));
        adapterMap.put(StdOutImpl.class.getName(),new StdOutImpl(StdOutImpl.class.getName()));
    }

    private LogFactory() {
        // disable construction
    }

    public static ILogAdapter getLog(Class<?> clazz) throws LogException {
        return getLog(clazz.getName());
    }

    public static ILogAdapter getLog(String logger) throws LogException {
        try {
            return adapterMap.get(logger);
        } catch (Throwable t) {
            throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
        }
    }
}
