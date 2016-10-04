<jsp:useBean id="item" scope="request" type="by.pvt.module3.entity.Staff"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<td>${item.id}</td>
<td>${item.name}</td>
<td>${item.surname}</td>
<td>${item.member.name}</td>