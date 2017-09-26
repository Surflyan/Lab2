function isNum (num) {
	for (i = 0; i<num.length; i++) {
		c = num.charAt(i);
		if(c>'9' || c<'0')
			return false;
	}
	return true;
}

function check() {
	
	if (document.author.age.value == "") {
		window.alert("年龄不能为空！");
		document.author.age.focus();
		return false;
	}
	
	if (!isNum(document.author.age.value)) {
		window.alert("年龄不能为非数字！");
		document.author.age.focus();
		return false;
	}
	
	if (document.author.country.value == "") {
		window.alert("国籍不能为空！");
		document.author.country.focus();
		return false;
	}
}