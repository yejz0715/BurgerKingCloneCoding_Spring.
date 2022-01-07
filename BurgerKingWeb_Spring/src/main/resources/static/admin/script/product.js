function go_wrt(){
	document.frm.action = "adminProductWriteForm";
	document.frm.submit();
}

function go_wrt2(){
	document.frm.action = "adminShortProductWriteForm";
	document.frm.submit();
}

function go_mov(){
	location.href="adminProductList";
}
function go_mov2(){
	location.href="adminShortProductList";
}

function del_product(){
	var count = 0;  //  체크된 체크박스의 갯수를 카운트 하기위한 변수
	if(document.frm.delete.length==undefined){   // 장바구니에 물건이 하나일때, 체크박스가 하나일때
		if( document.frm.delete.checked == true)   // 그 체크박스만 체크되어 있는지 확인
			count++;	 
	}else{
		for( var i=0; i<document.frm.delete.length; i++){
			if( document.frm.delete[i].checked==true)
				count++;
		}
	}
	// 지금의 스크립트 명령은 체크박스가 하나도 체크되지 않았다면 원래로 되돌아 가기위한 코드들입니다
	if( count == 0 ){
		alert("삭제할 항목을 선택해주세요");
	} else{
		document.frm.action = "adminProductDelete";
	    document.frm.submit();
	}
}

function go_save(){
	var theForm = document.frm;
	if(theForm.kind1.value==""){
		alert("상품분류를 선택하세요.");
		theForm.kind1.focus();
	}else if(theForm.pname.value==""){
		alert("상품명을 입력하세요.");
		theForm.pname.focus();
	}else if(theForm.price1.value==""){
		alert("가격을 입력하세요");
		theForm.price1.focus();
	}else if(theForm.kind2.value==""){
		alert("분류번호를 입력하세요");
		theForm.kind2.focus();
	}else if(theForm.kind3.value==""){
		alert("세부를 입력하세요");
		theForm.kind3.focus();
	}else if(theForm.content.value==""){
		alert("상품상세를 입력하세요");
		theForm.content.focus();
	}else if(theForm.image.value==""){
		alert("상품이미지를 입력하세요");
		theForm.image.focus();
	}else{
		theForm.action = "adminProductWrite?";
		theForm.submit();
	}
}

function go_save2(){
	var theForm = document.frm;
	if(theForm.kind1.value==""){
		alert("상품분류를 선택하세요.");
		theForm.kind1.focus();
	}else if(theForm.pname.value==""){
		alert("상품명을 입력하세요.");
		theForm.pname.focus();
	}else if(theForm.kind2.value==""){
		alert("분류번호를 입력하세요");
		theForm.kind2.focus();
	}else if(theForm.image.value==""){
		alert("상품이미지를 입력하세요");
		theForm.image.focus();
	}else{
		theForm.action = "adminShortProductWrite";
		theForm.submit();
	}
}

function go_detail(pseq){
	var url = "adminProductDetail?pseq=" + pseq;
	document.frm.action = url;
	document.frm.submit();
}
function go_detail2(pseq){
	var url = "adminShortProductDetail?pseq=" + pseq;
	document.frm.action = url;
	document.frm.submit();
}

function go_mod(pseq){
	var url = "adminProductUpdateForm?pseq=" + pseq;
	location.href=url;
}

function go_mod_save(){
	if(document.frm.kind1.value==""){
		alert("상품분류를 선택하세요.");
		document.frm.kind1.focus();
	}else if(document.frm.pname.value==""){
		alert("상품명을 선택하세요.");
		document.frm.pname.focus();
	}else if(document.frm.price1.value==""){
		alert("가격를 선택하세요.");
		document.frm.price1.focus();
	}else if(document.frm.kind2.value==""){
		alert("분류번호를 선택하세요.");
		document.frm.kind2.focus();
	}else if(document.frm.kind3.value==""){
		alert("세부를 선택하세요.");
		document.frm.kind3.focus();
	}else if(document.frm.content.value==""){
		alert("상품상세를 선택하세요.");
		document.frm.content.focus();
	}else{
		if(confirm('수정하시겠습니까?')){
			document.frm.action = "adminProductUpdate";
			document.frm.submit();
		}
	}
}
function go_mod_save2(k1){
	if(document.frm.kind1.value==""){
		alert("상품분류를 선택하세요.");
		document.frm.kind1.focus();
	}else if(document.frm.pname.value==""){
		alert("상품명을 선택하세요.");
		document.frm.pname.focus();
	}else if(document.frm.kind2.value==""){
		alert("분류번호를 선택하세요.");
		document.frm.kind2.focus();
	}else{
		if(confirm('수정하시겠습니까?')){
			document.frm.action = "adminShortProductUpdate?k1="+k1;
			document.frm.submit();
		}
	}
}

function go_search(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminProductList?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}
function go_search2(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminShortProductList?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_total(){
	document.frm.key.value="";
	document.frm.action = "adminProductList?page=1";
	document.frm.submit();
}
function go_total2(){
	document.frm.key.value="";
	document.frm.action = "adminShortProductList?page=1";
	document.frm.submit();
}

function go_search_order(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminOrderList?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_total_order(){
	document.frm.key.value="";
	document.frm.action = "adminOrderList?page=1";
	document.frm.submit();
}

function go_order_save(){
	var count = 0;
	if(document.frm.result.length == undefined){
		if(document.frm.result.checked == true)
			count++;
	}else{
		for(var i = 0; i < document.frm.result.length; i++){
			if(document.frm.result[i].checked == true)
				count++;
		}
	}
	
	if(count == 0){
		alert("주문처리할 항목을 선택해 주세요.")
	}else{
		document.frm.action = "adminOrderSave";
		document.frm.submit();
	}
}

function go_search_qna(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminQnaList?page=1";
	document.frm.action = url;
	document.frm.submit();
}

function go_total_qna(){
	document.frm.key.value="";
	document.frm.action = "adminQnaList?page=1";
	document.frm.submit();
}

function go_view(qseq){
	location.href = "adminQnaDetail?qseq=" + qseq;
}

function go_rep(){
	document.frm.action="adminQnaRepsave";
	document.frm.submit();
}

function selectFile(k1){
	var url = "selectimg?k1="+k1;
	var opt = "toolbar=no,menubar=no,resizable=no,width=450,height=200";
	window.open( url,'selectimg' ,  opt);
}