package example.company.tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.time.format.FormatStyle;
import java.util.Locale;

public abstract class TimeFormatter extends SimpleTagSupport {
    protected Locale locale;
    protected FormatStyle style = FormatStyle.SHORT;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setStyle(String styleTag) {
        style = FormatStyle.valueOf(styleTag.toUpperCase());
    }
}
