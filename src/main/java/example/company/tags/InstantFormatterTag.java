package example.company.tags;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantFormatterTag extends TimeFormatter {
    private Instant value;

    public void setValue(Instant value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write(format());
    }

    private String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(style)
                .withLocale(locale).withZone(ZoneId.systemDefault());
        return formatter.format(value);
    }


}
