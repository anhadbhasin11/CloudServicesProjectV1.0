package org.courseregistration.rest.writters;

public class JsonRootRelProvider  /*implements RelProvider*/ {

        /*DefaultRelProvider defaultRelProvider = new DefaultRelProvider();

        @Override
        public String getItemResourceRelFor(Class<?> type) {
            JsonRootName[] rootName = type.<JsonRootName>getAnnotationsByType(JsonRootName.class);
            return rootName!=null ? rootName : defaultRelProvider.getItemResourceRelFor(type);
        }

        @Override
        public String getCollectionResourceRelFor(Class<?> type) {
            JsonRootName[] rootName = type.<JsonRootName>getAnnotationsByType(JsonRootName.class);
            return rootName!=null ? English.plural(rootName.value()) : English.plural(defaultRelProvider.getCollectionResourceRelFor(type));
        }

        @Override
        public boolean supports(Class<?> delimiter) {
           return defaultRelProvider.supports(delimiter);
        }
    }*/
}
