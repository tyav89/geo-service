package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;


class LocalizationServiceImplTest {
    private LocalizationService localizationService;

    @BeforeEach
    void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    void locale() {
        Country country = Country.RUSSIA;
        Assertions.assertEquals(localizationService.locale(country), "Добро пожаловать");
    }

    @Test
    void localeNoRussia() {
        Country country = Country.BRAZIL;
        Assertions.assertEquals(localizationService.locale(country), "Welcome");
    }
}