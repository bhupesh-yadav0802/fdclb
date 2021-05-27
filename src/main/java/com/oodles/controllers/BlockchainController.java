package com.oodles.controllers;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.oodles.dto.BlockchainDto;
import com.oodles.utils.ResponseHandler;



@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class  BlockchainController {
	
	Logger logger = LoggerFactory.getLogger(BlockchainController.class);
	
	
	@Autowired
	private MessageSource messageSource;
	
	//@Value("${nodeproject.url}")
	private String uri1 = "13.233.254.1:4011";

	@GetMapping(value = "/generateAddress")
	public ResponseEntity<Object> createAddress(@RequestParam String password) {
		try {
			
			String url = "http://"+uri1+"/fdclb/generateAddress";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("password", password);
			

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
	
	
	
	@GetMapping(value = "/getBalance")
	public ResponseEntity<Object> getBalance(@RequestParam String address) {
		try {

			String url = "http://"+uri1+"/fdclb/getBalance";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("address", address);
			

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

	
	
	@PostMapping(value = "/withdraw")
	public ResponseEntity<Object> withdraw(@RequestBody BlockchainDto walletDto) {
		try {
			String url = "http://"+uri1+"/fdclb/withdraw";

			RestTemplate restTemplate = new RestTemplate();
			JSONObject request = new JSONObject();
			request.put("fromAddr", walletDto.getFromAddr());
			request.put("toAddr", walletDto.getToAddr());
			request.put("privateKey", walletDto.getPrivateKey());
			request.put("amount", walletDto.getAmount());

			// set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

			// send request and parse result
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			if (response.getStatusCode() == HttpStatus.OK) {

				return ResponseHandler.response(response.getBody(), "", true, HttpStatus.OK);
			} else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {

				return ResponseHandler.response(false, "", true, HttpStatus.BAD_REQUEST);
			}

		
		
		}
	catch(Exception e)	
		{
		return ResponseHandler.response("",
				messageSource.getMessage("back.text", null, LocaleContextHolder.getLocale()), false,
				HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	
	
	@GetMapping(value = "/deposits")
	public ResponseEntity<Object> deposits(@RequestParam String address) {
		try {
			String url = "http://"+uri1+"/fdclb/deposits";

			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("address", address);
			

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

	@GetMapping(value = "/transactions")
	public ResponseEntity<Object> transactions(@RequestParam String address) {
		try {
			String url = "http://"+uri1+"/fdclb/transactions";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> params = new HashMap<>();
			params.put("address", address);
			

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


}
