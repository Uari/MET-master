function failCheck() {
    var url = location.href;
    var text = (url.slice(url.indexOf('?') + 1, url.length));
    text = text.split('=')[1];

    if(text == 'fail')
        alert("아이디가 없거나 패스워드가 일치하지 않습니다.");
    else if(text == 'error')
        alert("error");
    else if(text == 'sessionExpires')
        alert("세션이 만료됨");
}