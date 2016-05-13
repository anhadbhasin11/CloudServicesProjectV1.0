package org.courseregistration.webconfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

public class CacheControlAnnotation {

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CacheMaxAge {
            /**
             * @return The amount of time to cache this resource.
             */
            long time();

            /**
             * @return The {@link TimeUnit} for the given {@link #time()}.
             */
            TimeUnit unit();

            boolean isPrivate();

            boolean noStore();
        }

        /**
         * Sets the cache header to the value "no cache"
         *
         */
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface NoCache {

        }

        private CacheControlAnnotation() {
        }
}
