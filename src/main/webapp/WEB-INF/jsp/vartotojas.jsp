<%@ include file="common/navigation.jspf"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <p>Add new Vartotojas:</p>
    <form:form method="post" modelAttribute="vartotojas">

        <form:input path="ID" type="hidden" required="required" />
        <form:errors path="ID" />

        <form:label path="vardas">Vardas</form:label>
        <form:input path="vardas" type="text" required="required" />
        <form:errors path="vardas" />

        <form:label path="telNr">Telefono numeris</form:label>
        <form:input path="telNr" type="text" required="required" />
        <form:errors path="telNr" />

        <button type="submit">OK</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>