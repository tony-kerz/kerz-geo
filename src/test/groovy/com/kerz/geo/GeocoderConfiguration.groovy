package com.kerz.geo

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties([GeocoderProperties])
public class GeocoderConfiguration {
  
  @Autowired
  GeocoderProperties props
  
  @Bean
  Geocoder geocoder() {
    new MapzenGeocoder(uri: props.uri, key: props.key, country: props.country)
  }
}