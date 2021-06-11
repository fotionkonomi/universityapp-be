package al.edu.fti.softwareengineering.universityappbe.api.rest.exceptionhandler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ExceptionMessageUtilImpl implements ExceptionMessageUtil{

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    @Override
    public String getLocalizedMessage(String messageKey) {
        return accessor.getMessage(messageKey);
    }
}
