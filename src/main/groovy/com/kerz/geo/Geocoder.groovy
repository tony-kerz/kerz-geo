package com.kerz.geo

interface Geocoder {
  Point geocode(Map options, String address)
}
