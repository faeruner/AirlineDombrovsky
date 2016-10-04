<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Error Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/css/airline.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#errorModal").modal({
                show: true
            });
        });
    </script>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="errorModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header alert alert-danger">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Error Message</h4>
            </div>
            <div class="modal-body">
                <p> Request from ${pageContext.errorData.requestURI} is failed
                    <br/>
                    Servlet name or type: ${pageContext.errorData.servletName}
                    <br/>
                    Status code: ${pageContext.errorData.statusCode}
                    <br/>
                    Exception: ${pageContext.errorData.throwable}
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
</body>
</html>