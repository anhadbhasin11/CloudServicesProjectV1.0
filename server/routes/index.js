var path = require('path');
var cache = path.join(__dirname, 'fake_data');

var router = {
  setup: function (app, aws) {
    app.get('/customers', function(req, res, next) {
      res.sendFile(path.join(cache, 'customers.json'));
    });

    app.get('/orders', function(req, res, next) {
      res.sendFile(path.join(cache, 'orders.json'));
    });

    app.get('/products', function(req, res, next) {
      console.log("calling AWS");
      res.sendFile(path.join(cache, 'products.json'));
    });


      /*  var params = {
       TableName: "ProductLine",
       Key: {
       "RollNumber": 2
       }
       };

       var products = aws.getData(params);
       res.send(200, products);
       });

       /* GET home page. */
      app.get('/', function (req, res, next) {
        res.sendFile('../index.html');
      });
  }
};

module.exports = router;
