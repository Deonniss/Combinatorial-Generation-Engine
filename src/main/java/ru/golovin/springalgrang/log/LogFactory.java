package ru.golovin.springalgrang.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum LogFactory {

    out("out");

    final Logger logger;

    LogFactory(String name) {
        logger = LoggerFactory.getLogger(name);
    }

    public Log get(String tag) {
        return new Log(tag, logger);
    }

    public Log get(Class clazz) {
        return get(clazz.getSimpleName());
    }
}
