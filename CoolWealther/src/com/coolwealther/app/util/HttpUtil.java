package com.coolwealther.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;

/*
 * 从服务器获取数据的操作封装
 */
public class HttpUtil {

	public static void sendRequest(final String address,final HttpCallbackListener listener)
	{
		new Thread(new Runnable() {
			
			public void run() {
				HttpURLConnection connection=null;
				try {
					URL url=new URL(address);
					connection=(HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in=connection.getInputStream();
					BufferedReader reader=new BufferedReader(new InputStreamReader(in));
					StringBuilder response=new StringBuilder();
					String line;
					while((line=reader.readLine())!=null)
					{
						response.append(line);
					}
					if(listener!=null)
					{
						//回调onFinsh方法
						listener.onFinsh(response.toString());
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}catch (Exception e) {
					if(listener!=null){
					//回调onError方法
					listener.onError(e);
					}
				}finally{			
					if(connection!=null)
					{
						connection.disconnect();
					}
				}
				
			}
		}).start();
	}
}
