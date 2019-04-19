package example.company.tags;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateFormatterTag extends TimeFormatter {
    private LocalDate value;

    public void setValue(LocalDate value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write(format());
    }

    private String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(style).withLocale(locale);
        return formatter.format(value);
    }
}
