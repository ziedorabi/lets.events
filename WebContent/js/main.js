$(document).ready(function(){


	var down = false;

	$('#bell').click(function(e){

	var color = $(this).text();
	if(down){

	$('#box').css('height','0px');
	$('#box').css('opacity','0');
	down = false;
	}else{

	$('#box').css('height','auto');
	$('#box').css('opacity','1');
	down = true;

	}

	});

	});
	
	
	$(document).ready(function(){  
      var i=1;  
      $('#add').click(function(){  
           i++;  
           $('#dynamic_field').append('<div id="row'+i+'"><td><input style="margin-top: 0.75rem ;" type="text" id="search" class="new-guest" name="guest[]" placeholder="Choisissez un invité" class="name_list"/></td><td><button type="button" name="remove" id="'+i+'" class="btn btn-danger btn_remove">X</button></td></div>');  
      });  
      $(document).on('click', '.btn_remove', function(){  
           var button_id = $(this).attr("id");   
           $('#row'+button_id+'').remove();  
      });  
 });  
 

 $(document).ready(function() {

	$('#search').autocomplete({
		source: 'searchuser'
	});

});
 $(document).ready(function(){
 
 function load_unseen_notification(view = '')
 {
  $.ajax({
   url:"fetch",
   method:"GET",
   data:{view:view},
   dataType:"json",
   success:function(data)
   {
    $('.dropdown-menu1').html(data.notification);
    if(data.unseen_notification > 0)
    {
     $('.count').html(data.unseen_notification);
    }
   }
  });
 }
 
 load_unseen_notification();
 
 $('#comment_form').on('submit', function(event){
  event.preventDefault();
  if($('#subject').val() != '' && $('#comment').val() != '')
  {
   var form_data = $(this).serialize();
   $.ajax({
    url:"",
    method:"GET",
    data:form_data,
    success:function(data)
    {
     $('#comment_form')[0].reset();
     load_unseen_notification();
    }
   });
  }
  else
  {
   alert("Both Fields are Required");
  }
 });
 
 $(document).on('click', '.dropdown-toggle1', function(){
  $('.count').html('');
  load_unseen_notification('yes');
 });
 
 setInterval(function(){ 
  load_unseen_notification();; 
 }, 5000);
 
});