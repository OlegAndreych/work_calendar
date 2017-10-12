# work calendar
Russian work calendar.

This app takes work calendar data set from http://data.gov.ru/opendata/7708660670-proizvcalendar and publish converted version as iCalendar (ics) on http root path.

You can find hosted version at http://workcalendar.olegandreych.tk/.

To run this app by yourself you should specify `open.data.accessToken` property with data.gov.ru access token as value in application.properties file or as environment variable in according to [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-relaxed-binding).  
Some standard Spring Boot properties may also apply.
