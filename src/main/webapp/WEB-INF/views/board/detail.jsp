<%@ page language="java" contentType="text/html; charset=UTF8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>

<div class="container">
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <div>
        글 번호 : <span id="id"><i>${board.id} </i></span>
        작성자 : <span><i>${board.user.username}</i></span>
    </div>
    <br/>
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id==principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>

    <hr/>
    <div>${board.content}</div>
    <hr/>

    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}"/>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-body">
                <textarea class="form-control" id="reply-content" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" class="btn btn-primary" id="btn-reply-save">등록</button>
            </div>
        </form>
    </div>
    <br/>
    <div class="card">
        <div class="card-header">댓글리스트</div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply--1" class="list-group-item d-flex justify-content-between">
                    <div class="d-flex">
                        <div class="font-italic">${reply.user.username} &nbsp;</div>
                        <div>${reply.content}</div>
                    </div>
                    <button class="badge">delete</button>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>