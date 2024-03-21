package ru.golovin.springalgrang.spam.log.simple;

import ru.golovin.springalgrang.spam.Spammer;
import ru.golovin.springalgrang.spam.log.Log;
import ru.golovin.springalgrang.spam.log.LogFactory;

public class LogSimpleSpammer implements Spammer {

    private static final Log log = LogFactory.out.get(LogSimpleSpammer.class);

    @Override
    public void spam(int count) {
        for (int i = 0; i < count; i++) {
            log.info("");
        }
    }
}
