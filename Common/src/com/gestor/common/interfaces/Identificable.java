package com.gestor.common.interfaces;

import com.gestor.common.dto.MailMessage;

public interface Identificable {
	Integer getId();
	void copyFrom(Object object);
	void setEstado();
	Boolean getEstadoAlta();
	MailMessage getMailMessageCreate();
	MailMessage getMailMessageUpdate();
}
