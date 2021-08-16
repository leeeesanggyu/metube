/*
 	매번 랜덤값을 파라미터로 전달하는 이유 : 
	IE의 경우 매번 다른 임의 값을 전달하지 않으면 '새로고침' 
	클릭해도 정상 호출되지 않아 이미지가 변경되지 않는 문제가 발생된다
*/
function audio(){ 
	var rand = Math.random(); 
	var uAgent = navigator.userAgent;
	var soundUrl = '${ctx}/captchaAudio?rand='+rand; 
	if(uAgent.indexOf('Trident')>-1 || uAgent.indexOf('MISE')>-1){ /*IE 경우 */ 
		audioPlayer(soundUrl); 
	} else if (!!document.createElement('audio').canPlayType){ /*Chrome 경우 */ 
		try { new Audio(soundUrl).play(); 
		} catch (e) { 
			audioPlayer(soundUrl); 
		} 
	} else { 
		window.open(soundUrl,'','width=1,height=1'); 
	} 
} 

function getImage() {  
	var url = `/captchaImg`;
	document.querySelector('#captcha').setAttribute('src', url); 
}

function audioPlayer(objUrl){ 
	document.querySelector('#ccaudio').innerHTML = '<bgsoun src="' +objUrl +'">'; 
}