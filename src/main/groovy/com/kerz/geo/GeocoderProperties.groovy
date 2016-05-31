package com.kerz.geo

import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull

import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.springframework.boot.context.properties.ConfigurationProperties

@CompileStatic
@ConfigurationProperties(prefix='geocoder')
class GeocoderProperties {
  @NotNull
  Class<? extends Geocoder> clazz
  
  @NotNull
  String uri

  @NotNull
  String key

  String country

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this)
  }
}
