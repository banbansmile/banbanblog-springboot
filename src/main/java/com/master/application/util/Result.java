package com.master.application.util;

public class Result {

	public static final Integer SUCCEEDED = 0;
	public static final Integer FAILED = 400;
	
	private Integer code;
	private String msg;
	private Object data;
	private Long totalnum;

	public Result(Integer code) {
		this.code = code;
	}

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	public Result(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(Integer code, String msg, Object data, Long totalnum) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.totalnum = totalnum;
	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Long getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Long totalnum) {
		this.totalnum = totalnum;
	}


    public static Result failed(String msg) {
        return new Result(Result.FAILED, msg, "");
    }
	
	
}
