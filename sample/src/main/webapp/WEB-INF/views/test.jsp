<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		console.log(new Date(2010-10-5));
		const bookData = {
			bookSeq : 54,
			isbn : 'abcd15',
			title : 'test21',
			author : 'writer2',
			publisher : '',
			publishDate : new Date('2010','9','5'),
			bookPosition : '',
			bookStatus : ''
		};
		
		$('#searchSeq').on('click',function(){
			$.ajax({
				url : '/book/restful/10',
				type : 'get',
				success : (data)=>{
					alert(JSON.stringify(data));
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				    alert(jqXHR.status);
				    alert(textStatus);
				    alert(errorThrown);
				}
			});
		});
		
		$('#insertBook').on('click',function(){
			//alert(JSON.stringify(bookData));
			$.ajax({
				url : '/book/restful/',
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify(bookData),
				success : (data)=>{
					alert(JSON.stringify(data));
				},
				error: function(jqXHR, textStatus, errorThrown) {
				    alert(jqXHR.status);
				    alert(textStatus);
				    alert(errorThrown);
				}
			}); /* */
		});
		
		$('#updateBook').on('click',function(){
			//alert(JSON.stringify(bookData));
			$.ajax({
				url : '/book/restful/',
				type : 'put',
				contentType : 'application/json',
				data : JSON.stringify(bookData),
				success : (data)=>{
					alert(JSON.stringify(data));
				}
			}); /* */
		});
		
		$('#searchAll').on('click',function(){
			$.ajax({
				url : '/book/restful/',
				type : 'get',
				success : (data)=>{
					//alert(JSON.stringify(data));
					console.log(JSON.stringify(data));
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.responseText);
				    alert(jqXHR.status);
				    alert(textStatus);
				    alert(errorThrown);
				}
			});
		});
		
		$('#deleteSeq').on('click',function(){
			$.ajax({
				url : '/book/restful/53',
				type : 'delete',
				success : (data)=>{
					alert(JSON.stringify(data));
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     }
				
			});
		});
	});
</script>
</head>
<body>
<h1>Test</h1>
<button id="searchSeq">도서검색</button>
<button id="insertBook">도서삽입</button>
<button id="updateBook">도서수정</button>
<button id="searchAll">도서전체검색</button>
<button id="deleteSeq">도서삭제</button>
</body>
</html>