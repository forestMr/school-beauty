package edu.forest;

import java.util.logging.Logger;

/**
 * @version 1.0
 * @anthor zsl on 2019/6/11
 * @since jdk8
 */
public class LoggerUtil {
    private static Logger logger = Logger.getGlobal();

    public static Logger  loggerUtil(){
        return logger;
    }
}
