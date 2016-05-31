package com.kerz.geo

import groovy.json.JsonOutput

import org.apache.http.HttpEntity
import org.apache.http.HttpStatus
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.util.EntityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

abstract class HttpClientGeocoder implements Geocoder {
  static Logger log = LoggerFactory.getLogger(HttpClientGeocoder)

  String uri
  String key
  String country

  @Autowired
  HttpClient httpClient

  Point geocode(Map options = [:], String address) {
    URIBuilder builder = new URIBuilder(options.uri ?: uri)

    getParamMap(
      address, 
      key: options.key ?: key, 
      country: options.country ?: country ?: 'USA'
      ).each { k, v -> builder.addParameter(k, v) }

    String url = builder.build()
    HttpGet httpGet = new HttpGet(url)
    CloseableHttpResponse response = httpClient.execute(httpGet)
    Point point
    try {
      int status = response.getStatusLine().getStatusCode()
      log.debug("geocode: url=[$url], status=[$status]")
      if (status == HttpStatus.SC_OK) {
        HttpEntity entity = response.getEntity()
        String responseString = EntityUtils.toString(entity)
        log.trace("geocode: response=${JsonOutput.prettyPrint(responseString)}")
        point = getPoint(responseString)
        log.debug("geocode: point=$point")
      }
      else
      {
        throw new RuntimeException("call to [$url] returned bad status code [$status]")
      }
    } finally {
      response.close()
    }

    point
  }
  
  abstract Map getParamMap(Map options = [:], String address)
  abstract Point getPoint(String response)
}