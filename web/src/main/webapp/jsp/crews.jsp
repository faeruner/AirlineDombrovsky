<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/jsp/common/frame_list.jsp" flush="true">
	<jsp:param name="entity_name" value="Crew"/>
	<jsp:param name="table_head" value="<th>Attributes</th><th>Crew</th>"/>
	<jsp:param name="page_fields" value="/jsp/entity-tables/crew-row.jsp"/>
</jsp:include>
