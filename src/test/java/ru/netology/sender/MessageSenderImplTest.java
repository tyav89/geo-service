package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;


class MessageSenderImplTest {
    private MessageSender messageSender;
    private String key = MessageSenderImpl.IP_ADDRESS_HEADER;


    @Test
    void sendShowRU() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(key,"172.0.32.11");

        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Assertions.assertEquals(messageSender.send(headers), "Добро пожаловать");
        System.out.println();
        Assertions.assertNotEquals(messageSender.send(headers), "Welcome");

    }

    @Test
    void sendShowEN() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(key,"96.44.183.149");

        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationServiceImpl localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Assertions.assertEquals(messageSender.send(headers), "Welcome");
        System.out.println();
        Assertions.assertNotEquals(messageSender.send(headers), "Добро пожаловать");
    }
}