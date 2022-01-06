/*function go_pop1() {
	window.open("burger.do?command=popup1", "a",
			"width=500, height=700, left=50, top=50");}
function go_pop2() {
	window.open("burger.do?command=popup2", "a",
			"width=500, height=700, left=50, top=50");}


function agree()  {
  // 선택된 목록 가져오기
  const query = 'input[name="agree"]:checked';
  const selectedEls = 
      document.querySelectorAll(query);
  
  // 선택된 목록에서 value 찾기
  let result = '';
  selectedEls.forEach((el) => {
    result += el.value + ' ';
  });
  
	if( result == "1 2 " || result=="1 2 3 4 " || result=="1 2 3 " || result=="1 2 4 " || result=="1 2 3 4 5 ")
	{	
		document.contractFrm.action ="burger.do?command=joinpage";
		document.contractFrm.submit(); 
	}
	else
	{
		alert('약관에 동의하셔야 회원 가입이 가능합니다.');
	}
}*/	

	

function go_save(){
	if (document.joinpageForm.id.value == "") {
		alert("아이디를 입력하여 주세요."); 	    
	    document.joinpageForm.id.focus();
	} else if(document.joinpageForm.name.value == "") {
	    alert("이름을 입력해 주세요.");	    
	    document.joinpageForm.name.focus();
	} else if(document.joinpageForm.phone.value == "") {
	    alert("휴대폰번호을 입력해 주세요.");	   
	    document.joinpageForm.phone.focus();
	} else if(document.joinpageForm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");	    
	    document.joinpageForm.pwd.focus();
	} else if(document.joinpageForm.pwd.value != document.joinpageForm.pwdCheck.value) {
	    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");	    
	    document.joinpageForm.pwd.focus();
	} else{
		document.joinpageForm.action ="burger.do";
		document.joinpageForm.submit(); 
	}
}