<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buyerMain.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta charset="UTF-8">

<title>Buyer CRUD</title>
</head>
<body>

<jsp:include page="/buyer/contents.jsp" />
<jsp:include page="/buyer/footer.jsp" />

</body>
<script Type="text/javascript">
    var contentArea = $('#contentArea');
    
    $('#menu').on('click','li',function () { 
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
    });
    $('#create').on('click',function(){
        $('#myModal').modal('show');
        $('#confirmBtn').show();    
        $('#resetBtn').show().click();  
        $('#deleteBtn').hide();
        $('#updateBtn').hide();
    });
    
    $('#contentArea').on('click','tr',function () {
    	$('#myModal').modal('show');
        $('#deleteBtn').show();
        $('#updateBtn').show();
        $('#confirmBtn').hide();    
        $('#resetBtn').hide();    
            $.ajax({
                type: "get",
                url:  "<%=request.getContextPath()%>/BuyerController",
                data: "command=readOne&buyer_id="+$(this).prop("id"),
                dataType: "json",
                success: function (v) {
                	$("input[name='buyer_add1']").val(v.buyer_add1);
                    $("input[name='buyer_add2']").val(v.buyer_add2);
                    $("input[name='buyer_bank']").val(v.buyer_bank);
                    $("input[name='buyer_bankname']").val(v.buyer_bankname);
                    $("input[name='buyer_bankno']").val(v.buyer_bankno);
                    $("input[name='buyer_charger']").val(v.buyer_charger);
                    $("input[name='buyer_comtel']").val(v.buyer_comtel);
                    $("input[name='buyer_fax']").val(v.buyer_fax);
                    $("input[name='buyer_id']").val(v.buyer_id);
                    $("input[name='buyer_lgu']").val(v.buyer_lgu);
                    $("input[name='buyer_mail']").val(v.buyer_mail);
                    $("input[name='buyer_name']").val(v.buyer_name);
                    $("input[name='buyer_zip']").val(v.buyer_zip);
                }
            });
        }   
    );

    $('#readList').on('click',function () { 
        $.ajax({
            type: "get",
            url: "<%=request.getContextPath()%>/BuyerController",
            data: "command=readList",
            dataType: "json",
            success: function (response) {  
                let code = "<table id='listTB' class='table'>";
                code += "<thead class='thead-dark'> <tr><th>아이디</th><th>이름</th><th>구분</th><th>대표자</th><th>이메일</th></thead>";
                code += "<tbody>"
                $.each(response, function (i, v) { 
                     code+="<tr id="+v.buyer_id+"><td>"+v.buyer_id+"</td><td>"+v.buyer_name+"</td><td>"+v.buyer_lgu+"</td>";
                     code+="<td>"+v.buyer_charger+"</td><td>"+v.buyer_mail+"</td>";
                });
                code+="</tbody></table>"
                contentArea.html(code);
            }   
        });
        
    });
    
    $('#confirmBtn').on('click', function () {
    	var formData = $('#editForm').serialize();
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/BuyerController",
            data: formData,
            dataType: "json",
            success: function (response) {
             	alert(response.result);
             	$('#myModal').modal('hide');
            }
        });
    });
    $('#updateBtn').on('click', function () {
    	var formData = $('#editForm').serialize();
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/BuyerUpdate",
            data: formData,
            dataType: "json",
            success: function (response) {
             	alert(response.result);
             	$('#myModal').modal('hide');
            }
        });
    });
    $('#deleteBtn').on('click', function () {
    	var formData = $('#editForm').serialize();
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/BuyerDeleteController",
            data: formData,
            dataType: "json",
            success: function (response) {
            	alert(response.result);
            	$('#myModal').modal('hide');
            }
        });
    });
</script>
</html>