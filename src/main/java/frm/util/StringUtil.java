package frm.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
	public static final String EMPTY = "";
	public static final String COMM = ",";
	public static final String UNDERLINED = "_";
	public static final String VERTICAL_LINE = "|";
	public static final String HORIZONTAL_LINE = "-";
	public static final String QUESTION = "?";
	public static final String EXCLAMATION = "!";
	public static final String DOLLAR = "$";
	public static final String SEM = ";";
	public static final String SPACER = " ";
	public static final String COLON = ":";
	public static final String COLON_CN = "：";
	public static final String DOT = ".";
	public static final String DUN = "、";
	public static final String UPDUN = "`";
	public static final String ELLIPSIS = "...";
	public static final String EQ = "=";
	public static final String L_BRACKET = "[";
	public static final String R_BRACKET = "]";
	public static final String L_BRACE = "{";
	public static final String R_BRACE = "}";
	public static final String L_BRACK = "(";
	public static final String R_BRACK = ")";
	public static final String DOUBLEQUOTE = "\"";
	public static final String SINGLEQUOTE = "'";
	public static final String URL_SEPARATOR = "/";
	public static final String D_URL_SEPARATOR = "//";
	public static final String HTML_BR = "<br />";
	public static final String HTML_AND = "&";
	public static final String HTML_SPACER = "&nbsp;";
	public static final String GT = ">";
	public static final String PERCERT = "%";
	public static final String PERCERT_COMM = "%,";
	public static final String COMM_PERCERT = ",%";
	public static final String COMM_IN_QUOTE = "','";
	public static final String JIN = "#";
	public static final String START = "*";
	
	public static final String HAN_VERTICAL_LINE = "┃";
	public static final String HAN_SPACER = "　";
	
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String DAY = "day";
	public static final String HAN_AGE_YEAR = "岁";
	public static final String HAN_AGE_MONTH = "月";
	public static final String HAN_AGE_DAY = "日";
	
	public static final String FTP_PASV = "PASV"; //ftp被动模式
	public static final String FTP_PORT = "PORT"; //ftp主动模式
	
	public static final String FILE_SEPARATOR = File.separator; //系统文件分隔符
	public static final String OS_NAME = System.getProperty("os.name").toLowerCase();
	public static final boolean IS_WINDOWS_OS = (OS_NAME.indexOf("win") != -1);
	public static final boolean IS_LINUX_OS = (OS_NAME.indexOf("nux") != -1);
	public static final boolean IS_UNIX_OS = (OS_NAME.indexOf("nix") != -1);
	public static final String WINDOWS_FILE_SEPARATOR = "\\";
	public static final String LINUX_FILE_SEPARATOR = "/";
	public static final char WINDOWS_FILE_SEPARATOR_CHAR = '\\';
	public static final char LINUX_FILE_SEPARATOR_CHAR = '/';
	
	public static String[] getStringtoArray(String inputValue, String delim) {
		if (null == inputValue)
			inputValue = EMPTY;
		inputValue = inputValue.trim();// very important
		java.util.StringTokenizer t = new java.util.StringTokenizer(inputValue,
				delim);
		String[] ret = new String[t.countTokens()];
		int index = 0;
		while (t.hasMoreTokens()) {
			String token = t.nextToken().trim();
			// check for valid value here if needed
			ret[index] = token;
			index++;
		}
		return ret;
	}

	public static Long[] getStringtoLongArray(String inputValue, String delim) {
		if (null == inputValue)
			return null;
		
		inputValue = inputValue.trim();
		java.util.StringTokenizer t = new java.util.StringTokenizer(inputValue,
				delim);
		Long[] ret = new Long[t.countTokens()];
		int index = 0;
		while (t.hasMoreTokens()) {
			String token = t.nextToken().trim();
			try {
				ret[index] = Long.valueOf(token);
			} catch (NumberFormatException e) {
				ret[index] = null;
			}
			index++;
		}
		return ret;
	}

	public static Integer[] getStringtoIntegerArray(String inputValue, String delim) {
		if (null == inputValue)
			return null;
		inputValue = inputValue.trim();
		java.util.StringTokenizer t = new java.util.StringTokenizer(inputValue,
				delim);
		Integer[] ret = new Integer[t.countTokens()];
		int index = 0;
		while (t.hasMoreTokens()) {
			String token = t.nextToken().trim();
			try {
				ret[index] = Integer.valueOf(token);
			} catch (NumberFormatException e) {
				ret[index] = null;
			}
			index++;
		}
		return ret;
	}
	
	public static int[] getStringtoIntArray(String inputValue, String delim) {
		if (null == inputValue)
			return null;
		inputValue = inputValue.trim();
		java.util.StringTokenizer t = new java.util.StringTokenizer(inputValue,
				delim);
		int[] ret = new int[t.countTokens()];
		int index = 0;
		while (t.hasMoreTokens()) {
			String token = t.nextToken().trim();
			try {
				ret[index] = Integer.parseInt(token);
			} catch (NumberFormatException e) {
				ret[index] = -99;
			}
			index++;
		}
		return ret;
	}

	public static String getStringfromArray(Object[] inputArray, String delim) {
		if(null == delim) {
			delim = COMM;
		}
		StringBuffer ret = new StringBuffer();
		if (null == inputArray) {
			return ret.toString();
		}
		
		if (inputArray.length <= 0) {
			return ret.toString();
		}

		for (int i = 0, n = inputArray.length; i < n; i++) {
			ret.append(delim).append(inputArray[i]);
		}
		
		if(ret.length() >1) {
			return ret.substring(delim.length());
		} else {
			return ret.toString();
		}
	}

	public static String getStringfromArrayExcludeNull(String[] inputArray, String delim) {
		StringBuffer ret = new StringBuffer();
		if (null == inputArray) {
			return ret.toString();
		}
		
		if (inputArray.length <= 0) {
			return ret.toString();
		}

		for (int i = 0, n = inputArray.length; i < n; i++) {
			if (null == inputArray[i] || inputArray[i].length() == 0) {
				continue;
			}
			ret.append(delim).append(inputArray[i]);
		}
		
		if(ret.length() >1) {
			return ret.substring(delim.length());
		} else {
			return ret.toString();
		}
	}

	public static String getEmptyStringIfNull(String str) {
		if ( null == str) {
			return EMPTY;
		}
		return str;
	}

	/**
	 * @todo: use StringBuffer accept name with char, number or '_' or '.'
	 */
	public static void checkGoodName(String str) {
		byte[] s = str.getBytes();
		int length = s.length;
		byte b = 0;

		for (int i = 0; i < length; i++) {
			b = s[i];
			// if ((b >= 'a') && (b <= 'z')) {
			// lower char
			// } else if ((b >= 'A') && (b <= 'Z')) {
			// upper char
			// } else if ((b >= '0') && (b <= '9') && (i != 0)) {
			// numeric char
			// } else
			if (((b == '&') || (b == '$') || (b == '%') || (b == ' ') || (b == '"'))
					&& (i == 0)) {
				// not good char, throw an BadInputException
			} else {

			}
		}// for
	}

	public static String replace(String input, char from, String to) {
		if (null == input) {
			return null;
		}

		char[] s = input.toCharArray();
		int length = s.length;
		StringBuffer ret = new StringBuffer(length * 2);

		for (int i = 0; i < length; i++) {
			if (s[i] == from) {
				ret.append(to);
			} else {
				ret.append(s[i]);
			}
		}// for
		return ret.toString();
	}

	/*
	 * public static String replace(String input, String from, String to) { if
	 * (null == input) { return null; } return null; }
	 */

	/**
	 * This method can be replaced by getStringArray
	 */
	public static Collection<String> getSeparateString(String strContent, String pattern) {
		int beginIndex = 0;
		Collection<String> coResult = new ArrayList<String>();
		String result;
		int position = strContent.indexOf(pattern, beginIndex);
		while (position != -1) {
			result = strContent.substring(beginIndex, position);
			if (result.trim().length() > 0) {
				coResult.add(result);
			}
			beginIndex = position + pattern.length();
			position = strContent.indexOf(pattern, beginIndex);
		}

		return coResult;
	}

	public static String replaceFirst(String str, String replaceStr, String newStr) {
		if(replaceStr.equals(newStr)) {
			return str;
		}
		int side = -1;
		StringBuffer sb = new StringBuffer();
		if ((side = str.indexOf(replaceStr)) != -1) {
			sb.setLength(0);
			sb.append(str.substring(0, side)).append(newStr).append(str.substring(side + replaceStr.length(), str.length()));
			str = sb.toString();
		}
		return str;
	}
	
	public static String replaceAll(String str, String replaceStr, String newStr) {
		if(replaceStr.equals(newStr)) {
			return str;
		}
		int side = -1;
		StringBuffer sb = new StringBuffer();
		while ((side = str.indexOf(replaceStr)) != -1) {
			sb.setLength(0);
			sb.append(str.substring(0, side)).append(newStr).append(str.substring(side + replaceStr.length(), str.length()));
			str = sb.toString();
		}
		return str;
	}

	public static String replaceAll(String source, String find, String replace, boolean bIgnoreCase) {
		if (null == source || source.length() == 0) {
			return EMPTY;
		}

		if (null == find || find.length() == 0) {
			return (source);
		}
		if (null == replace) {
			replace = EMPTY;
		}
		StringBuffer sb = new StringBuffer(source);
		StringBuffer mod;
		boolean bDone = false;
		int prevIndex = 0, currIndex = 0;
		if (bIgnoreCase) {
			source = source.toLowerCase();
			find = find.toLowerCase();
		}
		mod = new StringBuffer(source);
		while (!bDone) {
			if ((currIndex = mod.toString().indexOf(find, prevIndex)) != -1) {
				sb = sb.replace(currIndex, currIndex + find.length(), replace);
				mod = mod.replace(currIndex, currIndex + find.length(), replace);
				prevIndex = currIndex + replace.length();
			} else {
				bDone = true;
			}
		}
		return (sb.toString());
	}

	/**
	 * 截取字符串
	 * 
	 * @param s
	 * @param maxLen
	 * @return
	 */
	public static String getShortStringInChar(String s, int maxLen) {
		if(null == s || s.length() == 0) {
			return s;
		}
		
		byte[] bytes = null;
		try {
			bytes = s.getBytes("Unicode");
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
		
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		for (; i < bytes.length && n < maxLen; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1)
				n++; // 在UCS2第二个字节时n加1
			else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0)
					n++;
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0)
				i = i - 1;
			// 该UCS2字符是字母或数字，则保留该字符
			else
				i = i + 1;
		}

		try {
			return new String(bytes, 0, i, "Unicode");
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 截取字符串，不足的以...补足
	 * 
	 * @param s
	 * @param maxLen
	 * @return
	 */
	public static String getShortStringInCharWithComm(String s, int maxLen) {
		if(null == s || s.length() == 0) {
			return s;
		}
		
		byte[] bytes = null;
		try {
			bytes = s.getBytes("Unicode");
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
		
		String comm = EMPTY;
		
		if (bytes.length > maxLen) {
			maxLen -= 3;
			comm = ELLIPSIS;
		}
		
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		for (; i < bytes.length && n < maxLen; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1)
				n++; // 在UCS2第二个字节时n加1
			else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0)
					n++;
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0)
				i = i - 1;
			// 该UCS2字符是字母或数字，则保留该字符
			else
				i = i + 1;
		}

		try {
			return new String(bytes, 0, i, "Unicode") + comm;
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	public static String[] split(String s, String t) {
		// s:mother string,t:spliter
		// get number of substrings
		int i = 0;
		int k = 1;
		for (; i < s.length();) {
			i = s.indexOf(t, i);
			if (i == -1) {
				break;
			} else {
				i += t.length();
			}
			k++;
		}
		// get all subtrings
		String[] v = new String[k];
		i = 0;
		k = 0;
		for (; i < s.length();) {
			int h = s.indexOf(t, i);
			if (h == -1) {
				v[k] = s.substring(i);
				k++;
				break;
			} else {
				if (i == h) {
					v[k] = EMPTY;
				} else {
					v[k] = s.substring(i, h);
				}
				i = h + t.length();
				k++;
			}
		}
		if (k < v.length) {
			v[k] = EMPTY;
		}
		return v;
	}

	public static String[] split(String s, String t, int n) {
		// s:mother string,t:spliter
		// get number of substrings
		int i = 0;
		int k = 1;
		for (; i < s.length() && k < n;) {
			i = s.indexOf(t, i);
			if (i == -1) {
				break;
			} else {
				i += t.length();
			}
			k++;
		}
		// get all subtrings
		String[] v = new String[k];
		i = 0;
		k = 0;
		for (; i < s.length();) {
			if (k == n - 1) {
				v[k] = s.substring(i);
				k++;
				break;
			}
			int h = s.indexOf(t, i);
			if (h == -1) {
				v[k] = s.substring(i);
				k++;
				break;
			} else {
				if (i == h) {
					v[k] = EMPTY;
				} else {
					v[k] = s.substring(i, h);
				}
				i = h + t.length();
				k++;
			}
		}
		if (k < v.length) {
			v[k] = EMPTY;
		}
		return v;
	}

	public static String[] splitRev(String s, String t, int n) {
		// s:mother string,t:spliter
		// get number of substrings
		int i = s.length() - 1;
		int k = 1;
		for (; i >= 0 && k < n;) {
			i = s.lastIndexOf(t, i);
			if (i == -1) {
				break;
			} else {
				i--;
			}
			k++;
		}
		// get all subtrings
		String[] v = new String[k];
		i = s.length() - 1;
		k = 0;
		for (; i >= 0;) {
			if (k == n - 1) {
				v[k] = s.substring(0, i + 1);
				k++;
				break;
			}
			int h = s.lastIndexOf(t, i);
			if (h == -1) {
				v[k] = s.substring(0, i + 1);
				k++;
				break;
			} else {
				if (i == h) {
					v[k] = EMPTY;
				} else {
					v[k] = s.substring(h + t.length(), i + 1);
				}
				i = h - 1;
				k++;
			}
		}
		if (k < v.length) {
			v[k] = EMPTY;
		}
		return v;
	}

	/**
	 * 查询包含指定字符的个数
	 */
	public static int getStringNum(String s, String t) {
		int i = 0;
		int count = 0;

		while (i >= 0) {
			i = s.indexOf(t, i);
			if (i >= 0) {
				i++;
				count++;
			}
		}

		return count;
	}
	
	/**
	 * 将阿拉伯数字转成英文字母
	 * @param number
	 * @param caseType L--小写 U--大写
	 * @return
	 */
	public static String getEnglishNum(int number, char caseType) {
		switch (caseType) {
			case 'L':
				return String.valueOf((char)(96 + number));
			case 'U':
				return String.valueOf((char)(64 + number));
			default:
				return String.valueOf(number);
		}
	}
	
	public static String getChineseNum(int number, int depth) {
		if (depth < 0) {depth = 0;}
		String chinese = EMPTY;
		String src = String.valueOf(number);
		if (src.charAt(src.length() - 1) == 'l'
				|| src.charAt(src.length() - 1) == 'L') {
			src = src.substring(0, src.length() - 1);
		}

		if (src.length() > 4) {
			chinese = getChineseNum(Integer.parseInt(src.substring(0, src
					.length() - 4)), depth + 1)
					+ getChineseNum(Integer.parseInt(src.substring(
							src.length() - 4, src.length())), depth - 1);
		} else {
			char prv = 0;
			for (int i = 0; i < src.length(); i++) {
				switch (src.charAt(i)) {
				case '0':
					if (prv != '0')
						chinese = chinese + "零";
					break;
				case '1':
					chinese = chinese + "一";
					break;
				case '2':
					chinese = chinese + "二";
					break;
				case '3':
					chinese = chinese + "三";
					break;
				case '4':
					chinese = chinese + "四";
					break;
				case '5':
					chinese = chinese + "五";
					break;
				case '6':
					chinese = chinese + "六";
					break;
				case '7':
					chinese = chinese + "七";
					break;
				case '8':
					chinese = chinese + "八";
					break;
				case '9':
					chinese = chinese + "九";
					break;
				}
				prv = src.charAt(i);

				switch (src.length() - 1 - i) {
				case 1:// 十
					if (prv != '0')
						chinese = chinese + "十";
					break;
				case 2:// 百
					if (prv != '0')
						chinese = chinese + "百";
					break;
				case 3:// 千
					if (prv != '0')
						chinese = chinese + "千";
					break;

				}
			}
		}
		while (chinese.length() > 0
				&& chinese.lastIndexOf("零") == chinese.length() - 1) {
			chinese = chinese.substring(0, chinese.length() - 1);
		}
		
		if (depth == 1) {
			chinese += "万";
		} else if (depth == 2) {
			chinese += "亿";
		}
		
		if(chinese.startsWith("一十")) {chinese = chinese.substring(1);}
		return chinese;
	}
	
	public static Object[] jionTwo1mArrays(Object[] obj1, Object[] obj2) {
		if(null == obj1 && null == obj2) {
			return null;
		}
		int obj1Len = obj1==null?0:obj1.length;
		int obj2Len = obj2==null?0:obj2.length;
		
		Object[] obj = new Object[obj1Len + obj2Len];
		
		if(null != obj1) {
			for (int i = 0; i < obj1.length; i++) {
				obj[i] = obj1[i];
			}
		}
		
		if(null != obj2) {
			for (int i = 0; i < obj2.length; i++) {
				obj[obj1Len + i] = obj2[i];
			}
		}
		
		return obj;
	}
	
	public static boolean contains(String[] as, String s) {
		if(null == as || null == s) {
			return false;
		}
		for(String asn : as) {
			if(asn.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 过滤HTML标签
	 * @param s
	 * @return
	 */
	public static String filterHTMLTag(String s) {
		if(null == s || s.length() == 0) {
			return EMPTY;
		}
		return s.replaceAll("<[^<>]+>", EMPTY);
	}
	
	/**
	 * 判断字符串中是否含有中文
	 * @param text
	 * @return
	 */
	public static boolean isContainChinese(String text) {
	     Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	     Matcher m = p.matcher(text);
	     if (m.find()) {
	         return true;
	     }
	     return false;
	}
	
	/**
	 * 判断手机号
	 * @param phone
	 * @return
	 */
	public static final String MOBILE_NUMBER_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
	public static boolean isMobileNumber(String phone) {
		if(phone.length() != 11) {
			return false;
		}
		Pattern p = Pattern.compile(MOBILE_NUMBER_REGEX);
        Matcher m = p.matcher(phone);
        return m.matches();
	}
	
	public static int getIntValueOfString(String s, int offset) {
		try {
			byte[] ab = s.getBytes("ISO-8859-1");
			int i = 0;
			for(byte b : ab) {
				i += b;
			}
			
			return i + offset;
		} catch (UnsupportedEncodingException e) {
			return s.length() + offset;
		}
	}
	
	/**
	 * 把字符串的第一个字符转化为大写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharUpCase(String s) {
		if(null == s || s.length() == 0) {
			return EMPTY;
		}
		
		return s.substring(0, 1).toUpperCase() + (s.length() > 1 ? s.substring(1) : EMPTY);
	}

	/**
	 * 把字符串的第一个字符转化为小写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharLowerCase(String s) {
		if(null == s || s.length() == 0) {
			return EMPTY;
		}
		
		return s.substring(0, 1).toLowerCase() + (s.length() > 1 ? s.substring(1) : EMPTY);
	}
	
	/**
	 * 删除文件路径后的/或\
	 * @param s
	 * @return
	 */
	public static String deleteLastFileSeparator(String s) {
		if(s.length() > 1) {
			if(s.endsWith(WINDOWS_FILE_SEPARATOR) || s.endsWith(LINUX_FILE_SEPARATOR)) {
				s = s.substring(0, s.length() - 1);
				return deleteLastFileSeparator(s);
			}
		}
		return s;
	}
	
	private static char[] randomChars = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 
		                                             '4', '5', '2', '3', '6', '7', '8', '9', '0', '1', 
		                                             '6', '8', '7', '9', '2', '3', '5', '0', '1', '4' };
	
	public static String getRandomChars(int num) {
		String randomStrng = EMPTY;
		Random generator = new Random();
		for (int i = 0; i < num; i++) {
			randomStrng += randomChars[generator.nextInt(randomChars.length - 1) + 1];
		}
		
		return randomStrng;
	}
	
	public static char[] getChar(){
        char[] ac = new char[62];
        char fword = 'A';
        char mword = 'a';
        char bword = '0';
        for (int i = 0; i < 62; i++) {
            if (i < 26) {
                ac[i] = fword;
                fword++;
            }else if(i<52){
                ac[i] = mword;
                mword++;
            }else{
                ac[i] = bword;
                bword++;
            }
        }
        return ac;
    }
	
	public static String getRandomChars2(int num) {
		char[] r = getChar();
        Random rr = new Random();
        char[] pw= new char[num];
        for(int i=0;i<pw.length;i++){
            int n = rr.nextInt(62);
            pw[i]=r[n];
        }
        
        return String.valueOf(pw);
	}
	
	public static String getFormatNumStr(int num, int maxStrLen) {
		NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(maxStrLen);
        nf.setMinimumIntegerDigits(maxStrLen);
        return nf.format(num);
	}
	
	/**
	 * 获取32位UUID字符串
	 * @return
	 */
	public static String getUUID32Str() {
		return UUID.randomUUID().toString().replaceAll(HORIZONTAL_LINE, EMPTY);
	}
	
	public static String toHex(byte input[]) {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }
	
	public static String maskString(String str, int startInd, int endInd, String maskChar) {
		if(null == str || str.length() == 0) {
			return str;
		}
		
		if(startInd >= endInd) {
			return str;
		}
		
		if(startInd >= str.length() || endInd >= str.length()) {
			return str;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(str.substring(0, startInd));
		for(int i = 0, n = endInd - startInd; i < n; i++) {
			sb.append(maskChar);
		}
		sb.append(str.substring(endInd));
			
		return sb.toString();
	}
}
