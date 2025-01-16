/*
 * (c) Copyright IBM Corp. 2021
 * (c) Copyright Instana Inc.
 */

package com.instana.sample;

import com.instana.sample.scheduler.CustomScheduler;

import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) throws Exception {
    CustomScheduler scheduler = new CustomScheduler();
    scheduler.schedule(new HttpClientJob(), 2, TimeUnit.SECONDS);
  }
}
