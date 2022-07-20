// 削除ページアラート
function clickalert() {
	var result = window.confirm('本当に削除してよろしいですか？');

	if(result) {
		//OKなら移動
		return true;
	}
	else {
		//キャンセルは何もしない
		return false;
	}
}

const logoutalert = () => {
	let result = window.confirm('ログアウトしてもよろしいですか？');
	return result;
}

// サブウィンドウ
function subwin_up() {
	window.open("/update","修正ページ","width=600,height=700,top=0,left=0");
}
function subwin_del() {
	window.open("/delete","修正ページ","width=600,height=700,top=0,left=0");
}
function closebtn() {
	window.close();
}


	

const resetCookie = () => {

	const dates = ['year=', 'month=', 'day='];
		for(let i = 0; i < dates.length; i++) {
		document.cookie = dates[i] + '' + '; max-age=0';
	}
}

const createCookie = () => {

	const dates = ['date_year', 'date_month', 'date_day'];
	for(let i = 0; i < dates.length; i++) {
		let value = document.getElementById(dates[i]).value;
		let date = dates[i].split('_');

		document.cookie = date[1] + '=' + value + '; max-age=600';
	}
}

const getCookieValue = (elem) => {
	//引数と同名のキーを取得し、値を返す
	let value = '';
	if(document.cookie != '') {
		let tmp = document.cookie.split(';');
		for(let i = 0; i < tmp.length; i++) {
			let data = tmp[i].split('=');
			data[0] = data[0].trim();
			if(data[0] == elem) {
				value = data[1];
			}
		}
	}
	return value;
}





const setSelectDate = () => {
	//main.html 期間絞り込みselect生成
	let year = document.getElementById("kikan_toshi");
	let month = document.getElementById("kikan_tsuki");
	const date = new Date();
	const thisYear = date.getFullYear();
	

	for (let i = 2020; i <= thisYear; i++) {
		let option = document.createElement("option");
		option.setAttribute("value", i);
		option.innerHTML = i + "年";
		year.appendChild(option);
		
		if(selectyear != null) {
			if(selectyear == option.value) {
				option.setAttribute("selected", true);
			}
		} else if(option.value == thisYear) {
			option.setAttribute("selected", true);
		}
	}
	
	for (let i = 1; i <= 12; i++) {
		let option = document.createElement("option");
		option.setAttribute("value", ("0" + i).slice(-2));
		option.innerHTML = i + "月";
		month.appendChild(option);
		if(selectmonth != null) {
			if(selectmonth == option.value) {
				option.setAttribute("selected", true);
			}
		} else if(option.value ==  date.getMonth() + 1) {
			option.setAttribute("selected", true);
		}
	}
}
const test = () => {
	
		console.log("通信");
			fetch('/test', {　 // 送信先URL
	   	 method: 'get'// 通信メソッド
	    
	});
}
const inputCheck = () => {
	let errermessage = document.getElementById("errermessage")
	errermessage.innerHTML = '';

	if(document.body.contains(document.getElementById("selectedId"))) {
		const selectedId =document.getElementById("selectedId").value;

		if(selectedId === null || selectedId ==='') {
			errermessage.insertAdjacentHTML('beforeend','エラー：IDを入力してください。');
			return false;
		}
	}

	let income = document.getElementById("income").value;
	let expense = document.getElementById("expense").value;

	if(income > 0 && expense > 0) {
		errermessage.insertAdjacentHTML('beforeend','エラー：入出金額の項目がどちらも1以上のデータは登録できません。');
		return false;
	}
	if(income == 0 && expense == 0) {
		errermessage.insertAdjacentHTML('beforeend', 'エラー：入出金額の項目がどちらも0のデータは登録できません。');
		return false;
	}
}
const selectDate = () => {
	let date = new Date();
	let year = date.getFullYear().toString();
	let monthvalue = date.getMonth() + 1;
	let month = monthvalue.toString();
	month = month.length < 2? "0" + month : month
	
	let day = date.getDate().toString();
	day = day.length < 2? "0" + day : day
	date = year + "-" + month + "-" + day; 
	document.getElementById("date").value = date;
}

