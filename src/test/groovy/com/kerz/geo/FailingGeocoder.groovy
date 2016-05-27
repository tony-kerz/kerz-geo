package com.kerz.geo

class FailingGeocoder extends HttpClientGeocoder {
  
  Map getParamMap(Map options = [:], String address) {
    [:]
  }
  
  Point getPoint(String response) {}
}