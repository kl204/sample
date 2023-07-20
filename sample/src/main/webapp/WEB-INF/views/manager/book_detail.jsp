<%@page import="bitedu.bipa.book.utils.DateCalculation"%>
<%@page import="bitedu.bipa.book.vo.BookCopy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
    <link href="../resources/css/basic_style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
<%
	BookCopy copy = (BookCopy)request.getAttribute("copy");
%>
<form action="" method="post" id='frm'>
    <table>
        <tr><th colspan="4" id="form">도서 상세</th></tr>
        <tr><th>구분</th><th class="data_ui" colspan="2">내용</th><th>비고</th></tr>
        <tr>
            <td>도서번호</td>
            <td colspan="2">
            	<%=copy.getBookSeq()%>
            	<input type="hidden" id="book_seq" value="<%=copy.getBookSeq()%>">
            </td>
            <td id="message"></td></tr>
       	<tr>
            <td>도서이미지</td>
            <td colspan="2">
            	<img src="/images/<%=copy.getBookImage()%>" width="300" height="200" id="preview">
            	<!-- <input type="file" name="book_image" id="up_image"> -->
            	<a href="download.do?fileName=<%=copy.getBookImage()%>">다운로드</a>
            </td>

         </tr>  
        <tr>
        	<td>ISBN</td>
        	<td colspan="2">
        		<%=copy.getIsbn()%>
        	</td>
        	<td>
        	</td>
        </tr>
        <tr>
        	<td>도서명</td>
        	<td colspan="2">
        		<%=copy.getTitle()%>
        	</td><td></td>
        </tr>
        <tr>
        	<td>저자/역자</td>
        	<td colspan="2">
        		<%=copy.getAuthor()%>
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판일</td>
        	<td colspan="2">
        		<%=DateCalculation.getDate(copy.getPublishDate())%>
        	</td>
        	<td></td>
        <tr>
        <tr>
        	<td>도서위치</td>
        	<td colspan="2">
        	<%=copy.getBookPosition().equals("BS-0001")?"일반서가":""%>
        	<%=copy.getBookPosition().equals("BS-0002")?"예약서가":""%>
        	<%=copy.getBookPosition().substring(0,3).equals("BB-")?"회원":""%>
        	</td>
        	<td></td>
        <tr>
        <tr>
        	<td>도서상태</td>
        	<td colspan="2">
        	<%=copy.getBookStaus().equals("BM-0001")?"도서대출서비스":""%>
        	<%=copy.getBookStaus().equals("BM-0002")?"도서수선":""%>
        	<%=copy.getBookStaus().equals("BM-0003")?"도서보관":""%>
        	</td>
        	<td></td>
        <tr>
        <tr>
        	<td colspan="4" id="sending">
        		<input type="submit" value="수정화면으로" id="go_view_update"> 
        		<input type="submit" value="도서리스트" id="go_book_list"> 
        	</td>
        </tr>
    </table>
</form>
	<script type="text/javascript" src="../resources/js/book.js"></script>
</body>
</html>