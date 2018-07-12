package com.mozcalti.training.aws.lambda.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class AWSClient {

	public static void main(String[] args) {
		BasicAWSCredentials awdCreds = new BasicAWSCredentials("DATA", "DATA");
		AWSLambda lambda = AWSLambdaAsyncClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awdCreds))
				.withRegion(Regions.US_EAST_2)
				.build();
		
		InvokeRequest request = new InvokeRequest().withFunctionName("get-widget").withPayload("{\"id\":\"1\"}");
		
		try {
			InvokeResult result = lambda.invoke(request);
			System.out.println("Status code " + result.getStatusCode());
			String json = new String(result.getPayload().array(), "UTF-8");
			System.out.println(json);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
