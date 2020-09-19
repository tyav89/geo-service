package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class GeoServiceImplTest {
    private GeoService geoService;

    @BeforeEach
    void init() {
        geoService = new GeoServiceImpl();
    }

    @Test
    void byIpLocalHostTest() {
        String localhost = GeoServiceImpl.LOCALHOST;
        Assertions.assertEquals(geoService.byIp(localhost).getCountry(),null);
    }

    @Test
    void byIpRUTest() {
        String moscowIp = GeoServiceImpl.MOSCOW_IP;
        Assertions.assertEquals(geoService.byIp(moscowIp).getCountry(), Country.RUSSIA);
    }

    @Test
    void byIpENTest() {
        String newYorkIp = GeoServiceImpl.NEW_YORK_IP;
        Assertions.assertEquals(geoService.byIp(newYorkIp).getCountry(), Country.USA);
    }

    @Test
    void byIpUnknow() {
        String unknownIp = "1.2.3.4";
        Assertions.assertEquals(geoService.byIp(unknownIp), null);
    }


}