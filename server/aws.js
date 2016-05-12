var AWS = require("aws-sdk");

AWS.config.update({
  region: "us-west-2",
  endpoint: "https://dynamodb.us-west-2.amazonaws.com",
  accessKeyId: 'AKIAIEEEXM3UO3KQKR2Q',
  secretAccessKey: '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'
});

var dynamodb = new AWS.DynamoDB();

var aws = {
  getData: function (params) {
    var docClient = new AWS.DynamoDB.DocumentClient();
    docClient.get(params, function(err, data) {
      if (err)
        console.log(JSON.stringify(err, null, 2));
      else
        console.log(JSON.stringify(data, null, 2));
    });
  }
};


module.exports = aws;