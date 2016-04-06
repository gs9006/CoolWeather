package com.coolwealther.app.util;

/*
 * 回调服务返回的结果
 */
public interface HttpCallbackListener {

   void onFinsh(String response);
   void onError(Exception e);
}
