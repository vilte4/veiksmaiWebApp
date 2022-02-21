<%@ include file="common/navigation.jspf"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <p>Add new Veiksmas:</p>
    <form:form method="post" modelAttribute="veiksmas">

        <form:input path="ID" type="hidden" required="required" />
        <form:errors path="ID" />

        <form:label path="reiksme">Veiksmas</form:label>
        <form:input path="reiksme" type="text" required="required" />
        <form:errors path="reiksme" />

        <form:label path="vartotojoID">Vartotojo ID</form:label>
        <form:input path="vartotojoID" type="text" required="required" />
        <form:errors path="vartotojoID" />

        <form:label path="date">Data</form:label>
        <form:input path="date" type="text" required="required" />
        <form:errors path="date"/>

        <button type="submit">OK</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>