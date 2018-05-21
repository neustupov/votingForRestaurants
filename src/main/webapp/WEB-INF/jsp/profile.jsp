<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <div class="row">
            <div class="col-5 offset-3">
                <%--@elvariable id="userTo" type="ru.javawebinar.topjava.to.UserTo"--%>
                <h3>${userTo.name} <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h3>

                <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                           action="${register ? 'register' : 'profile'}"
                           charset="utf-8" accept-charset="UTF-8">

                    <topjava:inputField labelCode='user.name' name="name"/>

                    <topjava:inputField labelCode='user.email' name="email"/>

                    <topjava:inputField labelCode='user.password' name="password" inputType="password"/>

                    <div class="text-right">
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="meal"/>
</jsp:include>
</html>