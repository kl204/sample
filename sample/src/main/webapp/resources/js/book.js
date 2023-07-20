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
			$('#frm').attr('action','upload.do');
			$('#publish_date').val($('#publish_date').val()+" 00:00:00");
			$('#frm').submit();
		});
		
		
		
		$('#go_book_update').on('click',function(){
			$('#frm').attr('action','update.do');
			$('#publish_date').val($('#publish_date').val()+" 00:00:00");
			$('#frm').submit();
		});
		
		$('#up_image').on('change',function(){
			  alert('in');
			  if (this.files && this.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('preview').src = e.target.result;
			      console.log(e.target)
			    };
			    reader.readAsDataURL(this.files[0]);
			  } else {
			    document.getElementById('preview').src = "";
			  }
			});
		$('#').on('click', function(){
			
		
		});	
		
		
	});
		
