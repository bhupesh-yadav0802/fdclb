/*
 * package com.oodles.utils; import java.io.IOException; import
 * java.math.BigInteger; import java.util.ArrayList; import java.util.Arrays;
 * import java.util.Collections; import java.util.List; import
 * java.util.concurrent.Future;
 * 
 * import org.web3j.abi.EventEncoder; import org.web3j.abi.EventValues; import
 * org.web3j.abi.FunctionEncoder; import org.web3j.abi.TypeReference; import
 * org.web3j.abi.datatypes.Address; import org.web3j.abi.datatypes.Event; import
 * org.web3j.abi.datatypes.Function; import org.web3j.abi.datatypes.Type; import
 * org.web3j.abi.datatypes.Utf8String; import
 * org.web3j.abi.datatypes.generated.Uint256; import
 * org.web3j.abi.datatypes.generated.Uint8; import org.web3j.crypto.Credentials;
 * import org.web3j.protocol.Web3j; import
 * org.web3j.protocol.core.DefaultBlockParameter; import
 * org.web3j.protocol.core.RemoteCall; import
 * org.web3j.protocol.core.methods.request.EthFilter; import
 * org.web3j.protocol.core.methods.response.Log; import
 * org.web3j.protocol.core.methods.response.TransactionReceipt; import
 * org.web3j.protocol.exceptions.TransactionException; import
 * org.web3j.tx.Contract; import org.web3j.tx.TransactionManager;
 * 
 * import rx.Observable; import rx.functions.Func1;
 * 
 *//**
	 * Auto generated code.<br>
	 * <strong>Do not modify!</strong><br>
	 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j
	 * command line tools</a>, or
	 * {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
	 *
	 * <p>
	 * Generated with web3j version 2.3.1.
	 *//*
		 * public final class Erc20TokenWrapper extends Contract {
		 * 
		 * private static final String BINARY =
		 * "0x608060405234801561001057600080fd5b506040516020806115f68339810180604052810190808051906020019092919050505033600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550601260ff16600a0a620f424002600081905550600054600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508060048190555050611509806100ed6000396000f3006080604052600436106100fc576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde0314610101578063095ea7b31461019157806318160ddd146101f657806323b872dd146102215780633018205f146102a6578063313ce567146102fd5780633cebb8231461032e57806340c10f191461037157806348f1cfdb146103d6578063602bc62b1461040357806370a082311461042e5780638ef16d021461048557806395d89b41146104c857806399d8404514610558578063a9059cbb146105b3578063cc48191214610618578063dd62ed3e1461065b578063f77c4791146106d2575b600080fd5b34801561010d57600080fd5b50610116610729565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561015657808201518184015260208101905061013b565b50505050905090810190601f1680156101835780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561019d57600080fd5b506101dc600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610762565b604051808215151515815260200191505060405180910390f35b34801561020257600080fd5b5061020b6108eb565b6040518082815260200191505060405180910390f35b34801561022d57600080fd5b5061028c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506108f4565b604051808215151515815260200191505060405180910390f35b3480156102b257600080fd5b506102bb610940565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561030957600080fd5b5061031261096a565b604051808260ff1660ff16815260200191505060405180910390f35b34801561033a57600080fd5b5061036f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061096f565b005b34801561037d57600080fd5b506103bc600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610a0f565b604051808215151515815260200191505060405180910390f35b3480156103e257600080fd5b5061040160048036038101908080359060200190929190505050610b77565b005b34801561040f57600080fd5b50610418610bdd565b6040518082815260200191505060405180910390f35b34801561043a57600080fd5b5061046f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610be7565b6040518082815260200191505060405180910390f35b34801561049157600080fd5b506104c6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c30565b005b3480156104d457600080fd5b506104dd610ce7565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561051d578082015181840152602081019050610502565b50505050905090810190601f16801561054a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561056457600080fd5b50610599600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d20565b604051808215151515815260200191505060405180910390f35b3480156105bf57600080fd5b506105fe600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610d76565b604051808215151515815260200191505060405180910390f35b34801561062457600080fd5b50610659600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610dc0565b005b34801561066757600080fd5b506106bc600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e77565b6040518082815260200191505060405180910390f35b3480156106de57600080fd5b506106e7610efe565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6040805190810160405280600a81526020017f4644434c4220544553540000000000000000000000000000000000000000000081525081565b60008082141580156107f157506000600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205414155b156107fb57600080fd5b81600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925846040518082815260200191505060405180910390a36001905092915050565b60008054905090565b600083836004544310158061091f575061090d82610d20565b801561091e575061091d81610d20565b5b5b151561092a57600080fd5b610935868686610f24565b925050509392505050565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b601281565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156109cb57600080fd5b80600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610a6d57600080fd5b610a82826000546112b190919063ffffffff16565b600081905550610ada82600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546112b190919063ffffffff16565b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff1660007fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a36001905092915050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610bd357600080fd5b8060048190555050565b6000600454905090565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610c8c57600080fd5b6001600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6040805190810160405280600581526020017f4644434c4200000000000000000000000000000000000000000000000000000081525081565b6000600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b6000338360045443101580610da15750610d8f82610d20565b8015610da05750610d9f81610d20565b5b5b1515610dac57600080fd5b610db685856112cf565b9250505092915050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e1c57600080fd5b6000600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6000600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600081600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410158015610ff1575081600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410155b8015610ffd5750600082115b151561100857600080fd5b61105a82600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546114c490919063ffffffff16565b600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506110ef82600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546112b190919063ffffffff16565b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506111c182600260008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546114c490919063ffffffff16565b600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a3600190509392505050565b60008082840190508381101515156112c557fe5b8091505092915050565b600081600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054101580156113205750600082115b151561132b57600080fd5b61137d82600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546114c490919063ffffffff16565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061141282600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546112b190919063ffffffff16565b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a36001905092915050565b60008282111515156114d257fe5b8183039050929150505600a165627a7a723058204bca31e9657cec5ebdb336eb38ea9466de4737c60fc09ff8b833c2be0cf6d0c6002900000000000000000000000000000000000000000000000000000000000004d2";
		 * 
		 * private Erc20TokenWrapper(String contractAddress, Web3j web3j, Credentials
		 * credentials, BigInteger gasPrice, BigInteger gasLimit) { super(BINARY,
		 * contractAddress, web3j, credentials, gasPrice, gasLimit); }
		 * 
		 * private Erc20TokenWrapper(String contractAddress, Web3j web3j,
		 * TransactionManager transactionManager, BigInteger gasPrice, BigInteger
		 * gasLimit) { super(BINARY, contractAddress, web3j, transactionManager,
		 * gasPrice, gasLimit); }
		 * 
		 * public List<TransferEventResponse> getTransferEvents(TransactionReceipt
		 * transactionReceipt) { final Event event = new Event("Transfer",
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new
		 * TypeReference<Address>() {}), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Uint256>() {})); List<EventValues> valueList =
		 * extractEventParameters(event, transactionReceipt);
		 * ArrayList<TransferEventResponse> responses = new
		 * ArrayList<TransferEventResponse>(valueList.size()); for (EventValues
		 * eventValues : valueList) { TransferEventResponse typedResponse = new
		 * TransferEventResponse(); typedResponse._from = (Address)
		 * eventValues.getIndexedValues().get(0); typedResponse._to = (Address)
		 * eventValues.getIndexedValues().get(1); typedResponse._value = (Uint256)
		 * eventValues.getNonIndexedValues().get(0); responses.add(typedResponse); }
		 * return responses; }
		 * 
		 * public Observable<TransferEventResponse>
		 * transferEventObservable(DefaultBlockParameter startBlock,
		 * DefaultBlockParameter endBlock) { final Event event = new Event("Transfer",
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new
		 * TypeReference<Address>() {}), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Uint256>() {})); EthFilter filter = new EthFilter(startBlock,
		 * endBlock, getContractAddress());
		 * filter.addSingleTopic(EventEncoder.encode(event)); return
		 * web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
		 * 
		 * @Override public TransferEventResponse call(Log log) { EventValues
		 * eventValues = extractEventParameters(event, log); TransferEventResponse
		 * typedResponse = new TransferEventResponse(); typedResponse._from = (Address)
		 * eventValues.getIndexedValues().get(0); typedResponse._to = (Address)
		 * eventValues.getIndexedValues().get(1); typedResponse._value = (Uint256)
		 * eventValues.getNonIndexedValues().get(0); typedResponse._transactionHash =
		 * log.getTransactionHash(); return typedResponse; } }); }
		 * 
		 * public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt
		 * transactionReceipt) { final Event event = new Event("Approval",
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new
		 * TypeReference<Address>() {}), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Uint256>() {})); List<EventValues> valueList =
		 * extractEventParameters(event, transactionReceipt);
		 * ArrayList<ApprovalEventResponse> responses = new
		 * ArrayList<ApprovalEventResponse>(valueList.size()); for (EventValues
		 * eventValues : valueList) { ApprovalEventResponse typedResponse = new
		 * ApprovalEventResponse(); typedResponse._owner = (Address)
		 * eventValues.getIndexedValues().get(0); typedResponse._spender = (Address)
		 * eventValues.getIndexedValues().get(1); typedResponse._value = (Uint256)
		 * eventValues.getNonIndexedValues().get(0); responses.add(typedResponse); }
		 * return responses; }
		 * 
		 * public Observable<ApprovalEventResponse>
		 * approvalEventObservable(DefaultBlockParameter startBlock,
		 * DefaultBlockParameter endBlock) { final Event event = new Event("Approval",
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new
		 * TypeReference<Address>() {}), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Uint256>() {})); EthFilter filter = new EthFilter(startBlock,
		 * endBlock, getContractAddress());
		 * filter.addSingleTopic(EventEncoder.encode(event)); return
		 * web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
		 * 
		 * @Override public ApprovalEventResponse call(Log log) { EventValues
		 * eventValues = extractEventParameters(event, log); ApprovalEventResponse
		 * typedResponse = new ApprovalEventResponse(); typedResponse._owner = (Address)
		 * eventValues.getIndexedValues().get(0); typedResponse._spender = (Address)
		 * eventValues.getIndexedValues().get(1); typedResponse._value = (Uint256)
		 * eventValues.getNonIndexedValues().get(0); typedResponse._transactionHash =
		 * log.getTransactionHash(); return typedResponse; } }); }
		 * 
		 * public Future<Utf8String> name() throws IOException { Function function = new
		 * Function("name", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Utf8String>() {})); return
		 * executeCallSingleValueReturn(function); }
		 * 
		 * public TransactionReceipt approve(Address _spender, Uint256 _amount) throws
		 * IOException, TransactionException { Function function = new
		 * Function("approve", Arrays.<Type>asList(_spender, _amount),
		 * Collections.<TypeReference<?>>emptyList()); return
		 * executeTransaction(function); }
		 * 
		 * public Future<Uint256> totalSupply() throws IOException { Function function =
		 * new Function("totalSupply", Arrays.<Type>asList(),
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {})); return
		 * executeCallSingleValueReturn(function); }
		 * 
		 * public TransactionReceipt transferFrom(Address _from, Address _to, Uint256
		 * _amount) throws IOException, TransactionException { Function function = new
		 * Function("transferFrom", Arrays.<Type>asList(_from, _to, _amount),
		 * Collections.<TypeReference<?>>emptyList()); return
		 * executeTransaction(function); }
		 * 
		 * public Uint8 decimals() throws IOException { Function function = new
		 * Function("decimals", Arrays.<Type>asList(),
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {})); return
		 * executeCallSingleValueReturn(function); }
		 * 
		 * public Uint256 balanceOf(Address _owner) throws IOException { Function
		 * function = new Function("balanceOf", Arrays.<Type>asList(_owner),
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {})); return
		 * executeCallSingleValueReturn(function); }
		 * 
		 * public Future<Address> owner() throws IOException { Function function = new
		 * Function("owner", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Address>() {})); return executeCallSingleValueReturn(function);
		 * }
		 * 
		 * public Future<Utf8String> symbol() throws IOException { Function function =
		 * new Function("symbol", Arrays.<Type>asList(),
		 * Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {})); return
		 * executeCallSingleValueReturn(function); }
		 * 
		 * public TransactionReceipt transfer(Address _to, Uint256 _amount) throws
		 * IOException, TransactionException { Function function = new
		 * Function("transfer", Arrays.<Type>asList(_to, _amount),
		 * Collections.<TypeReference<?>>emptyList()); return
		 * executeTransaction(function); }
		 * 
		 * public Future<Uint256> allowance(Address _owner, Address _spender) throws
		 * IOException { Function function = new Function("allowance",
		 * Arrays.<Type>asList(_owner, _spender), Arrays.<TypeReference<?>>asList(new
		 * TypeReference<Uint256>() {})); return executeCallSingleValueReturn(function);
		 * }
		 * 
		 * public static RemoteCall<Erc20TokenWrapper> deploy(Web3j web3j, Credentials
		 * credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger
		 * initialWeiValue, Uint256 totalSupply, Utf8String tokenName, Uint8
		 * decimalUnits, Utf8String tokenSymbol) { String encodedConstructor =
		 * FunctionEncoder.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName,
		 * decimalUnits, tokenSymbol)); return deployRemoteCall(Erc20TokenWrapper.class,
		 * web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor,
		 * initialWeiValue); }
		 * 
		 * public static RemoteCall<Erc20TokenWrapper> deploy(Web3j web3j,
		 * TransactionManager transactionManager, BigInteger gasPrice, BigInteger
		 * gasLimit, BigInteger initialWeiValue, Uint256 totalSupply, Utf8String
		 * tokenName, Uint8 decimalUnits, Utf8String tokenSymbol) { String
		 * encodedConstructor =
		 * FunctionEncoder.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName,
		 * decimalUnits, tokenSymbol)); return deployRemoteCall(Erc20TokenWrapper.class,
		 * web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor,
		 * initialWeiValue); }
		 * 
		 * public static Erc20TokenWrapper load(String contractAddress, Web3j web3j,
		 * Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) { return
		 * new Erc20TokenWrapper(contractAddress, web3j, credentials, gasPrice,
		 * gasLimit); }
		 * 
		 * public static Erc20TokenWrapper load(String contractAddress, Web3j web3j,
		 * TransactionManager transactionManager, BigInteger gasPrice, BigInteger
		 * gasLimit) { return new Erc20TokenWrapper(contractAddress, web3j,
		 * transactionManager, gasPrice, gasLimit); }
		 * 
		 * public static class TransferEventResponse { public Address _from;
		 * 
		 * public Address _to;
		 * 
		 * public Uint256 _value;
		 * 
		 * public String _transactionHash; }
		 * 
		 * public static class ApprovalEventResponse { public Address _owner;
		 * 
		 * public Address _spender;
		 * 
		 * public Uint256 _value;
		 * 
		 * public String _transactionHash; } }
		 */