package com.bigfire.imgdown.util;

import java.util.Random;
/**
 * @ IDE    ：Eclipse
 * @ Author ：dahuo
 * @ Date   ：2017年8月14日  上午10:20:33
 * @ Addr   ：China ZhengZhou
 * @ email  ：835476090@qq.com
 */
public class RandUtil {
	/**
	 * 提供一个随机数对象
	 */
	public static Random rand = new Random();
	/**
	 * 本工具类内的错误信息，可以通过类名加本属性查看错误信息
	 */
	public static String errorMsg="";
	/**
	 * 随机获取一个大写字符A-Z
	 * @return 一个A-Z的随机大写字符
	 */
	public static String getUpChar(){
		//65-90 A-Z
		byte[] b = new byte[1];
		b[0]=(byte) (rand.nextInt(91-65)+65);
		String str = new String(b);
		return str.charAt(0)+"";
	}
	/**
	 * 随机获取多个大写字符A-Z
	 * @param length 需要的字符串长度
	 * @return 返回一个多位的随机大写字符串
	 */
	public static String getUpChar(int length){
		String string="";
		for (int i = 0; i < length; i++) {
			string+=getUpChar();
		}
		return string;
	}
	/**
	 * 获取一个随机的小写字符
	 * @return 返回随机小写字符a-z
	 */
	public static String getLowerChar(){
		byte[] b = new byte[1];
		b[0]=(byte) (rand.nextInt(123-97)+97);
		String str = new String(b);
		return str.charAt(0)+"";
	}
	/**
	 * 随机获取多个大写字符a-z
	 * @param length 需要的字符串长度
	 * @return 返回一个多位的随机大写字符串
	 */
	public static String getLowerChar(int length){
		String string="";
		for (int i = 0; i < length; i++) {
			string+=getLowerChar();
		}
		return string;
	}
	/**
	 * 获取一个由随机字母产生的字符   (不区分大小写)
	 * @return 返回一个随机字符
	 */
	public static String getChar(){
		int select=rand.nextInt(2);
		if (select==0) {
			return getUpChar()+"";
		}else {
			return getLowerChar()+"";
		}
	}
	/**
	 * 获取一个指定长度且由随机字母产生的字符串(不区分大小写)
	 * @param length 随机字符的长度
	 * @return  返回一个指定长度的字符串
	 */
	public static String getChar(int length){
		String string="";
		for (int i = 0; i < length; i++) {
			string+=getChar();
		}
		return string;
	}
	/**
	 * 获取一个随机汉字
	 * @return 返回一个随机汉字
	 */
	public static String getChnaChar() {
        String str = "";
        int hightPos; //
        int lowPos;
        hightPos = (176 + Math.abs(rand.nextInt(39)));
        lowPos = (161 + Math.abs(rand.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0)+"";
    }
	/**
	 * 获取一个指定长度的随机汉字字符串
	 * @param length 随机汉字的长度
	 * @return  返回一个指定长度的随机汉字字符串
	 */
	public static String getChnaChar(int length){
		String string="";
		for (int i = 0; i < length; i++) {
			string+=getChnaChar();
		}
		return string;
	}
	/**
	 * 获取一个0-9的随机数字符
	 * @return 一位的字符串类型的随机数字
	 */
	public static String getNum() {
		return rand.nextInt(10)+"";
	}
	/**
	 * 获取一个指定长度的随机数字符
	 * @param length 随机数的长度
	 * @return 返回一个指定长度的随机数字字符串
	 */
	public static String getNum(int length) {
		String string="";
		for (int i = 0; i < length; i++) {
			string+=getNum();
		}
		return string;
	}
	/**
	 * 快速的获取一个指定范围的随机整数 包含头，包含尾
	 * @param start 开始
	 * @param end 结尾
	 * @return  返回一个随机整数
	 */
	public static int getRangeNum(int start,int end) {
		//包含头，包含尾
		return rand.nextInt(end-start+1)+start;
	}
	/**
	 * 获取一个随机标点符号
	 * @return 返回随机标点符号字符串
	 */
	public static String getSpecialChar() {
		char[] ch={'\\','\'','.',',','?',':','"',';','`','<','>','{','}','[',']','(',')','+','-','*','/','_','=','!','@','#','$','%','^','&','~'};
		int r=rand.nextInt(ch.length);	
		return ch[r]+"";
	}
	/**
	 * 获取一个指定长度的随机特殊字符
	 * @param length 长度
	 * @return 返回一个指定长度的随机特殊字符
	 */
	public static String getSpecialChar(int length){
		String string="";
		for (int i = 0; i < length; i++) {
			string+=getSpecialChar();
		}
		return string;
	}
	/**
	 * 获取一个随机字符串可以自定义长度和类型信息， type参数错误会返回字符串-1<br>
	 * 类型说明：type: <br>0为A-Z和a-z， <br>
	 * 1为0的基础上包含数字，<br>
	 * 2为1的基础上包含特殊符号，<br>
	 * 3为2的基础上包含汉字<br>
	 * @param length 长度
	 * @param type	类型
	 * @return 返回一个随机字符串
	 */
	public static String getString(int length,int type){
		//type: 0为A-Z和a-z， 1为0的基础上包含数字，2为1的基础上包含特殊符号，3为2的基础上包含汉字
		String sb= new String();
		switch (type) {
		
		case 0:
			for (int i = 0; i < length; i++) 
				sb+=(getChar());
			break;
		case 1:
			for (int i = 0; i < length; i++) {
				if (rand.nextBoolean()) {
					sb+=(getChar());
				}else {
					sb+=(getNum());
				}
			}
			break;
		case 2:
			for (int i = 0; i < length; i++) {
				switch (rand.nextInt(3)) {
				case 0:sb+=(getChar());break;
				case 1:sb+=(getNum());break;
				default:sb+=(getSpecialChar());break;
				}
			}
			break;
		case 3:
			for (int i = 0; i < length; i++) {
				switch (rand.nextInt(4)) {
				case 0:sb+=(getChar());break;
				case 1:sb+=(getNum());break;
				case 2:sb+=(getSpecialChar());break;
				default:sb+=(getChnaChar());break;
				}
			}
			break;
		default:
			errorMsg="参数数值错误\n\n参数type说明:\n0表示只有字母\n1表示字母加数字\n2表示含有特殊字符\n3表示含有中文字符";
			return "-1";
		}
		return sb;
	}
}
