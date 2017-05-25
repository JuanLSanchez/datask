package es.juanlsanchez.datask.web.util.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 *
 */
public class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    public static HttpHeaders createAlert(String message, String param) {
	HttpHeaders headers = new HttpHeaders();
	headers.add("X-apiJwtApp-alert", message);
	headers.add("X-apiJwtApp-params", param);
	return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
	return createAlert("bm."
		+ entityName
		+ ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
	return createAlert("bm."
		+ entityName
		+ ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
	return createAlert("bm."
		+ entityName
		+ ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
	log.error("Entity creation failed, {}", defaultMessage);
	HttpHeaders headers = new HttpHeaders();
	headers.add("X-apiJwtApp-error", "error."
		+ errorKey);
	headers.add("X-apiJwtApp-params", entityName);
	return headers;
    }
}
