package com.uni.common.exception;

import com.uni.common.utils.MessageUtil;

public class UniNotFoundException extends RuntimeException {
    public UniNotFoundException(Throwable e) {
        super(e);
    }

    public UniNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public UniNotFoundException(String message) {
        super(message);
    }

    public static UniNotFoundException create(String key, Object... args) {
        return new UniNotFoundException(MessageUtil.getText(key, args));
    }

    public static UniNotFoundException create(Throwable t, String key, Object... args) {
        return new UniNotFoundException(MessageUtil.getText(key, args), t);
    }
}
