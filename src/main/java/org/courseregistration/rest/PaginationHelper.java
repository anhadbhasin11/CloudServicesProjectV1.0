package org.courseregistration.rest;

import com.google.common.collect.Lists;
import org.springframework.hateoas.Link;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class PaginationHelper {

    public static List<Link> getPaginationLinks(int page,int size,UriInfo uriInfo, int totalNumberOfPages) {

        List<Link> links = Lists.newArrayList();
        if(page == totalNumberOfPages){
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page).queryParam("size",size).build().toString(),Link.REL_NEXT));
        }else{
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page+1).queryParam("size",size).build().toString(),Link.REL_NEXT));
        }
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",1).queryParam("size",size).build().toString(),Link.REL_FIRST));
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",totalNumberOfPages).queryParam("size",size).build().toString(),Link.REL_LAST));
        if(page>1){
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page-1).queryParam("size",size).build().toString(),Link.REL_PREVIOUS));
        }
        return links;
    }
}
