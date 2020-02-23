var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
  
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
  
app.get('/', function (req, res) {
    return res.send({ error: true, message: 'Test dorm Web API' })
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'dormdb'
});
  
dbConn.connect(); 

app.get('/alldorm', function (req, res) {
    dbConn.query('SELECT * FROM dorm', function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});
app.get('/alluser', function (req, res) {
    dbConn.query('SELECT * FROM user', function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});
app.get('/dorm/:id', function (req, res) {
    let user_id = req.params.id;
    if (!user_id) {
        return res.status(400).send({ error: true, message: 'Please provide user_id' });
    }
  
    dbConn.query("SELECT * FROM dorm WHERE user_id = ?", user_id , function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    });
});

app.get('/dormz1', function (req, res) {
    dbConn.query("SELECT * FROM dorm where zone= 'Kungsadan'", function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.get('/dormz2', function (req, res) {
    dbConn.query("SELECT * FROM dorm where zone = 'LungMor'", function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.get('/dormz3', function (req, res) {
    dbConn.query("SELECT * FROM dorm where zone = 'Columbo'", function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.get('/allroom', function (req, res) {
    dbConn.query("SELECT room.room_id,room.dorm_id,room.room_name,room.price,room.avariable,room.amout_room,room.room_img,room_detail.bed,room_detail.air,room_detail.fan,room_detail.water_heater,room_detail.bill,room_detail.insure,room_detail.internet,room_detail.animal FROM room JOIN room_detail ON(room.room_id = room_detail.room_id)", function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/dorm', function (req, res) {
  
    var std = req.body
      
    if (!std ) {
        return res.status(400).send({ error:true, message: 'Please provide Dorm' });
    }
    dbConn.query("INSERT INTO dorm SET ? ", std , function (error, results, fields) { 
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/user', function (req, res) {
  
    var std = req.body
      
    if (!std ) {
        return res.status(400).send({ error:true, message: 'Please provide Room ' });
    }
    dbConn.query("INSERT INTO user SET ? ", std , function (error, results, fields) { 
        if (error) throw error;
        return res.send(results);
    });
});


app.post('/room', function (req, res) {
  
    var std = req.body
      
    if (!std ) {
        return res.status(400).send({ error:true, message: 'Please provide Room ' });
    }
    dbConn.query("INSERT INTO ROOM SET ? ", std , function (error, results, fields) { 
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/roomdetail', function (req, res) {
  
    var std = req.body
      
    if (!std ) {
        return res.status(400).send({ error:true, message: 'Please provide RoomDetail ' });
    }
    dbConn.query("INSERT INTO room_detail SET ? ", std , function (error, results, fields) { 
        if (error) throw error;
        return res.send(results);
    });
});

app.get('/room/:id', function (req, res) {
  
    let dorm_id = req.params.id;
  
    if (!dorm_id) {
        return res.status(400).send({ error: true, message: 'Please provide dorm_id' });
    }
  
    dbConn.query("SELECT room.room_id,room.dorm_id,room.room_name,room.price,room.avariable,room.amout_room,room.room_img,room_detail.bed,room_detail.air,room_detail.fan,room_detail.water_heater,room_detail.bill,room_detail.insure,room_detail.internet,room_detail.animal FROM room INNER JOIN room_detail ON(room.room_id = room_detail.room_id) WHERE room.dorm_id=?", dorm_id , function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    });
  
});

app.put('/dorm/:id', function (req, res) {
  
    let dorm_id = req.params.id;
    let dorm = req.body;
  
    if (!dorm_id || !dorm ) {   
        return res.status(400).send({ error: user, message: 'Please provide Dorm data and dorm_id' });    
    }
  
        dbConn.query('UPDATE dorm SET ? WHERE dorm_id = ?',[dorm, dorm_id], function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been updated successfully.' });
    });
});

app.put('/user/:id', function (req, res) {
  
    let user_id = req.params.id;
    let user = req.body;
  
    if (!user_id || !user ) {   
        return res.status(400).send({ error: user, message: 'Please provide user data and user_id' });    
    }
  
        dbConn.query('UPDATE user SET ? WHERE user_id = ?',[user, user_id], function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been updated successfully.' });
    });
});

app.put('/room/:id', function (req, res) {
  
    let room_id = req.params.id;
    let room = req.body;
  
    if (!room_id || !room ) {   
        return res.status(400).send({ error: user, message: 'Please provide Room data and room_id' });    
    }
  
        dbConn.query('UPDATE room SET ? WHERE room_id = ?',[room, room_id], function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been updated successfully.' });
    });
});

app.put('/roomdetail/:id', function (req, res) {
  
    let room_id = req.params.id;
    let room = req.body;
  
    if (!room_id || !room ) {   
        return res.status(400).send({ error: user, message: 'Please provide Room data and room_id' });    
    }
  
        dbConn.query('UPDATE room_detail SET ? WHERE room_id = ?',[room, room_id], function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been updated successfully.' });
    });
});

app.delete('/dorm/:id', function (req, res) {
  
    let dorm_id = req.params.id;
  
    if (!dorm_id) {
        return res.status(400).send({ error: true, message: 'Please provide dorm_id' });
    }
    dbConn.query('DELETE FROM dorm WHERE dorm_id = ?', dorm_id, function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been deleted successfully.' });
    });
});

app.delete('/user/:id', function (req, res) {
  
    let user_id = req.params.id;
  
    if (!user_id) {
        return res.status(400).send({ error: true, message: 'Please provide user_id' });
    }
    dbConn.query('DELETE FROM user WHERE user_id = ?', user_id, function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been deleted successfully.' });
    });
});


app.delete('/room/:id', function (req, res) {
  
    let room_id = req.params.id;
  
    if (!room_id) {
        return res.status(400).send({ error: true, message: 'Please provide room_id' });
    }
    dbConn.query('DELETE FROM room WHERE room_id = ?', room_id, function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been deleted successfully.' });
    });
});

app.delete('/roomdetail/:id', function (req, res) {
  
    let room_id = req.params.id;
  
    if (!room_id) {
        return res.status(400).send({ error: true, message: 'Please provide room_id' });
    }
    dbConn.query('DELETE FROM room_detail WHERE room_id = ?', room_id, function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Dorm has been deleted successfully.' });
    });
});

app.post('/login',function(req, res){
    var data = req.body;
    var username = data.username;
    var password = data.password;
    dbConn.query('SELECT * FROM user WHERE username = ? AND password = ?',[username,password],function(error,results,fields){
        dbConn.on('error',function(err){
            console.log('[MySQL ERROR]',err)
        });
        if(results && results.length){
            res.end(JSON.stringify(results[0]))
        }
        else{
            res.end(JSON.stringify('Wrong password'));
        }
    });
});

app.listen(3000, function () {
    console.log('Node app is running on port 3000');
});
 
module.exports = app;