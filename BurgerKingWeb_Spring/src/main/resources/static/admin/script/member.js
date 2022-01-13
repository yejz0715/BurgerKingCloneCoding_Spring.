function go_member_search(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminMemberList?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_member_total(){
	document.frm.key.value="";
	document.frm.action = "adminMemberList?page=1";
	document.frm.submit();
}

function del_member(){
	var count = 0;  //  체크된 체크박스의 갯수를 카운트 하기위한 변수
	if(document.frm.delete.length==undefined){   // 체크박스가 하나일때
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
		document.frm.action = "adminMemberDelete";
	    document.frm.submit();
	}
}

function go_member_update(mseq){
	var url = "adminMemberUpdateForm?mseq=" + mseq;
	document.frm.action = url;
	document.frm.submit();
}
