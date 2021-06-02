package com.oodles.controllers;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.abi.datatypes.Function;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oodles.dto.ResultDto;
import com.oodles.dto.WithdrawDto;
import com.oodles.utils.ResponseHandler;



@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class  BlockchainController {
	
	Logger logger = LoggerFactory.getLogger(BlockchainController.class);
	
	
	@Autowired
	private MessageSource messageSource;
	
	//@Value("${nodeproject.url}")
	private String fileUrl = "/home/";

	@GetMapping(value = "/generateAddress")
	public ResponseEntity<Object> createAddress(@RequestParam String walletPassword) {
		try {
			
			File walletDirectory = new File(""+fileUrl);
			Bip39Wallet walletName = WalletUtils.generateBip39Wallet(walletPassword, walletDirectory);
			Credentials credentials = WalletUtils.loadBip39Credentials(walletPassword, walletName.getMnemonic());
			String accountAddress = credentials.getAddress();
			ECKeyPair privateKey = credentials.getEcKeyPair();
			String seedPhrase = walletName.getMnemonic();
			CreateWalletResponse createWalletResponse= new CreateWalletResponse();
			createWalletResponse.setAccountAddress(credentials.getAddress());
			createWalletResponse.setMneminicCode(walletName.getMnemonic());
			createWalletResponse.setPrivateKey(privateKey.getPrivateKey().toString(16));
			createWalletResponse.setPublicKey(privateKey.getPublicKey().toString(16));
			createWalletResponse.setSeedPhrase(walletName.getMnemonic());
			
				return ResponseHandler.response(createWalletResponse, "", true, HttpStatus.OK);
			
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping(value = "/getEthBalance")
	public ResponseEntity<Object> getBalance(@RequestParam String address) {
		try {
			
			
			Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2"));

			
		    EthGetBalance ethGetBalance=
		    web3.ethGetBalance(address,DefaultBlockParameter.valueOf("LATEST")).sendAsync().get();

		    BigInteger wei = ethGetBalance.getBalance();
		    java.math.BigDecimal tokenValue = Convert.fromWei(String.valueOf(wei), Convert.Unit.ETHER);
		    String strTokenAmount = String.valueOf(tokenValue);
							return ResponseHandler.response(strTokenAmount, "", true, HttpStatus.OK);
			
		
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}

		@GetMapping(value = "/deposits")
	public ResponseEntity<Object> deposits(@RequestParam String address) {
		try {
			String url = "http://api-ropsten.etherscan.io/api";

			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("module", "account");
			params.put("action", "txlist");
			params.put("address", address);
			params.put("sort", "desc");


			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
			    builder.queryParam(entry.getKey(), entry.getValue());
			}

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity(headers), String.class);
			ObjectMapper objectMapper = new ObjectMapper();

			// Deserialization into the `Employee` class
			ResultDto result = objectMapper.readValue(response.getBody().toString(), ResultDto.class);
            
				return ResponseHandler.response(result, "", true, HttpStatus.OK);
			
		
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/transactions")
	public ResponseEntity<Object> transactions(@RequestParam String address) {
		try {
			String url = "https://api-ropsten.etherscan.io/api";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("module", "account");
			params.put("action", "tokentx");
			params.put("contractaddress", "0x9B93C862Bbe1ff1916D36962100A6139764977F4");
			params.put("address", address);
			params.put("page", "1");
			params.put("offset", "100");
			params.put("sort", "asc");


			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
			    builder.queryParam(entry.getKey(), entry.getValue());
			}

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(headers), String.class);
			
				return ResponseHandler.response(response.getBody(), "", true, HttpStatus.OK);
			
		
		
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping(value = "/getErcBalance")
	public ResponseEntity<Object> getErcBalance(@RequestParam String address) {
		try {
			
			
			String url = "https://api-ropsten.etherscan.io/api";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("module", "account");
			params.put("action", "tokenbalance");
			params.put("contractaddress", "0x9B93C862Bbe1ff1916D36962100A6139764977F4");
			params.put("address", address);
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
			    builder.queryParam(entry.getKey(), entry.getValue());
			}

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(headers), String.class);

			// ObjectMapper instantiation
			ObjectMapper objectMapper = new ObjectMapper();

			// Deserialization into the `Employee` class
			ResultDto result = objectMapper.readValue(response.getBody().toString(), ResultDto.class);
            String no=result.getResult();
            String sub=result.getResult().substring(0, result.getResult().length());
			double relsultBalance =Double.parseDouble(sub);
			double balance = relsultBalance> 0 ? relsultBalance/Math.pow(10, 18) :0;
			
			return ResponseHandler.response(balance, "", true, HttpStatus.OK);
			
		
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}

	
	@RequestMapping(value = "/transferEther", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

	public ResponseEntity<Object> transferEther(@RequestBody WithdrawDto walletDto) {
		try {
			Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2"));
			
			String privetKey = walletDto.getPrivateKey();
			Credentials credentials = Credentials.create(privetKey);
			
			String recipientAddress = walletDto.getToAddr();
			String amountToBeSent=walletDto.getAmount();
			
			TransactionReceipt receipt = Transfer.sendFunds(web3, credentials, recipientAddress, BigDecimal.valueOf(Long.valueOf(amountToBeSent)), Unit.ETHER).send();

			return ResponseHandler.response(receipt ,
					messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
					HttpStatus.BAD_REQUEST);
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}


	
	
	@RequestMapping(value = "/transferErc20Token", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

	public ResponseEntity<Object> transferErcToken(@RequestBody WithdrawDto walletDto) {
		try {
			Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/46bec8b8af6948049280bc6737cc10d2"));
			Credentials credentials = Credentials.create(walletDto.getPrivateKey());
			
			String recipientAddress = walletDto.getToAddr();
			String amountToBeSent=walletDto.getAmount();

	        String result = transferERC20Token(web3, walletDto.getFromAddr(), recipientAddress, new BigDecimal(amountToBeSent), credentials, "0x9B93C862Bbe1ff1916D36962100A6139764977F4");

			return ResponseHandler.response(result ,
					messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
					HttpStatus.BAD_REQUEST);
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
	}


	 public  String transferERC20Token(Web3j web3j,String from, String to, BigDecimal value, Credentials credentials, String contractAddress) throws IOException, InterruptedException, ExecutionException 
	 {
	        BigInteger nonce;
	        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
	        if (ethGetTransactionCount == null) {
	            return null;
	        }
	        nonce = ethGetTransactionCount.getTransactionCount();
	                 //gasPrice and gasLimit can be set manually
	        BigInteger gasPrice;
	        EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
	        if (ethGasPrice == null) {
	            return null;
	        }
	        //gasPrice = ethGasPrice.getGasPrice();
	        gasPrice=BigInteger.valueOf(2000000000L);
	                 //BigInteger.valueOf(4300000L) If the transaction fails, it is probably a problem with the setting of the fee.
	        //BigInteger gasLimit = BigInteger.valueOf(60000L);
	        BigInteger gasLimit = BigInteger.valueOf(151754L) ;                                         
	                 //ERC20 token contract method
	      //  value = value.multiply(VALUE);
	        final Uint256 uint256 = new Uint256(value.multiply(BigDecimal.TEN.pow(18)).toBigInteger());

	        Function function = new Function(
	                "transfer",
	                Arrays.asList(new Address(to), uint256),
	                Collections.singletonList(new TypeReference<Type>() {
	                }));
	        String encodedFunction = FunctionEncoder.encode(function);
	        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
	                contractAddress, encodedFunction);

	                 //Signature Transaction
	        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
	        String hexValue = Numeric.toHexString(signMessage);
	                 //Send the transaction
	        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
	        String hash = ethSendTransaction.getTransactionHash();
	        if (hash != null) {
	            return hash;
	        }
	        return null;
	    }
	
	
	
	}


