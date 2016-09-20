<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/jsp/common/frame_edit.jsp" flush="true">
    <jsp:param name="entity_name" value="Airport"/>
    <jsp:param name="command_select" value="sel_airport"/>
    <jsp:param name="command_insert" value="ins_airport"/>
    <jsp:param name="command_update" value="upd_airport"/>
    <jsp:param name="page_fields" value="/jsp/entity-edit/airport-fields.jsp"/>
</jsp:include>