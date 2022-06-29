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
	//Main.jsp 期間絞り込みselect生成
	let year = document.getElementById("kikan_toshi");
	let month = document.getElementById("kikan_tsuki");
	const date = new Date();
	const thisYear = date.getFullYear();

	for (let i = 2020; i <= thisYear; i++) {
		let option = document.createElement("option");
		option.setAttribute("value", i);
		option.innerHTML = i + "年";
		year.appendChild(option);
		if(option.value == thisYear) {
			option.setAttribute("selected", true);
		}
	}
	for (let i = 1; i <= 12; i++) {
		let option = document.createElement("option");
		option.setAttribute("value", ("0" + i).slice(-2));
		option.innerHTML = i + "月";
		month.appendChild(option);
		if(option.value ==  date.getMonth() + 1) {
			option.setAttribute("selected", true);
		}
	}
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
	console.log(date);
	let year = date.getFullYear().toString();
	let monthvalue = date.getMonth() + 1;
	let month = monthvalue.toString();
	console.log(date);
	month = month.length < 2? "0" + month : month
	
	let day = date.getDate().toString();
	day = day.length < 2? "0" + day : day
	date = year + "-" + month + "-" + day; 
	console.log(date);
	document.getElementById("date").value = date;
}
(function() {
	/*
	 * 任意の年でうるう年があるかをチェック
	 * @param{number}チェックしたい西暦年号
	 * @return{boolean}うるう年であるかを示す真偽値
	 */
		const isLeapYear = date_year =>(date_year % 4 === 0) && (date_year % 100 !== 0) || (date_year % 400 === 0);

	/*
	 * 任意の年の２月の日数を数える
	 * @param{number}チェックしたい西暦年号
	 * @return{boolean}その年の2月の日数
	 */
		const countDatesOfFeb = date_year => isLeapYear(date_year) ? 29 : 28;

	/*
	 * セレクトボックスの中にオプションを生成する
	 * @param{string}セレクトボックスのDOMのid属性値
	 * @param{number}オプションを生成する最初の数値
	 * @param{number}オプションを生成する最後の数値
	 * @param{number}現在の日数にマッチする数値
	 */

		const createOption = (id, startNum, endNum, current) => {
			let selectdate = document.getElementById(id);
			const selectDom = document.getElementById(id);
			let optionDom = '';
			for (let i = startNum; i <= endNum; i++) {
				if (i == current) {
					option = '<option value="' + i + '" selected>' + i + '</option>';
				} else {
					option = '<option value="' + i + '">' + i + '</option>';
				}
				optionDom += option;
			}
			selectDom.insertAdjacentHTML('beforeend', optionDom);
		}


		// DOM
		const yearBox = document.getElementById('date_year');
		const monthBox = document.getElementById('date_month');
		const dateBox = document.getElementById('date_day');
		const reset = document.getElementById('reset');
		const selectUpdateId = document.getElementById('selectUpdateId');

		// 日付データ
		const today = new Date();
		const thisYear = today.getFullYear();
		const thisMonth = today.getMonth() + 1;
		const thisDate = today.getDate();

		let datesOfYear = [31, countDatesOfFeb(thisYear), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

		// イベント
		monthBox.addEventListener('change', (e)=> {
			dateBox.innerHTML = '';
			let selectedMonth = e.target.value;
			if(selectedMonth.search('[1-9]') !== -1) {
				while(selectedMonth.match('[a-z]', '[A-Z]') !== null) {
					selectedMonth = selectedMonth.substring(1);
					if(selectedMonth.startsWith('0')) {
						selectedMonth = selectedMonth.substring(1);
					}
				}
			}
			datesOfYear = [31, countDatesOfFeb(thisYear), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
			createOption('date_day', 1, datesOfYear[selectedMonth - 1], 1);
		});

		yearBox.addEventListener('change', e => {
			monthBox.innerHTML = '';
			dateBox.innerHTML = '';
			const updatedYear = e.target.value;
			datesOfYear = [31, countDatesOfFeb(updatedYear), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

			createOption('date_month', 1, 12, 1);
			createOption('date_day', 1, datesOfYear[0], 1);
		});

		reset.addEventListener('click', e => {
			resetCookie();

			yearBox.innerHTML = '';
			monthBox.innerHTML = '';
			dateBox.innerHTML = '';

			createOption('date_year', 2020, thisYear, thisYear);
			createOption('date_month', 1, 12, thisMonth);
			createOption('date_day', 1, datesOfYear[thisMonth - 1], thisDate);
		});

		// ロード時
		if(document.cookie.length > 0) {

			let date = {};
			date.year = getCookieValue('year');
			date.month = getCookieValue('month');
			date.day = getCookieValue('day');

			createOption('date_year', 2020, thisYear, date.year);
			createOption('date_month', 1, 12, date.month);
			createOption('date_day', 1, datesOfYear[thisMonth - 1], date.day);

		} else {
			createOption('date_year', 2020, thisYear, thisYear);
			createOption('date_month', 1, 12, thisMonth);
			createOption('date_day', 1, datesOfYear[thisMonth - 1], thisDate);
		}
	}());
