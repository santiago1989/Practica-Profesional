package com.gestor.backend.dto;

import java.io.InputStream;

import com.gestor.common.enums.ContentType;

public class ReportResult {
	
	private InputStream inputStream;
	
	private ContentType contentType;
	
	public ReportResult(InputStream inputStream, ContentType contentType) {
		super();
		this.inputStream = inputStream;
		this.contentType = contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
}
