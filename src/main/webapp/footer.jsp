<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer class="mt-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; 2019 Hotello Inc.</p>
    <div class="w-25 mx-auto">
        <select class="custom-select w-50 mx-auto" data-width="fit" onchange="updateLanguage(this)"
                id="langSelector">
            <option <%--data-content='<span class="flag-icon flag-icon-us"></span> English'--%> value="en_US">English
            </option>
            <option  <%--data-content='<span class="flag-icon flag-icon-mx"></span> Español'--%> value="uk_UA">
                Українська
            </option>
        </select></div>
    <script type="text/javascript">
        // setting current language
        setCurrentLanguage('<c:out value="${locale}" />');

        function updateLanguage(langSelector) {
            var langTag = langSelector.value;
            // saving language tag in cookies
            var nextYearDate = new Date(Date.now() + 1000 * 60 * 60 * 24 * 365);
            setCookie('locale', langTag, {'path': '/', 'expires': nextYearDate});
            location.reload();
        }

        function setCookie(name, value, options) {
            options = options || {};

            var expires = options.expires;

            if (typeof expires == "number" && expires) {
                var d = new Date();
                d.setTime(d.getTime() + expires * 1000);
                expires = options.expires = d;
            }
            if (expires && expires.toUTCString) {
                options.expires = expires.toUTCString();
            }

            value = encodeURIComponent(value);

            var updatedCookie = name + "=" + value;

            for (var propName in options) {
                updatedCookie += "; " + propName;
                var propValue = options[propName];
                if (propValue !== true) {
                    updatedCookie += "=" + propValue;
                }
            }

            document.cookie = updatedCookie;
        }

        function setCurrentLanguage(langTag) {
            document.getElementById('langSelector').value = langTag;
        }
    </script>
</footer>