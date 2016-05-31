package com.kerz.geo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties([GeocoderProperties])
public class GeocoderTestConfiguration {
  
  @Autowired
  GeocoderProperties props
  
  @Bean
  FailingGeocoder failingGeocoder() {
    new FailingGeocoder(uri: props.uri, key: props.key, country: props.country)
  }
}