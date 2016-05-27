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

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = [
  HttpClientConfiguration,
  GeocoderConfiguration
  ]
)
class GeocoderTest {

  Logger log = LoggerFactory.getLogger(GeocoderTest)
  
  @Autowired
  Geocoder geocoder
  
  @Before
  void setUp() throws Exception {
    log.debug('setup')
  }

  @After
  void tearDown() throws Exception {
    log.debug('teardown')
  }

  @Test
  void shouldWork() throws Exception {
    String address = '151 Farmington Ave, Hartford CT 06108'
    Point point = geocoder.geocode(address)
    assertNotNull(point)
    log.info("geocoded address=[$address] to point=$point")
  }
}
