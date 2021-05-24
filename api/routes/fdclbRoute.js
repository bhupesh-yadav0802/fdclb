
'use strict';

module.exports = function(app) {
  var fdclb = require('../controllers/fdclb');
  
  
  app.route('/fdclb/generateAddress')
    .get(fdclb.get_wallet);
  
    app.route('/fdclb/getBalance')
    .get(fdclb.get_balance);
  
    app.route('/fdclb/deposits')
    .get(fdclb.get_deposits); 
    
    app.route('/fdclb/transactions')
    .get(fdclb.get_transactions);

    app.route('/fdclb/transfer')
    .post(fdclb.transfer);

    app.route('/fdclb/send_token')
    .post(fdclb.send_token);
    
    app.route('/fdclb/withdraw')
    .post(fdclb.withdraw);
  
};



