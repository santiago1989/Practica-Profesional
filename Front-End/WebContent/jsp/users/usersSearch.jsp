<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa/jmesa.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
    function onInvokeAction(id) {
        $.jmesa.setExportToLimit(id, '');
        $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
    }
    function onInvokeExportAction(id) {
        var parameterString = $.jmesa.createParameterStringForLimit(id);
        location.href = '${pageContext.request.contextPath}/tag.run?' + parameterString;
    }
</script>
<title>B&uacute;squeda de Usuarios</title>
</head>
<body>
     <jmesa:tableModel id="jmesaTag" items="${users}" maxRows="8" exportTypes="csv,excel" maxRowsIncrements="8,16,24" filterMatcherMap="org.jmesaweb.controller.TagFilterMatcherMap"
      stateAttr="restore" var="bean">
          <jmesa:htmlTable captionKey="presidents.caption" width="600px">		
                <jmesa:htmlRow>	
                    <jmesa:htmlColumn property="name.firstName" titleKey="presidents.firstName">
                        <a href="http://www.whitehouse.gov/history/presidents/">${bean.name.firstName}</a>
                    </jmesa:htmlColumn>
                    <jmesa:htmlColumn property="name.lastName" title="Last Name"/>
                    <jmesa:htmlColumn property="term"/>
                    <jmesa:htmlColumn property="career" filterEditor="org.jmesa.view.html.editor.DroplistFilterEditor"/>
                    <jmesa:htmlColumn property="born" pattern="MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor"/>
                </jmesa:htmlRow>
            </jmesa:htmlTable> 
      </jmesa:tableModel>
</body>
</html>