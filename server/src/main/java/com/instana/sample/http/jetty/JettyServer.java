/*
 * (c) Copyright IBM Corp. 2021
 * (c) Copyright Instana Inc.
 */
package com.instana.sample.http.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.Servlet;

public class JettyServer {

  public static void run(int port) throws Exception {
    Server server = new Server(port);

    ServletHandler handler = new ServletHandler();
    server.setHandler(handler);

    handler.addServletWithMapping(HelloWorldServlet.class, "/");
    handler.addServletWithMapping(NameServlet.class, "/name");

    server.start();
  }
}
