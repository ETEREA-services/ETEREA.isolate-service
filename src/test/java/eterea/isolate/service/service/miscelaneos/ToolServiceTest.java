package eterea.isolate.service.service.miscelaneos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ToolServiceTest {

    @Test
    void stringDDMMYYYY2OffsetDateTime_validDate() {
        OffsetDateTime result = ToolService.stringDDMMYYYY2OffsetDateTime("15072025");
        assertEquals(2025, result.getYear());
        assertEquals(7, result.getMonthValue());
        assertEquals(15, result.getDayOfMonth());
    }

    @Test
    void onlyNumbers_extractsDigits() {
        assertEquals("123456", ToolService.onlyNumbers("abc123def456"));
        assertEquals("", ToolService.onlyNumbers("abc"));
        assertEquals("7890", ToolService.onlyNumbers("7-8-9-0"));
    }

    @Test
    void number_2_text_entero_variosCasos() {
        assertTrue(ToolService.number_2_text_entero(new BigDecimal("1")).contains("uno"));
        assertTrue(ToolService.number_2_text_entero(new BigDecimal("1000")).contains("mil"));
        assertTrue(ToolService.number_2_text_entero(new BigDecimal("1000000")).contains("millon"));
    }

    @Test
    void number_2_text_centavos() {
        String texto = ToolService.number_2_text(new BigDecimal("123.45"));
        assertTrue(texto.contains("con 45/100"));
    }

    @Test
    void hourAbsoluteArgentina_and_dateAbsoluteArgentina() {
        OffsetDateTime hour = ToolService.hourAbsoluteArgentina();
        OffsetDateTime date = ToolService.dateAbsoluteArgentina();
        assertEquals(0, date.getHour());
        assertEquals(0, date.getMinute());
        assertEquals(0, date.getSecond());
        assertEquals(0, date.getNano());
        assertEquals(hour.getOffset(), ZoneOffset.UTC);
    }

    @Test
    void lastDayOfMonth_and_firstTime_lastTime() {
        OffsetDateTime lastDay = ToolService.lastDayOfMonth(2025, 2);
        assertEquals(28, lastDay.getDayOfMonth());
        OffsetDateTime first = ToolService.firstTime(lastDay);
        OffsetDateTime last = ToolService.lastTime(lastDay);
        assertEquals(0, first.getHour());
        assertEquals(23, last.getHour());
        assertEquals(59, last.getMinute());
    }

    @Test
    void dateToOffsetDateTime_convertsCorrectly() {
        Date date = new Date();
        OffsetDateTime odt = ToolService.dateToOffsetDateTime(date);
        assertEquals(date.toInstant().atOffset(ZoneOffset.UTC), odt);
    }
}
