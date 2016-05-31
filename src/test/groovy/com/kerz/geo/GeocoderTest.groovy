package com.kerz.geo

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.kerz.geo.mapzen.MapzenGeocoder

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = [
  HttpClientConfiguration,
  HttpClientGeocoderConfiguration,
  GeocoderTestConfiguration
  ]
)
class GeocoderTest {

  static Logger log = LoggerFactory.getLogger(GeocoderTest)
  
  static String address = '151 Farmington Ave, Hartford CT 06108'
  
  @Autowired
  MapzenGeocoder geocoder
  
  @Autowired
  FailingGeocoder failingGeocoder
  
  @Before
  void setUp() {
    log.debug('setup')
  }

  @After
  void tearDown() {
    log.debug('teardown')
  }

  @Test
  void shouldWork() {
    Point point = geocoder.geocode(address)
    assertNotNull(point)
  }
  
  @Test(expected = Exception)
  void shouldFail() {
    Point point = geocoder.geocode(address, uri: 'http://no.work.ie')
  }
  
  @Test(expected = RuntimeException)
  void shouldFailBad() {
    Point point = failingGeocoder.geocode(address)
  }
}
