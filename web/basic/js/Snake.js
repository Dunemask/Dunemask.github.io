// Javascript document
$(document).ready(function () {
    //Canvas STuff
    var	canvas = $("#canvas")[0];
    var	ctx	= canvas.getContext("2d");
    var	w =	$("#canvas").width();	//w	will the width used for the canvas
    var	h = $("#canvas").height();	//h	will the heigth used for the canvas


    //save cell 
    var cw = 10; //cell width in pixels
    var d = "right"; //This will store the direction
    var food;
    var score = 0; // count of the score
    var highscore = localStorage.getItem("HighScore");
    var speed;
    var game_loop;
    
    function snake_Runtime(){
    	
    }
    
    //This function will initiate the game
    function init(){
         d = "right"; //Default direction of the snake
         create_snake();
         create_food();
         score = 0;
         speed = 50;


         
         //setting highscore
         
         //Movement every 60
          if(typeof game_loop != "undefined")
            clearInterval(game_loop);
         game_loop = setInterval(paint, speed);
    } 

         // init();
         //snake_array
        function create_snake(){
        var length = 5;
        snake_array = [];
        for(var i = length - 1; i >= 0; i--) {
            snake_array.push({x:i+50 ,y:50});
            }

        }
    //creates food
    function create_food(){
        food = {
        x: Math.round(Math.random()*(w-cw)/cw), 
        y: Math.round(Math.random()*(h-cw)/cw), };
        
    }
   
    function paint(){
      ctx.fillStyle ="#333333";
      ctx.fillRect(0, 9, w,h);
      
    var tail;
    //movement of snake
    var nx = snake_array[0].x; //snake arry x axis is 0
    var ny = snake_array[0].y;
    
    //Direction Checks
    if(d == "right") nx++; //posotive x axis
    else if(d == "left") nx--; //nexative x
    else if(d == "up") ny--;  //posative y
    else if(d == "down") ny++; //negative y
    //checks collision with itself or wall
    if (nx <= -1 || nx >= w/cw || ny <= 0 || ny >= h/cw || check_collision(nx, ny, snake_array)){
        //restart game
    	init();
        return;
    
    }
        //eating food
        if( (nx == food.x)	&&	(ny == food.y)){
            tail = {x: nx, y: ny};
            score++;
            /*
            speed = speed - 20 ; //.00000000000025
            game_loop = setInterval(paint, speed); */
            

            
                    if(highscore < score)
            localStorage.setItem("HighScore", score);     
            highscore = localStorage.getItem("HighScore");
            
            //new food
            create_food();
        }
        else{
            tail = snake_array.pop();
            tail.x =nx;
            tail.y =ny;
        }
        //puts	the	tail	as	the	first	cell
        snake_array.unshift(tail);
        for(var	i =	0; i < snake_array.length; i ++){
        var	c = snake_array[i];
        paint_cell(c.x,	c.y);
        }
        //paint	food
        paint_cell(food.x,	food.y);
        //paint	the	score
        var	score_text = "Score: " + score;
        ctx.font="30px Arial";
        ctx.fillText(score_text, 0, h-660);
        score_text = "High Score: " + highscore;
        ctx.fillText(score_text, 0, h-620);
    }
        
        function paint_cell(x,y){
    ctx.fillStyle = "#cbcc00";
    ctx.fillRect(x*cw, y* cw, cw, cw);
    ctx.strokeStyle = "#cbcc00";
    ctx.strokeRect(x*cw, y*cw, cw, cw);
    }
    
    //Checks for collision
    function check_collision(x,y, array){
        for(var i = 0; i < array.length ; i++){ 
        //Conditional statement test for collision
        if(array[i].x == x && array [i].y ==y )
        return true;
    }
        return false; }
      

    $(document).keydown(function(e){
        var key = e.which;
        if(key == "65" && d != "right") d = "left";
        else if(key == "87" && d != "down") d = "up"; 
        else if(key == "68" && d != "left") d = "right"; 
        else if(key == "83" && d != "up") d = "down"; });

    init();
    });