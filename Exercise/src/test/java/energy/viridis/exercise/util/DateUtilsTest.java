package energy.viridis.exercise.util;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;


class DateUtilsTest {
    private static final int DAY = 9;
    private static final int MONTH = 2;
    private static final int YEAR = 2015;

    private static final int HOUR = 16;
    private static final int MINUTE = 20;

    private static final String DATE_PATTERN_VALID = DateUtils.ISO8601_COMPLETE_DATE;
    private static final String DATE_PATTERN_NULL = null;
    private static final String DATE_PATTERN_EMPTY = "";
    private static final String DATE_PATTERN_INVALID = "xxxx-xx-xx";

    private static final Date DATE;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, YEAR);
        calendar.set(Calendar.MONTH, MONTH - 1);
        calendar.set(Calendar.DAY_OF_MONTH, DAY);
        DATE = calendar.getTime();
    }

    private static final LocalDate LOCAL_DATE = LocalDate.of(YEAR, MONTH, DAY);

    private static final String DATE_STRING = String.format("%s-%02d-%02d", YEAR, MONTH, DAY);
    private static final String DEFAULT_DATE_STRING = String.format("%s-%02d-%02d %02d:%02d", DAY, MONTH, YEAR, HOUR, MINUTE );

    private static final String DATE_STRING_NULL = null;
    private static final String DATE_STRING_EMPTY = "";
    private static final String DATE_STRING_INVALID = "xxxx-xx-xx";

    @Test
    public void testStringToDateWithDateFillAndPatternValidSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING, DATE_PATTERN_VALID);
        assertNotNull(date);
        LocalDate localDate = DateUtils.dateToLocalDate(date);
        assertEquals(YEAR, localDate.getYear());
        assertEquals(MONTH, localDate.getMonthValue());
        assertEquals(DAY, localDate.getDayOfMonth());
    }

    @Test
    public void shouldStringToDefultDateWithDateFillAndPatternValidSuccess() {
        Date date = DateUtils.stringToDefaultDate(DEFAULT_DATE_STRING);
        assertThat(date, is(notNullValue()));
        LocalDateTime localDateTime = DateUtils.dateToLocalDateTime(date);
        assertEquals(YEAR, localDateTime.getYear());
        assertEquals(MONTH, localDateTime.getMonthValue());
        assertEquals(DAY, localDateTime.getDayOfMonth());
        assertEquals(HOUR, localDateTime.getHour());
        assertEquals(MINUTE, localDateTime.getMinute());
    }

    @Test
    public void testStringToDateWithDateFillAndPatternInvalidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING, DATE_PATTERN_INVALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateFillAndPatternEmptyTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING, DATE_PATTERN_EMPTY);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateFillAndPatternNullTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING, DATE_PATTERN_NULL);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateNullAndPatternValidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_NULL, DATE_PATTERN_VALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateNullAndPatternInvalidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_NULL, DATE_PATTERN_INVALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateNullAndPatternEmptyTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_NULL, DATE_PATTERN_EMPTY);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateNullAndPatternNullTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_NULL, DATE_PATTERN_NULL);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateEmptyAndPatternValidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_EMPTY, DATE_PATTERN_VALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateEmptyAndPatternInvalidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_EMPTY, DATE_PATTERN_INVALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateEmptyAndPatternEmptyTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_EMPTY, DATE_PATTERN_EMPTY);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateEmptyAndPatternNullTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_EMPTY, DATE_PATTERN_NULL);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateInvalidAndPatternValidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_INVALID, DATE_PATTERN_VALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateInvalidAndPatternInvalidTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_INVALID, DATE_PATTERN_INVALID);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateInvalidAndPatternEmptyTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_INVALID, DATE_PATTERN_EMPTY);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testStringToDateWithDateInvalidAndPatternNullTreatExceptionSuccess() {
        Date date = DateUtils.stringToDate(DATE_STRING_INVALID, DATE_PATTERN_NULL);
        assertThat(date, is(nullValue()));
    }

    @Test
    public void testDateToStringSuccess() {
        String date = DateUtils.dateToString(DATE, DATE_PATTERN_VALID);
        assertNotNull(date);
        assertEquals(DATE_STRING, date);
    }

    @Test
    public void testDateToLocalDateSuccess() {
        LocalDate localDate = DateUtils.dateToLocalDate(DATE);
        assertNotNull(localDate);
        assertEquals(YEAR, localDate.getYear());
        assertEquals(MONTH, localDate.getMonthValue());
        assertEquals(DAY, localDate.getDayOfMonth());
    }

    @Test
    public void testGetCurrentimeSucess() {

        Date now = DateUtils.localTimeToDate(LocalDateTime.now());
        Date expectedNow = DateUtils.getCurrentTime();
        assertEquals(DateUtils.dateToString(now, DATE_PATTERN_VALID), DateUtils.dateToString(expectedNow, DATE_PATTERN_VALID));

    }

    @Test
    public void testDateTimeToStringSuccess() {
        String date = DateUtils.dateTimeToString(DATE, DATE_PATTERN_VALID);
        assertNotNull(date);
        assertEquals(DATE_STRING, date);
    }


    @Test
    public void testLocalDateToDateSuccess() {
        Date date = DateUtils.localDateToDate(LOCAL_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertNotNull(date);
        assertEquals(YEAR, calendar.get(Calendar.YEAR));
        assertEquals(MONTH, calendar.get(Calendar.MONTH) + 1);
        assertEquals(DAY, calendar.get(Calendar.DAY_OF_MONTH));
    }

}