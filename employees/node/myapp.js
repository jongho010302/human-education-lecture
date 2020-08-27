var express = require('express');
var mysql = require('mysql');
var cors = require('cors');

var app = express();
app.use(cors());
app.use(express.json());

var conn = mysql.createConnection({
  host : "kirei.ct5oa68d8bbs.ap-northeast-2.rds.amazonaws.com",
  user : "kirei",
  password: "Whdghwhdgh0302!",
  database: "myapp"
});

app.get('/', function(req, res, next){
  res.json({"msg:":" Hello!"});
});

app.get('/employees', function(req, res, next){
  conn.query('SELECT * FROM employees', function(error, results){
    if(error){
      res.json({"msg:":"/ database error"});
    } else{
      res.json(results);
    }
  });
});

app.post('/employees', function(req, res, next){
  
  var emp = req.body;

  var query = "insert into employees(emp_name, emp_email, emp_phone)"
                  + " values(?, ?, ?)";
   var parm = [emp.emp_name, emp.emp_email, emp.emp_phone];

  conn.query(query, parm, function(error, results){
    if(error){ 
       console.log(error);
       conn.rollback();
      res.json({msg: "database error"});
     } else {
       conn.commit();
       res.json(results);
    }
   });
});

app.put('/employees', function(req, res, next){
  
  var emp = req.body;

  var query = 'update employees'
            + ' set emp_name=?, emp_email=?, emp_phone=?'
            + ' where emp_no=?';
   var parm = [emp.emp_name, emp.emp_email, emp.emp_phone, emp.emp_no];

  conn.query(query, parm, function(error, results){
    if(error){ 
       console.log(error);
       conn.rollback();
      res.json({msg: "database error"});
     } else {
       conn.commit();
       res.json(results);
    }
   });
});

app.delete('/employees', function(req, res, next){
  
  var emp = req.body;

  console.log(emp);

  var query = 'delete from employees where emp_no = ?'
  var parm = [emp.emp_no];

  conn.query(query, parm, function(error, results){
    if(error){ 
       console.log(error);
       conn.rollback();
      res.json({msg: "database error"});
     } else {
       conn.commit();
       res.json(results);
    }
   });
});

app.listen(8080, function(){
  console.log('web server listening on port 8080');
});