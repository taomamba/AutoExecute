package frm.page;

import frm.util.StringUtil;


public class ViewPage {
	/**
	 * 默认每页显示记录数
	 */
	private int pageSize;

	/**
	 * 页码
	 */
	private int pageNum = 1;

	/**
	 * 记录总数
	 */
	private long total = 0;

	/**
	 * 开始记录数(新的游标起始位置)
	 */
	private int startRs = 0;

	/**
	 * 翻页的url(路径),不带参数,参数由其它属性指定
	 */
	private String url = StringUtil.EMPTY;

	/**
	 * 翻页参数,多个参数用&分隔
	 */
	private String param = StringUtil.EMPTY;

	/**
	 * 上下环境
	 */
	private String contextPath = StringUtil.EMPTY;

	/**
	 * 要执行的Object语句
	 */
	private Object object;
	
	/**
	 * 项目在服务器上的真实路径
	 */
	private String realPath;

	/**
	 * 翻页条外观模版
	 */
	private String pageTemplate;
	
	/**
	 * 翻页外观模版路径
	 */
	private String pageTemplatePath;
	
	/**
	 * 分页条可视页码1..10 11..20
	 * 如果记录总页数不大于该值，则分页全部展示，否则只展示一部分
	 */
	private int maxShowPageNum;
	
	public String getPageTemplatePath() {
		return pageTemplatePath;
	}

	public void setPageTemplatePath(String pageTemplatePath) {
		this.pageTemplatePath = pageTemplatePath;
	}

	public String getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStartRs() {
		return startRs;
	}

	public void setStartRs(int startRs) {
		this.startRs = startRs;
	}

	public int getMaxShowPageNum() {
		return maxShowPageNum;
	}

	public void setMaxShowPageNum(int maxShowPageNum) {
		this.maxShowPageNum = maxShowPageNum;
	}
}
