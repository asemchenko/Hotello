package example.company.tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.time.format.FormatStyle;
import java.util.Locale;

public abstract class TimeFormatter extends SimpleTagSupport {
    protected Locale locale;
    protected FormatStyle style = FormatStyle.SHORT;

    /**
     * @param locale consists of two parts - ISO-639 language code and ISO-3166 country code, e.g. en_US
     */
    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale.replace('_', '-'));
    }

    public void setStyle(String styleTag) {
        style = FormatStyle.valueOf(styleTag.toUpperCase());
    }
}
