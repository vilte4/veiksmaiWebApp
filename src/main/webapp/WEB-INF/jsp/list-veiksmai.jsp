<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <H1>List of Veiksmai:</H1>
    <!--
<p>${veiksmai}</p>
-->
    <table border="1">
        <caption>Veiksmai</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Reiksme</th>
            <th>VartotojoID</th>
            <th>Vardas</th>
            <th>Data</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${veiksmai}" var="veiksmas">
            <tr>
                <td>${veiksmas.ID}</td>
                <td>${veiksmas.reiksme}</td>
                <td>${veiksmas.vartotojoID}</td>
                <c:forEach items="${vartotojai}" var="vartotojas">
                    <c:if test="${vartotojas.ID == veiksmas.vartotojoID}">
                        <td>${vartotojas.vardas}</td>
                    </c:if>
                </c:forEach>
                <td>${veiksmas.date}</td>
                <td><a type="button" href="/update-veiksmas/${veiksmas.ID}">UPDATE</a></td>
                <td><a type="button" href="/delete-veiksmas/${veiksmas.ID}">DELETE</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
        <a href="add-veiksmas">ADD Veiksmai</a>
    </div>
</div>
<%@ include file="common/footer.jspf"%>