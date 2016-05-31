package com.kerz.geo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.kerz.geo.mapzen.MapzenGeocoder;;;

@Configuration
@EnableConfigurationProperties([GeocoderProperties])
public class HttpClientGeocoderConfiguration {
  
  @Autowired
  GeocoderProperties props
  
  @Bean
  Geocoder geocoder() {
    def geocoder = props.clazz.newInstance()
    geocoder.uri = props.uri
    geocoder.key = props.key
    geocoder.country = props.country
    geocoder
  }
}