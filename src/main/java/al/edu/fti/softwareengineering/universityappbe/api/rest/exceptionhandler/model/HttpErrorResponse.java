package al.edu.fti.softwareengineering.universityappbe.api.rest.exceptionhandler.model;

import lombok.Data;

import java.util.Date;

@Data
public class HttpErrorResponse {
    private int errorCode;
    private Date timestamp;
    private String path;
    private String message;
    private String localizedMessage;

    public HttpErrorResponse() {
        this.timestamp = new Date();
    }
}
