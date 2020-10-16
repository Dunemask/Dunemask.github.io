const express = require('express')
const path = require('path')
const app = express()
const fs = require('fs');
const bodyParser = require('body-parser');
const session = require('express-session');
const port = 3000
app.use(session({secret: 'ssshhhhh',saveUninitialized: true,resave: true}));
app.use(express.static(path.join(__dirname,'../')));
app.use(bodyParser.urlencoded({
   extended: false
}));
app.use(bodyParser.json());
app.get('/', function (req, res) {
  res.render('index.html', {});
});
app.listen(port, () => console.log('Dunemask Server listening on port '+port+"!"))
