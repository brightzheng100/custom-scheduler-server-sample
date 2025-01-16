/*
 * (c) Copyright IBM Corp. 2021
 * (c) Copyright Instana Inc.
 */

package com.instana.sample;

import com.instana.sample.http.jetty.HelloWorldServlet;
import com.instana.sample.http.jetty.JettyServer;

import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) throws Exception {
    JettyServer.run(8090);
  }
}
