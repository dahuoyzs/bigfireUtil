



### 分享一下自己整理的工具类

> 很久很久之前,有时在处理一些字符串时总是不太满意,当时还没有了解到网上的一些好用的三方工具,所以就自己整理了一套工具类，虽然现在已经知道了网上很多很好用的工具类，但是由于自己封装的东西已经顺手了，所以很多时候还是用自己的.

用自己的工具类有两点好处，

* 1.体积小，没有三方依赖，

* 2.自己整理的工具类当然更熟悉怎么用，而且如果出现什么错误的时候也可以自己修改源码

#### 介绍之前，写看一下作者,之前的使用

**1.获取百度标题**

![title](img\title.png)

2.获取网页中的图片连接

![getImgs](img\getImgs.png)



好了，我们有了基础的知识，下面我们写个小demo吧

![imgdownload](img\imgdownload.png)

what???

哈哈,作者加了图形化界面而已,

对于图形化界面不在范围本工具类范围内，不过图中的小demo源码也会提供一下。



下面介绍一下工具类

StrUtil.java【推荐】//爬虫时 简单的规则可以不用正则表达式了。

```java
//获取两个指定文本中间的字符串
String getBetweenfirst(String source,String start,String end)；
//获取两个指定文本中间的字符串
String getBetween(String source,String start,String end,int index)；
//获取两个指定文本中间的全部字符串
String[] getBetweenAll(String resources,String start,String end)；
//查找一文本在另一文本中最先出现的位置，位置值从 1 开始。如果未找到，返回-1。
int findStringindex(String source,String find)；
//查找一文本在另一文本中第几次出现的位置，位置值从 1 开始。如果未找到，返回-1。
int findStringindex(String source,String find,int index)；
//统计某个字符串在源字符串中一共出现的次数
int countTextContainNumber(String source,String find)；
//把一个字符串进行MD5加密
String MD5(String source)；
//把一个字符串进行MD5加密   参数2错误 会返回-1
String MD5(String source,int digit)；
//在第一次某段字符串间插入字符串    返回插入后的文本
//批量操作，可以在多对标签中的第一对标签间插入字符串
String insertfirst(String source,String start,String end,String insert)；
//在最后一次某段字符串间插入字符串    返回插入后的文本
//批量操作，可以在多对标签中的最后一对标签间插入字符串
String insertend(String source,String start,String end,String insert)；
//批量在某个字符串后面加入一段文本
String insertAfterAll(String source,String start,String insert)；
//在指定某个字符串后面加入一段文本  多个标签时，在第几个标签后插入，
//参数num如果大于文本中start字符串出现的总次数，将返回源文本
String insertAfter(String source,String start,String insert,int num)；
//在指定两个字符串间插入一段文本，
String insert(String source,String start,String end,String insert,int num)；
//批量处理在start和end间插入一段文本。
String insertAll(String source,String start,String end,String insert)；
//在指定位置后插入一段文本
String insert(String source,int index,String insert)；
//获取第几位之后的所有文本
String getStringright(String source, int index)；
//获取前多少位的文本
String getStringleft(String source, int index)；
//获取某个字符串之前的文本
String getLeft(String source, String cut)；
//获取某个字符串之后的文本
String getRight(String source, String cut)；
//打印字符并换行
void pln(Object object)；
//打印字符
void p(Object object)；
//打印字符 并且显示打印时的行号
void print(Object object)；
```

HttpUtil.java【推荐】//短小精悍,方便实用

```java
//向指定URL发送GET方法的请求返回值是utf-8的网页源代码 or json字符串
String utf8Get(String url);
//向指定URL发送GET方法的请求返回值是gbk的网页源代码 or json字符串
String gbkGet(String url);
//utf-8格式post请求
String utf8Post(String url, String param);
//文件下载到制定目录
boolean download(String pathnet,String path);
//https协议的get请求
String httpsGet(String content);
//https协议的post请求
String httpsPost(String content);
//根据一个网页地址获取网页中的所有图片连接
String getImgsByUrl(String url);
```

RandUtil.java			//随机产生   数字，字母，汉字，等。

```java
//随机获取一个大写字符A-Z
String getUpChar();
//随机获取多个大写字符A-Z
String getUpChar(int length);
//获取一个随机的小写字符
String getLowerChar();
//随机获取多个小写字符a-z
String getLowerChar(int length);
//获取一个由随机字母产生的字符   (不区分大小写)
String getChar();
//获取一个指定长度且由随机字母产生的字符串(不区分大小写)
String getChar(int length);
//获取一个随机汉字
String getChnaChar();
//获取一个指定长度的随机汉字字符串
String getChnaChar(int length);
//获取一个0-9的随机数字符
String getNum();
//获取一个指定长度的随机数字符【可用于生成验证码】
String getNum(int length);
//获取一个指定范围的随机整数   包含头，包含尾  eg:10-1000
int getRangeNum(int start,int end);
//获取一个随机标点符号
String getSpecialChar();
//获取一个指定长度的随机特殊字符
String getSpecialChar(int length);
//获取一个随机字符串可以自定义长度和类型信息， type参数错误会返回字符串-1
String getString(int length,int type);

```

IOUtil.java 					  //文件工具类   

BeanToMap.java			//对象->Map     	Map->对象

FormatUtil.java			  //格式化工具		目前只整理的json格式化

MultipleHttp.java			//自娱自乐的一个 多线程 批量请求的工具类,



具体代码中有更详细的注释，欢迎下载学习。





