package com.cnews.guji.smart.common.net;


import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.util.NetWorkUtils;

/**
 * 网络异常
 */

public class ErrorHanding {
    public ErrorHanding() {
    }

    public static String handleError(Throwable throwable) {
        throwable.printStackTrace();
        String message;
        if (!NetWorkUtils.isNetworkAvailable(BaseApplication.getAppContext())) {
            message = "无网络连接";
        } else if (throwable instanceof ServerException) {
            message = throwable.getMessage();
        } else {
            message = "连接服务器失败";
        }
        return message;
    }

    private class ServerException extends Exception {
        public ServerException(String msg) {
            super(msg);
        }
    }
}
