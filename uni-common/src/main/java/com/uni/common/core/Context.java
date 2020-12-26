package com.uni.common.core;

import lombok.Getter;

@Getter
public class Context {

    private static final ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();
    private String userUid;
    private String organizationUid;

    public static Context renewContext(String userUid, String organizationUid) {
        Context context = new Context();
        context.organizationUid = organizationUid;
        context.userUid = userUid;

        contextThreadLocal.set(context);

        return context;
    }

    public static Context getContext() {
        return contextThreadLocal.get();
    }

    public static void removeContext() {
        contextThreadLocal.remove();
    }

}
