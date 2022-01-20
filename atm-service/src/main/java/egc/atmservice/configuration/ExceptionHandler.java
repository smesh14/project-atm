package egc.atmservice.configuration;

import egc.atmservice.exception.AtmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class ExceptionHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String responseMessage = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
        log.warn("Integration error: " + responseMessage);
        throw new AtmException(responseMessage);
    }
}
