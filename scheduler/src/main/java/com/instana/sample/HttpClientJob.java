/*
 * (c) Copyright IBM Corp. 2021
 * (c) Copyright Instana Inc.
 */
package com.instana.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Random;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpClientJob implements Runnable {

  private static final Logger logger = LogManager.getLogger(App.class);

  public void run() {
    // Randomize the calls
    Random rand = new Random();
    int rand_int = rand.nextInt(10);

    // if rand_int > 2, happy calls; otherwise, error it out
    if (rand_int > 2) {
      // call / endpoint
      helloworld();
      // call /name endpoint
      name();
    } else {
      // call something with error
      try{
        error("http://nonexistingfakefqdn");
      } catch (Exception e) {
        // do nothing to make things 
      }
    }
  }

  public void helloworld() {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpUriRequest request = new HttpGet("http://localhost:8090/");
    try (CloseableHttpResponse response = httpClient.execute(request)) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String result = reader.readLine();
      logger.info("Received " + response.getStatusLine() + " with body: " + result);
    } catch (Exception e) {
      logger.error("Could not call " + request.getURI() + ". Error: " + e.getMessage());
    }
  }

  public String name() {
    String result = "";
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpUriRequest request = new HttpGet("http://localhost:8090/name");
    try (CloseableHttpResponse response = httpClient.execute(request)) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      result = reader.readLine();
      logger.info("Received " + response.getStatusLine() + " with body: " + result);
    } catch (Exception e) {
      logger.error("Could not call " + request.getURI() + ". Error: " + e.getMessage());
    }

    return result;
  }

  public void error(String url) throws MalformedURLException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpUriRequest request = new HttpGet(url);
    try (CloseableHttpResponse response = httpClient.execute(request)) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String result = reader.readLine();
      logger.info("Received " + response.getStatusLine() + " with body: " + result);
    } catch (Exception e) {
      logger.error("Could not call " + request.getURI() + ". Error: " + e.getMessage());
      throw new MalformedURLException(url);
    }
  }
}
