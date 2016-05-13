package org.courseregistration.exception;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    public Response toResponse(ApplicationException ex) {

        Map<String,List<ApplicationException>> exMap = new HashMap<>();
        exMap.put("errors", Lists.<ApplicationException>newArrayList(ex));
        return Response.status(ex.getStatus())
            .entity(exMap)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}
