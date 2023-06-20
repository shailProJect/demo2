package com.example.demo.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileBean {
	private CommonsMultipartFile urlPath;
	private String text;
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CommonsMultipartFile getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(CommonsMultipartFile urlPath) {
		this.urlPath = urlPath;
	}
}
