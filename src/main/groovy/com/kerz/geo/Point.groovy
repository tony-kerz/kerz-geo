package com.kerz.geo

import org.apache.commons.lang3.builder.ReflectionToStringBuilder

class Point {
  float lat
  float lon
  
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this)
  }
}
