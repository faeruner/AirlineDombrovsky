<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/jsp/common/frame_list.jsp" flush="true">
	<jsp:param name="entity_name" value="User"/>
	<jsp:param name="command_select" value="sel_user"/>
	<jsp:param name="command_delete" value="del_user"/>
	<jsp:param name="table_head" value="<th>id</th><th>Name</th><th>Surname</th><th>Login</th><th>Role</th>"/>
	<jsp:param name="page_fields" value="/jsp/entity-tables/user-row.jsp"/>
</jsp:include>