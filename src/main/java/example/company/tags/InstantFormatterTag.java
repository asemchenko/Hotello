package example.company.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class InstantFormatterTag extends SimpleTagSupport {
    private Instant value;
    private Locale locale;
    private FormatStyle style = FormatStyle.SHORT;

    public void setValue(Instant value) {
        this.value = value;
    }

    /**
     * @param locale consists of two parts - ISO-639 language code and ISO-3166 country code, e.g. en_US
     */
    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale.replace('_', '-'));
    }

    public void setStyle(String styleTag) {
        style = FormatStyle.valueOf(styleTag.toUpperCase());
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write(format());
    }

    private String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(locale).withZone(ZoneId.systemDefault());
        return formatter.format(value);
    }



}
