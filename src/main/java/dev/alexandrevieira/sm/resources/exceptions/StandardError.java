package dev.alexandrevieira.sm.resources.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private LocalDateTime timestamp;

	public StandardError(Integer status, String msg, LocalDateTime timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTimestamp() {
		return timestamp.toString();
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
