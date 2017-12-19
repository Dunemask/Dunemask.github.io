// JavaScript Do

$(document).ready(function(e) {
    //This next section will select the c box and add
	//a play function to the box
	
	var happy = document.getElementById('happy');
	$('#FavSong').mousedown(function(){
			happy.currentTime = 0;
			happy.play();
	});
    var happy2 = document.getElementById('happy2');
	$('#FavSong2').mousedown(function(){
			happy2.currentTime = 0;
			happy2.play();
		});
    var happy3 = document.getElementById('happy3');
	$('#FavSong3').mousedown(function(){
			happy3.currentTime = 0;
			happy3.play();
	});
	var c_note = document.getElementById('cNote');
	$('#c').mouseover(function(){
			c_note.currentTime = 0;
			c_note.play();
		});
		//Set function of D note
		var d_note = document.getElementById('dNote');
	$('#d').mouseover(function(){
			d_note.currentTime = 0;
			d_note.play();
		});
		
		var e_note = document.getElementById('eNote');
	$('#e').mouseover(function(){
			e_note.currentTime = 0;
			e_note.play();
		});
		//Set function of F note
		var f_note = document.getElementById('fNote');
	$('#f').mouseover(function(){
			f_note.currentTime = 0;
			f_note.play();
		});
		//Set function of G note
		var g_note = document.getElementById('gNote');
	$('#g').mouseover(function(){
			g_note.currentTime = 0;
			g_note.play();
		});
		//Set function of A note
		var a_note = document.getElementById('aNote');
	$('#a').mouseover(function(){
			a_note.currentTime = 0;
			a_note.play();
		});
		//Set function of B note
		var b_note = document.getElementById('bNote');
	$('#b').mouseover(function(){
			b_note.currentTime = 0;
			b_note.play();

		
		});
});