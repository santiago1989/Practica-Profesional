package com.gestor.common.enums;

public enum ContentType {
	
	TXT(".txt","text/plain"),
	XLS(".xls","application/vnd.ms-excel"),
	PDF(".pdf","application/pdf"),
	DOC(".doc","application/msword"),
	PNG(".png","image/png"),
	JPG(".jpg","image/jpeg");
	
	
	private String description;

	private String mimeType;
	
	private ContentType(String description,String mimeType){
		this.description = description;
		this.mimeType = mimeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public static ContentType lookUp(String extension){
		for (ContentType contentType : ContentType.values()) {
			if(contentType.getDescription().endsWith(extension)){
				return contentType;
			}
		}
		return null;
	}
}
