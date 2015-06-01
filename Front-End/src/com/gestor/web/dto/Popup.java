package com.gestor.web.dto;

import com.gestor.web.enums.PopupType;

public class Popup {
	
	private String description;
	
	private PopupType type;

	public Popup(String description, PopupType type) {
		super();
		this.description = description;
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type.getDescription();
	}

	public void setType(PopupType type) {
		this.type = type;
	}
}
