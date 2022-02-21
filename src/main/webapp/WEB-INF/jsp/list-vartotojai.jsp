<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <H1>List of Vartotojai:</H1>
    <!--
<p>${vartotojai}</p>
-->
    <table border="1">
        <caption>Vartotojai</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Vardas</th>
            <th>Telefono numeris</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vartotojai}" var="vartotojas">
            <tr>
                <td>${vartotojas.ID}</td>
                <td>${vartotojas.vardas}</td>
                <td>${vartotojas.telNr}</td>
                <td><a type="button" href="/update-vartotojas/${vartotojas.ID}">UPDATE</a></td>
                <td><a type="button" href="/delete-vartotojas/${vartotojas.ID}">DELETE</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
        <a href="add-vartotojas">ADD Vartotojai</a>
    </div>
</div>
<%@ include file="common/footer.jspf"%>