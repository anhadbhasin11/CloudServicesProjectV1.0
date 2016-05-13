package org.courseregistration.exception;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties({"stackTrace"})
public class ApplicationException extends Exception {
    @XmlElement
    int status;
    @XmlElement
    int code;
    @XmlElement
    String tittle;
    @XmlElement
    String detail;
    @XmlElement
    String source;
    @XmlElement
    String link;

    private ApplicationException(int status, int code, String tittle, String detail, String source, String link) {
        super();
        this.status =status;
        this.code = code;
        this.tittle = tittle;
        this.detail=detail;
        this.source=source;
        this.link=link;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
    public String getTittle() {
        return tittle;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static ApplicationExceptionBuilder createNew(){
        return new ApplicationExceptionBuilder();
    }



    public static class ApplicationExceptionBuilder {
        int status;
        int code;
        String tittle;
        String detail;
        String source;
        String link;

        public ApplicationExceptionBuilder withSource(String source){
            this.source = source;
            return this;
        }
        public ApplicationExceptionBuilder withStatus(int status){
            this.status = status;
            return this;
        }
        public ApplicationExceptionBuilder withCode(int code){
            this.code = code;
            return this;
        }
        public ApplicationExceptionBuilder withTitle(String title){
            this.tittle = title;
            return this;
        }
        public ApplicationExceptionBuilder withDetail(String detail){
            this.detail = detail;
            return this;
        }
        public ApplicationExceptionBuilder withLink(String link){
            this.link = link;
            return this;
        }

        public ApplicationException build(){
           return new ApplicationException(status,code,tittle,detail,source,link);
       }
    }
}
