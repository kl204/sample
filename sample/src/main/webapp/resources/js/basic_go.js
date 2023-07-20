/**
 * 
 */
	$(document).ready(function(){
		
		$('#go_view_update').on('click',function(){
			alert($('#book_seq').val());
			$('#frm').attr('action','view_update.do?bookSeq='+$('#book_seq').val());
			$('#frm').submit();
		});
		
		$('#go_book_list').on('click',function(){
			alert('go');
			$('#frm').attr('action','list.do');
			$('#frm').attr('method','get');
			$('#frm').submit();
		});
		
		$('#go_book_regist').on('click',function(){
			alert($('#publish_date').val());
			$('#frm').attr('action','regist.do');
			$('#publish_date').val($('#publish_date').val()+" 00:00:00");
			$('#frm').submit();
		});
		
		
		
		$('#go_book_update').on('click',function(){
			$('#frm').attr('action','update.do?bookSeq='+$('#book_seq').val());
			$('#publish_date').val($('#publish_date').val()+" 00:00:00");
			$('#frm').submit();
		});
		
	});
		
