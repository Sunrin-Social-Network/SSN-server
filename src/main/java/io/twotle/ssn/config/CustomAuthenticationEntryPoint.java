package io.twotle.ssn.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.component.ExceptionCode;
import io.twotle.ssn.dto.ErrorResponseDTO;
import lombok.Getter;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, CustomException {
        String exceptionMessage = (String) request.getAttribute("exception");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //Message message = new Message(exceptionMessage, HttpStatus.UNAUTHORIZED);
//        ErrorResponseDTO dto = new ErrorResponseDTO(0, exceptionMessage);
//        String res = this.convertObjectToJson(dto);
//        response.getWriter().print(res);
        throw new CustomException(ExceptionCode.DEFAULT_ERROR);
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        return object == null ? null : objectMapper.writeValueAsString(object);
    }
}
