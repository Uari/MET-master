
function doSendMail()
{
    var scenarioId = document.getElementById("scenarioId").value;
 	var send_user_area = document.getElementById("sendUserNames");
 	var options = [];

 	for(var i = 0; i < send_user_area.options.length; i++)
 	{
 		var sendTarget = send_user_area.options[i].value;
 		options.push(sendTarget);
 	}

    var message = {
        scenarioId : scenarioId,
        sendUserIds : options
    };

    console.log("message : " + message.sendUserIds);

    $.ajax({
        type    : 'POST',
        url     : '/postMail',
        traditional: true,
        data    : message,
        success : function(result) {
            alert("success");
        },
        error   : function(result) {},
        complete: function() {
    }});
}

function loadScenario()
{
    var selectScenario = document.getElementById('scenarioNames');
    selectScenario = selectScenario.options[selectScenario.selectedIndex].value;

    var message = {
        scenarioId : selectScenario
    };

    $.ajax({
        type    : 'POST',
        url     : '/scenarioInfoLoad',
        data    : message,
        success : function(result) {
            $("#scenarioInfoForm").replaceWith(result);
        },
        error   : function(result) {},
        complete: function() {
    }});
}

function modalClick(clickStatus)
{
	if (clickStatus == 'on')
    {
        $.ajax({
        type    : 'POST',
        url     : '/callCorpInfo',
        success : function(result) {
            document.getElementsByClassName('modal')[0].style.display = 'table';
            $("#corpNames").replaceWith(result);
        },
        error   : function(result) {},
        complete: function() {
        }})

    }
    else
    {
        document.getElementsByClassName('modal')[0].style.display = 'none';
    }
}

function changeSelect(url, output, selectData)
{
    var message = {
        select : selectData
    };

    $.ajax({
    type    : 'POST',
    url     : url,
    data    : message,
    success : function(result) {
        console.log(result);
        $(output).replaceWith(result);
    },
    error   : function(result) {},
    complete: function() {
    }});
}

function applyToUserInfo()
{
	//modal 안의 리스트를
	var toUserSelect  = document.getElementById("toUserNames");
	//밖 select의 리스트로
	var send_user_area = document.getElementById("sendUserNames");
	if(send_user_area.options.length > 0)
		send_user_area.options.length = 0;
	/*
	for(var i = send_user_area.options.length - 1; i >= 0; i--)
	{
		toUserSelect.options[i] = null;
	}
	*/
	for(var i = 0; i < toUserSelect.options.length; i++)
	{
		var option = document.createElement("option");
		option.text = toUserSelect.options[i].text;
		option.value = toUserSelect.options[i].value;
		send_user_area.options.add(option);
	}
	document.getElementsByClassName('modal')[0].style.display = 'none';
}

function addUser()
{
	var userSelect = document.getElementById("userNames");
	var toUserArea = document.getElementById("toUserNames");
	for(var i = 0; i < userSelect.options.length; i++)
	{
		if(userSelect.options[i].selected)
		{
			var option = document.createElement("option");
			option.text = userSelect.options[i].text;
			option.value = userSelect.options[i].value;
			toUserArea.options.add(option);
		}
	}
}

function removeUser()
{
	var toUserSelect = document.getElementById("toUserNames");
	for(var i = toUserSelect.options.length - 1; i >= 0; i--)
		if(toUserSelect.options[i].selected)
			toUserSelect.options[i] = null;
}

function trainingAdd()
{

}

function trainingRemove()
{

}