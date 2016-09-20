<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/jsp/common/frame_edit.jsp" flush="true">
    <jsp:param name="entity_name" value="User"/>
    <jsp:param name="command_select" value="sel_user"/>
    <jsp:param name="command_insert" value="ins_user"/>
    <jsp:param name="command_update" value="upd_user"/>
    <jsp:param name="page_fields" value="/jsp/entity-edit/users-fields.jsp"/>
</jsp:include>