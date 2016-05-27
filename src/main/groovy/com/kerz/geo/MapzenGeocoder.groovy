package com.kerz.geo

import com.jayway.jsonpath.JsonPath

class MapzenGeocoder extends HttpClientGeocoder {
  
  Map getParamMap(Map options = [:], String address) {
    def paramMap = [:]
    paramMap['api_key'] = options.key
    paramMap['boundary.country'] = options.country
    paramMap['size'] = '1'
    paramMap['text'] = address
    paramMap
  }
  
  Point getPoint(String response) {
    List<Float> coords = JsonPath.read(response, '$.features[0].geometry.coordinates')
    new Point(lat: coords[0], lon: coords[1])
  }
}