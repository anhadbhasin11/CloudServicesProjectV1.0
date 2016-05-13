package org.courseregistration.exception;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

    public Response toResponse(Throwable ex) {
        logger.error(ex.getMessage(),ex);

        GenericException exception;

        if(ex instanceof ApplicationException){
            exception = GenericException.from((ApplicationException)ex);
        }else{
            exception = new GenericException();
            setHttpStatus(ex, exception);
            exception.setCode(1009);
            exception.setTittle(ex.getMessage());
            exception.setDetail(ex.toString());
            exception.setLink("");
        }

        Map<String,List<GenericException>> exMap = new HashMap<>();
        exMap.put("errors", Lists.newArrayList(exception));

        return Response.status(exception.getStatus())
            .entity(exMap)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

    private void setHttpStatus(Throwable ex, GenericException exception) {
        if(ex instanceof WebApplicationException) {
            exception.setStatus(((WebApplicationException)ex).getResponse().getStatus());
        } else {
            exception.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}


