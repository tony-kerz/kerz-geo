package com.kerz.geo

import org.apache.http.HttpEntity
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.util.EntityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

import com.jayway.jsonpath.JsonPath

class MapzenGeocoder implements Geocoder {
  static Logger log = LoggerFactory.getLogger(MapzenGeocoder)
  
  String uri
  String key
  String country
  
  @Autowired
  HttpClient httpClient

  Point geocode(String address) {
    // https://search.mapzen.com/v1/search?text=06108&api_key=search-ABCDEF&boundary.country=USA&size=1
    URIBuilder builder = new URIBuilder(uri)

    builder
        .addParameter('api_key', key)
        .addParameter('boundary.country', country ?: 'USA')
        .addParameter('size', '1')
        .addParameter('text', address)

    HttpGet httpGet = new HttpGet(builder.build())
    CloseableHttpResponse response = httpClient.execute(httpGet)
    Point point
    try {
      HttpEntity entity = response.getEntity()
      String json = EntityUtils.toString(entity)
      List<Float> coords = JsonPath.read(json, '$.features[0].geometry.coordinates')
      log.debug("response=$json, coords=$coords")
      point = new Point(lat: coords[0], lon: coords[1])
    } finally {
      response.close()
    }

    point
  }
}