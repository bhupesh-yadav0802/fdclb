
var Web3 = require('web3');
var fs = require('fs');
var BigNumber = require('bignumber.js');
let Tx = require('ethereumjs-tx');

var web3 = new Web3(Web3.givenProvider || 'https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2');
var Accounts = require('web3-eth-accounts');
const ERC20Contract = require('erc20-contract-js');


const numberToBN = require('number-to-bn');
var CircularJSON = require('circular-json');
const request = require('request');

const jwt = require('jsonwebtoken');

var path = require("path");
var async = require('async')

let contractAddresss = "0x107E1D4d725c1597AC615C2de2E7aca1d38d2954"

//---------------------------------------------=======================

const getBalance = (address, cb) => {
  web3.eth.getBalance(address).then(token => {
    console.log("amount==>>", token)
    token = new BigNumber(token).dividedBy(new BigNumber(Math.pow(10, 18)))
    console.log("amountBigNo.==>>", token)
    cb(null, token)
  }).catch(err => {
    cb(null, err)
  })
}


const getCurrentGasPrice = (cb) => {
  web3.eth.getGasPrice()
    .then((currentGasPrice) => {
      console.log("currentGasPrice===>>", currentGasPrice)
      return cb(currentGasPrice)
    })
}

const estGas = (toAddr, fromAddr, value, cb) => {
  web3.eth.estimateGas({
    from: fromAddr,
    to: toAddr,
    value: value
  }).then((estmdGas) => {
    console.log(" Your estmdGas is ==>>", estmdGas)
    return cb(estmdGas)
  }).catch(console.log)
}

const getTxnCountForNonce = (addr, cb) => {
  web3.eth.getTransactionCount(addr)
    .then((count) => {
      return cb(count)
    });
}

const signTxn_transfer = (toAddr, fromAddr, value, key, cb) => {
  estGas(toAddr, fromAddr, value, (estmdGas) => {
    getCurrentGasPrice((currentGasPrice) => {
      getTxnCountForNonce(fromAddr, (hardCount) => {
      
        var fee_res = new BigNumber(estmdGas).multipliedBy(new BigNumber(2 * 1e9));
        var data = new BigNumber(value).minus(new BigNumber(fee_res));
        
        console.log("data@@@@@", data)
        console.log("***********", fee_res)
        console.log("actual fee can be ducted----", estmdGas * currentGasPrice)
        let rawTx = {
          nonce: web3.utils.toHex(hardCount),
          from: web3.utils.toHex(fromAddr),
          
          gasPrice: web3.utils.toHex(2 * 1e9),
          gas: web3.utils.toHex(estmdGas),
          to: web3.utils.toHex(toAddr),
          value: web3.utils.toHex(data)
        }
       
        var tx = new Tx(rawTx);
        tx.sign(key);
        let serializedTx = tx.serialize();
        console.log("serializedTx", serializedTx)
        let cbData = '0x' + serializedTx.toString('hex')
        console.log("cb Data is ", cbData)
        cb(cbData)
      })
    })
  })
}

const signTxn = (toAddr, fromAddr, value, key, cb) => {
  estGas(toAddr, fromAddr, value, (estmdGas) => {
    getCurrentGasPrice((currentGasPrice) => {
      getTxnCountForNonce(fromAddr, (hardCount) => {
        
        let rawTx = {
          nonce: web3.utils.toHex(hardCount),
          from: web3.utils.toHex(fromAddr),
          gasPrice: web3.utils.toHex(5 * 1e9),
          gas: web3.utils.toHex(estmdGas),
          to: web3.utils.toHex(toAddr),
          value: web3.utils.toHex(value)
        }
       
        var tx = new Tx(rawTx);
        tx.sign(key);
        let serializedTx = tx.serialize();
        console.log("serializedTx", serializedTx)
        let cbData = '0x' + serializedTx.toString('hex')
        console.log("cb Data is ", cbData)
        cb(cbData)
      })
    })
  })
}



module.exports = {

  //---------------------------------------------------------------------------newAddress----------------------------------
  get_wallet: (req, res) => {

    if (!req.query.password) {
      return res.send({ code: 400, message: "Parameters Missing!!" })
    }
    var privateKey = web3.eth.accounts.wallet.create(1, req.query.password)
   
    var objInfo = privateKey.length - 1;
    

    var result = {
      address: privateKey[objInfo].address,
      privateKey: privateKey[objInfo].privateKey,
    }

    return res.send({ code: 200, Result: result })

  },

  ///////----------------------------------------Getting balance----------------------------------------------------------------------////////

  get_balance: (req, res) => {
    var options = {
            
      url: `https://api-ropsten.etherscan.io/api?module=account&action=tokenbalance&contractaddress=${contractAddresss}&address=${req.query.address}`
  
    };
    console.log(options);
    function callback(error, response, body) {
      console.log("))))))))))))(((((((((((===>>", body)
      
      var body_status = JSON.parse(body).status
      if ( body_status=="0") {
        res.status(200).json({ status:400, message: "Invalid address", isSuccessful: false });
      }else if (!error && response.statusCode == 200) {
       
        res.status(200).json({ status:200, message: "Wallet_balance", isSuccessful: true,  data: {balance: JSON.parse(body).result / 1000 }});
      } else {

        res.send({ code: 500, error: "Internal server error" })
      }
    }
    request(options, callback);

  },

  ////////////////////////////////////////////////withdraw///////////////////////////////

  withdraw: async (req, res) => {
    
    var obj = {
      fromAddr: req.body.fromAddr,
      toAddr: req.body.toAddr,
      privateKey: req.body.privateKey,
      amount: req.body.amount
    }
    
    if (!obj.fromAddr || !obj.toAddr || !obj.privateKey) {
      res.send({ code: 409, message: "Parameter Missing..!!!" })
    } else {
     
      var AbiArray = JSON.parse(fs.readFileSync(path.resolve(__dirname, './tt3.json'), 'utf-8'));
      var ContractAddress = "0x107E1D4d725c1597AC615C2de2E7aca1d38d2954";
      
      var web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2'));
      var transferAmount = web3.utils.toWei(obj.amount.toString(), "ether");
      console.log("Amount => ", transferAmount);
      var nonce = await web3.eth.getTransactionCount(obj.fromAddr);
      const contract = new web3.eth.Contract(AbiArray, ContractAddress);
      const data = contract.methods.transfer(obj.toAddr, transferAmount);
      var rawTransaction = {
        nonce: web3.utils.toHex(nonce),
        gasPrice: web3.utils.toHex(2 * 1e11),
        gasLimit: web3.utils.toHex(0x250CA),
        to: ContractAddress,
        value: "0x0",
        data: data.encodeABI(),
        chainId : 3
      };
      var privKey = new Buffer.from(obj.privateKey, "hex");
      var tx = new Tx(rawTransaction, { chain: 'Public' });
      tx.sign(privKey);
      let serializedTx = "0x" + tx.serialize().toString("hex");
      console.log(serializedTx);
      web3.eth
        .sendSignedTransaction(serializedTx)
        .on("transactionHash", async (txHash, err) => {
          if (!err) {
            console.log(txHash);
            res.status(200).json({ status:200, message: "Transaction placed", isSuccessful: true,  data: {txHash:txHash }});
     

          } else {
            console.log(err);
            res.status(400).json({ error: err });
          }
        });
    }

  },
  
 
  ///////-------------------------------------------------accountHistory--------------------------------------////////
  get_deposits: function (req, res, ) {
    if (!req.query.address) {
      return res.send({ code: 400, message: "Parameters Missing!!" })
    }
    var dataString = {
      "name": req.query.address,
    }

    var options = {
     
       url: 'http://api-ropsten.etherscan.io/api?module=account&action=txlist&address=' + req.query.address + '&sort=desc',
      method: 'POST',
      headers: {
        'accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(dataString)
    };

    function callback(error, response, body) {
      var arr =[]
      if (!error && response.statusCode == 200) {

      
        var body_result = JSON.parse(body).result
        if (body_result) {
          body_result.forEach((elem) => {
            if (elem.to === (req.query.address).toLowerCase()) { arr.push(elem) }
          })
          res.send({ code: 200, deposits: arr })
        }
        else {
          res.send({ code: 500, message: "Not Found." })
        }
      }
      else
        res.send({ code: 500, message: "Internal Sever Error" })
    }
       
    request(options, callback);

  },

//=========================================================transfer==================================================================

transfer: async (req, res) => {
  var AdminAddress = '0x05Be0B0BC88C5F3A25198D6a01966EE605Eb1a19';
  var privateKey = '992cc1a71749ac9ff0f6559b958f49bd614460b95f0b53b90e6ce700c671488d';
  var AbiArray = JSON.parse(fs.readFileSync(path.resolve(__dirname, './tt3.json'), 'utf-8'));
  var ContractAddress = "0x107E1D4d725c1597AC615C2de2E7aca1d38d2954";
  const { receiverAddress, amount } = req.body;
  var web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2'));
  var transferAmount = web3.utils.toWei(amount.toString(), "ether");
  console.log("Amount => ", transferAmount);
  var nonce = await web3.eth.getTransactionCount(AdminAddress);
  const contract = new web3.eth.Contract(AbiArray, ContractAddress);
  const data = contract.methods.transfer(receiverAddress, transferAmount);
  var rawTransaction = {
    nonce: web3.utils.toHex(nonce),
    gasPrice: web3.utils.toHex(2 * 1e9),
    gasLimit: web3.utils.toHex(0x250CA),
    to: ContractAddress,
    value: "0x0",
    data: data.encodeABI(),
    chainId : 3
  };
  var privKey = new Buffer.from(privateKey, "hex");
  var tx = new Tx(rawTransaction, { chain: 'Public' });
  tx.sign(privKey);
  let serializedTx = "0x" + tx.serialize().toString("hex");
  console.log(serializedTx);
  web3.eth
    .sendSignedTransaction(serializedTx)
    .on("transactionHash", async (txHash, err) => {
      if (!err) {
        console.log(txHash);
        res.status(200).json({ status:200, message: "Transaction placed", isSuccessful: true,  data: {txHash:txHash }});
      } else {
        console.log(err);
        res.status(400).json({ error: err });
      }
    });
},
///////////////////////////////////////////////Send Token/////////////////////////////////////////////
send_token: async (req, res) => {
  var AdminAddress = '0x05Be0B0BC88C5F3A25198D6a01966EE605Eb1a19';
  var privateKey = '992cc1a71749ac9ff0f6559b958f49bd614460b95f0b53b90e6ce700c671488d';
  var AbiArray = JSON.parse(fs.readFileSync(path.resolve(__dirname, './tt3.json'), 'utf-8'));
  var ContractAddress = "0x107E1D4d725c1597AC615C2de2E7aca1d38d2954";
  const { receiverAddress, no_of_rametron } = req.body;
  var amount = no_of_rametron * 100
  var web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2'));
  var transferAmount = web3.utils.toWei(amount.toString(), "ether");
  console.log("Amount => ", transferAmount);
  var nonce = await web3.eth.getTransactionCount(AdminAddress);
  const contract = new web3.eth.Contract(AbiArray, ContractAddress);
  const data = contract.methods.transfer(receiverAddress, transferAmount);
  var rawTransaction = {
    nonce: web3.utils.toHex(nonce),
    gasPrice: web3.utils.toHex(2 * 1e9),
    gasLimit: web3.utils.toHex(0x250CA),
    to: ContractAddress,
    value: "0x0",
    data: data.encodeABI(),
    chainId : 3
  };
  var privKey = new Buffer.from(privateKey, "hex");
  var tx = new Tx(rawTransaction, { chain: 'Public' });
  tx.sign(privKey);
  let serializedTx = "0x" + tx.serialize().toString("hex");
  console.log(serializedTx);
  web3.eth
    .sendSignedTransaction(serializedTx)
    .on("transactionHash", async (txHash, err) => {
      if (!err) {
        console.log(txHash);
        res.status(200).json({ status:200, message: "Transaction placed", isSuccessful: true,  data: {txHash:txHash }});
      } else {
        console.log(err);
        res.status(400).json({ error: err });
      }
    });
},
///////////////////////////////////////////////////Transaction history////////////////////////////////////////////////////////////////////////////////////////////////////////////
get_transactions: function (req, res, ) {
  if (!req.query.address) {
    return res.send({ code: 400, message: "Parameters Missing!!" })
  }
  var dataString = {
    "name": req.query.address,
  }

  var options = {
   
    // url: 'https://api-ropsten.etherscan.io/api?module=account&action=txlist&address=' + req.query.address + `&startblock=0&endblock=99999999&sort=asc&contractaddress=${contractAddresss}`,
    url:`https://api-ropsten.etherscan.io/api?module=account&action=tokentx&contractaddress=${contractAddresss}&address=` + req.query.address + `&page=1&offset=100&sort=asc`,
     method: 'GET',
    headers: {
      'accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dataString)
    
  };
//console.log(options.body,"sdfh")
  function callback(error, response, body) {
    var arr =[]
    if (!error && response.statusCode == 200) {

    
      var body_result = JSON.parse(body).result
      //console.log(body_result,"jhfgkjg")
      if (Array.isArray(body_result)){
        let temparray;
        body_result.map(val=>{
          val.value=val.value/1000
          })
      
        }
        //console.log(body_result,"body")
        if (body_result.length==0){
          temparray=[];
          res.send({ code: 404, message: "transactions not found.", isSuccessful: true, data:temparray });
        }
        else {
      if (Array.isArray(body_result)) {
        body_result.forEach((elem) => {
          if (elem.to === (req.query.address).toLowerCase()) { arr.push(elem) }
        })
        temparray=body_result;
        res.status(200).json({ status:200, message: "transaction_list", isSuccessful: true,  data:body_result });
      }
      
      else {
        res.status(200).json({ status:200, message: "transaction_list", isSuccessful: true,  data:temparray });
      }
    }
  }
  }
     
  request(options, callback);

},
}


