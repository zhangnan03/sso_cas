package cn.symdata.common;


public interface DataEnum {
	/**
	 *@Copyright:Copyright (c) 2012-2015
	 *@Company:zplay.cn
	 *@Title:数据状态枚举类
	 *@Description:一般删除状态置为INVALID
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月23日  下午3:11:27
	 *@Version:1.0
	 */
	enum UserStatus implements DataEnum{
		
		VALID("正常",1),
		INVALID("禁用",0);
		/** 状态代码*/
		private int statusCode;
		
		/** 状态描述*/
		private String statusDescription;
		
		
		private UserStatus( String statusDescription,int statusCode) {  
	        this.statusCode = statusCode;  
	        this.statusDescription = statusDescription;  
	    }  
		public String getStatusDescription() {
			return statusDescription;
		}
		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		// 普通方法  
	    public static String getStatusDescription(int statusCode) {  
	        for (UserStatus userStatus : UserStatus.values()) {  
	            if (userStatus.ordinal()== statusCode) {  
	                return userStatus.statusDescription;  
	            }  
	        }  
	        return null;  
	    }
	 // 普通方法  
	    public static UserStatus getEnum(int statusCode) {  
	        for (UserStatus userStatus : UserStatus.values()) {  
	            if (userStatus.ordinal() == statusCode) {  
	                return userStatus;  
	            }  
	        }  
	        return null;  
	    }
	}
	enum DeleteStatus implements DataEnum{
		DELETE("删除",1),
		NORMAL("正常",0);
		/** 状态代码*/
		private int statusCode;
		
		/** 状态描述*/
		private String statusDescription;
		
		
		private DeleteStatus( String statusDescription,int statusCode) {  
	        this.statusCode = statusCode;  
	        this.statusDescription = statusDescription;  
	    }  
		public String getStatusDescription() {
			return statusDescription;
		}
		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		// 普通方法  
	    public static String getStatusDescription(int statusCode) {  
	        for (DeleteStatus userStatus : DeleteStatus.values()) {  
	            if (userStatus.ordinal()== statusCode) {  
	                return userStatus.statusDescription;  
	            }  
	        }  
	        return null;  
	    }
	 // 普通方法  
	    public static DeleteStatus getEnum(int statusCode) {  
	        for (DeleteStatus userStatus : DeleteStatus.values()) {  
	            if (userStatus.ordinal() == statusCode) {  
	                return userStatus;  
	            }  
	        }  
	        return null;  
	    }
	}
	/**
	 *@Copyright:Copyright (c) 2012-2015
	 *@Company:symdata
	 *@Title:
	 *@Description:日志的操作类型
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月9日  下午7:53:56
	 *@Version:1.0
	 */
	enum OperatorType implements DataEnum{
		
		SAVE("保存",0),
		UPDATE("保存",1),
		DELETE("禁用",2);
		/** 状态代码*/
		private int statusCode;
		
		/** 状态描述*/
		private String statusDescription;
		
		
		private OperatorType( String statusDescription,int statusCode) {  
	        this.statusCode = statusCode;  
	        this.statusDescription = statusDescription;  
	    }  
		public String getStatusDescription() {
			return statusDescription;
		}
		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		// 普通方法  
	    public static String getStatusDescription(int statusCode) {  
	        for (OperatorType operatorType : OperatorType.values()) {  
	            if (operatorType.ordinal()== statusCode) {  
	                return operatorType.statusDescription;  
	            }  
	        }  
	        return null;  
	    }
	 // 普通方法  
	    public static OperatorType getEnum(int statusCode) {  
	        for (OperatorType operatorType : OperatorType.values()) {  
	            if (operatorType.ordinal() == statusCode) {  
	                return operatorType;  
	            }  
	        }  
	        return null;  
	    }
	}
	public enum ErrorCode implements DataEnum {

		SUCCESS("0000", "成功"), 
		ERR1001("1001", "传入参数不合法!"), 
		ERR1002("1002","非法用户!"), 
		ERR1003("1003", "非法应用!"), 
		ERR2001("2001", "系统异常!"), 
		ERR2002("2002", "应用异常!"), 
		ERR2003("2003", "未找到对象!"), 
		ERR2004("2004","数据库异常!"), 
		ERR2005("2005", "网络异常!"), 
		ERR9999("9999", "其他错误!");
		/** 状态代码 */
		private String code;

		/** 状态描述 */
		private String description;

		private ErrorCode(String code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}
}
