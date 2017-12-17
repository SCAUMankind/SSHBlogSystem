$(function(){
	var curId=$(".in").attr("id").split('-')[2];
	$(".panel-heading").click(function(){
        var id=$(this).next().attr("id").split('-')[2];
        if(id=="1"&&id!=curId)
		{
			location.href="manager.html";
			curId=1;
		}
		else if(id=="2"&&id!=curId)
		{
			location.href="blog.html";
			curId=2;
		}
		else if(id=="3"&&id!=curId)
		{
			location.href="label.html";
			curId=3;
		}
	});
	$(".panel-body ul li a").click(function(){
		var highlightId=$(this).attr("class");
		///////alert(highlightId);
		//alert(curId);
		var id=$(this).parent().parent().parent().parent().attr('id').split('-')[2];
		//alert(id);
		$("a[href='#panel-"+highlightId+"'][data-toggle='tab']").click();
	});
});