package org.courseregistration.exception;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties({"stackTrace"})
public class GenericException{

    /**
     * the HTTP status code applicable to this problem
     * **/
    @XmlElement
    int status;
    /** An application-specific error code
    */
    @XmlElement
    int code;
    /**
     * a short, human-readable summary of the problem
     * */
    @XmlElement
    String tittle;
    /**
     * a human-readable explanation specific to this occurrence of the problem
     * */
    @XmlElement
    String detail;
    /**
     * an object containing references to the source of the error,
     * */
    @XmlElement
    String source;

    @XmlElement
    String link;



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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
    public static GenericException from(ApplicationException ex){
        GenericException newException = new GenericException();
        newException.setStatus(ex.getStatus());
        newException.setTittle(ex.getTittle());
        newException.setDetail(ex.getDetail());
        newException.setCode(ex.getCode());
        newException.setLink(ex.getLink());
        newException.setSource(ex.getSource());
        return newException;
    }
    public GenericException(){
    }

}
