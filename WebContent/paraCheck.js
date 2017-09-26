function isNum (num) {
	for (i = 0; i<num.length; i++) {
		c = num.charAt(i);
		if(c !='.' && (c>'9' || c<'0'))
			return false;
	}
	return true;
}
function isISBN(isbn) {
	for (i = 0; i<isbn.length; i++) {
		c = isbn.charAt(i);
		if(c !='-' && (c>'9' || c<'0'))
			return false;
	}
	return true;
}

function check() {
	if (document.book.id.value == "") {
		window.alert("ISBN不能为空！");
		document.book.id.focus();
		return false;
	}
	
	if (!isNum(document.book.id.value)) {
		window.alert("ISBN格式错误！");
		document.book.id.focus();
		return false;
	}
	
	if (document.book.bookname.value == "") {
		window.alert("书名不能为空！");
		document.book.bookname.focus();
		return false;
	}
	if (document.book.author.value == "") {
		window.alert("作者不能为空！");
		document.book.author.focus();
		return false;
	}
	
	if (document.book.press.value == "") {
		window.alert("出版社不能为空！");
		document.book.press.focus();
		return false;
	}
	
	if (document.book.pubdate.value == "") {
		window.alert("出版日期不能为空！");
		document.book.pubdate.focus();
		return false;
	}
	
	if (document.book.price.value == "") {
		window.alert("价格不能为空！");
		document.book.price.focus();
		return false;
	}
	
	if (!isNum(document.book.price.value)) {
		window.alert("价格必须为数值！");
		document.book.price.focus();
		return false;
	}
	
	
	
}