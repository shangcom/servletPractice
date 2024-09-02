<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    <li>id= <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li>username= <%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li>age= <%=((Member)request.getAttribute("member")).getAge()%></li>--%>

    <%--EL은 request.getAttribute("member")를 호출하지 않아도, request 스코프에 저장된 "member" 객체를 자동으로 인식.--%>
    <%--EL은 인식된 객체를 자동으로 적절한 타입으로 캐스팅하고, 속성에 접근하기 위해 해당 객체의 메서드를 자동으로 호출.
        ${member.id}는 member 객체의 getId() 메서드를 호출하여 값을 가져옴.--%>
    <li>id= ${member.id}</li>
    <li>username= ${member.username}</li>
    <li>age= ${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
