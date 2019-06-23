package com.bigfire.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class MultipleHttp
{
	String[] multRes;
	public MultipleHttp(String... urls) throws InterruptedException, ExecutionException
	{
		if (urls.length<2)
		{
			throw new RuntimeException("LinksFewExceptions\n最少2条以上请求异常");
		}
		multRes=new String[urls.length];
		double size=urls.length;
		System.out.println((int)size+"条url");
		int sqrt=(int)Math.sqrt(size);			//求开方数
		double div=size/sqrt;						//求除以开方说的详细大小
		int len=div>sqrt?sqrt+1:sqrt;				//如果div大于开方数，线程数=开方数+1，否则线程数=开方数，取大的值
		int twoArrInLen=div>len?len:sqrt;		//div大于len长度，内部数组取len，否者取开方数
		String[][] urlArrs=new String[len][twoArrInLen];

		for (int i = 0; i < urls.length; i++)
		{
			int left=i/len;
			int right=i%twoArrInLen;
			urlArrs[left][right]=urls[i];
		}
		ExecutorService pool = Executors.newFixedThreadPool(len);
		for (int i = 0; i < len; i++)
		{
			pool.submit(new MyRun(urlArrs[i], i, len));
		}
		pool.shutdown();
		while(!pool.isTerminated())
		{
		}
		ok(multRes);
	}
	public  abstract void ok(String[] multRes);
	class MyRun implements Runnable
	{
		private String[] urls;
		private int index;
		private int len;
		public MyRun(String[] urls,int index,int len)
		{
			this.urls=urls;
			this.index=index;
			this.len=len;
		}
		@Override
		public void run()
		{
			for (int i = 0; i < urls.length; i++)
			{
				if (!urls[i].equals("")&&urls!=null)
				{
					multRes[i*len+index]=utf8Get(urls[i]);
				}
			}
		}
	}
	public static String utf8Get(String url) {
      String result = "";
      BufferedReader in = null;
      try {
          String urlNameString = url;
          URL realUrl = new URL(urlNameString);
          URLConnection connection = realUrl.openConnection();
          connection.setRequestProperty("accept", "*/*");
          connection.setRequestProperty("connection", "Keep-Alive");
          connection.setRequestProperty("user-agent",
                  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
          connection.connect();
          in = new BufferedReader(new InputStreamReader(
                  connection.getInputStream(),"UTF-8"));
          String line;
          while ((line = in.readLine()) != null) {
              result += line;
          }
      } catch (Exception e) {
          System.out.println("发送GET请求出现异常！" + e);
          e.printStackTrace();
      }
      finally {
          try {
              if (in != null) {
                  in.close();
              }
          } catch (Exception e2) {
              e2.printStackTrace();
          }
      }
      return result;
  }
}
