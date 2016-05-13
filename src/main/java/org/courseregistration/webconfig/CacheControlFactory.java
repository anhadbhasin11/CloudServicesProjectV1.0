package org.courseregistration.webconfig;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;
import org.courseregistration.webconfig.CacheControlAnnotation.CacheMaxAge;
import org.courseregistration.webconfig.CacheControlAnnotation.NoCache;

public class CacheControlFactory implements ResourceFilterFactory {

    @Override
    public List<ResourceFilter> create(AbstractMethod am) {
        if (am.isAnnotationPresent(CacheMaxAge.class)) {
            CacheMaxAge maxAge = am.getAnnotation(CacheMaxAge.class);
            String cacheHeaderValue = "max-age= " + maxAge.unit().toSeconds(maxAge.time());
            cacheHeaderValue=maxAge.isPrivate()? cacheHeaderValue+", private":cacheHeaderValue;
            cacheHeaderValue=maxAge.noStore()? cacheHeaderValue+", no-store":cacheHeaderValue;
            return newCacheFilter(cacheHeaderValue);
        } else if (am.isAnnotationPresent(NoCache.class)) {
            return newCacheFilter("no-cache");
        } else {
            return Collections.emptyList();
        }
    }

    private List<ResourceFilter> newCacheFilter(String content) {
        return Collections
            .<ResourceFilter> singletonList(new CacheResponseFilter(content));
    }

    private static class CacheResponseFilter implements ResourceFilter, ContainerResponseFilter {
        private final String headerValue;

        CacheResponseFilter(String headerValue) {
            this.headerValue = headerValue;
        }

        @Override
        public ContainerRequestFilter getRequestFilter() {
            return null;
        }

        @Override
        public ContainerResponseFilter getResponseFilter() {
            return this;
        }

        @Override
        public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
            response.getHttpHeaders().putSingle(HttpHeaders.CACHE_CONTROL, headerValue);
            return response;
        }
    }
}
