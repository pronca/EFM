package it.eng.care.domain.flow.core.controller.impl;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.common.error.GenericException;
import it.eng.care.platform.common.error.NotFoundException;
import it.eng.care.platform.tool.transport.messages.Message;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.service.CodedException;
import it.eng.care.platform.tool.transport.utils.ConstraintViolationDynamicPayloadKey;
import it.eng.care.platform.tool.validation.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@ControllerAdvice
public abstract class ControllerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
    public static final String UNEXPECTED_ERROR = "Exception.unexpected";

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected LoggedUserService loggedUserService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OperationResult<?>> handleExceptions(Exception ex) {
        if (ex instanceof CodedException) {
            CodedException cEx = (CodedException) ex;
            String errorMessage = messageSource.getMessage(cEx.getErrorCode(), cEx.getMessageArgs(), getGenericMessageException(ex), getLocale());
            return ResponseEntity.ok().body(OperationResult.failureCode(cEx.getErrorCode(), errorMessage));
        }
        if (ex instanceof ValidationException) {
            ValidationException vEx = (ValidationException) ex;
            return ResponseEntity.ok().body(vEx.asOperationResult());
        }
        return ResponseEntity.ok().body(OperationResult.failureCode(UNEXPECTED_ERROR, getGenericMessageException(ex)));
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<OperationResult<?>> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<Message> errorMessages = constraintViolations.stream()
                .map(constraintViolationToMessage)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(OperationResult.failure().addMessages(errorMessages));
    }

    @ExceptionHandler({ it.eng.care.platform.common.error.GenericException.class })
    public ResponseEntity<OperationResult<?>> constraintViolationExceptionHandler(GenericException ex) {
        String errorMessage = messageSource.getMessage(ex.getExceptionCoding().getCode(), null, getGenericException(), getLocale());
        return ResponseEntity.ok().body(OperationResult.failure(errorMessage));
    }

    @ExceptionHandler({ it.eng.care.platform.common.error.NotFoundException.class })
    public ResponseEntity<OperationResult<?>> notFoundExceptionHandler(NotFoundException ex) {
        String searchedName = messageSource.getMessage(ex.getSearchedElementName(), null, ex.getSearchedElementName(), getLocale());
        String message = messageSource.getMessage(ex.getMessage(), new Object[] { searchedName, ex.getSearchedElementId() }, ex.getMessage(), getLocale());
        return ResponseEntity.ok().body(OperationResult.failure(message));
    }

    public static String allMessages(Throwable cause) {
        StringBuilder loStrBuilder = new StringBuilder(cause.toString());
        Throwable loThrowable = cause;
        while ((loThrowable.getCause() != null) && (loThrowable != loThrowable.getCause())) {
            loThrowable = loThrowable.getCause();
            loStrBuilder.append("\n ");
            loStrBuilder.append(loThrowable.toString());
        }
        return loStrBuilder.toString();
    }

    @SuppressWarnings("deprecation")
    private Function<ConstraintViolation<?>, Message> constraintViolationToMessage = constraintViolation -> {
        Message message = null;
        Map<?, ?> dynamicPayloadMap = getDynamicPayload(constraintViolation);
        if (dynamicPayloadMap != null) {
            Object messageOnPayload = dynamicPayloadMap.get(ConstraintViolationDynamicPayloadKey.MESSAGE);
            if (messageOnPayload instanceof Message) {
                Object[] arguments = Optional.ofNullable(dynamicPayloadMap.get(ConstraintViolationDynamicPayloadKey.ARGUMENTS))
                        .filter(Object[].class::isInstance)
                        .map(Object[].class::cast)
                        .orElse(null);

                message = buildMessage((Message) messageOnPayload, arguments);
            } else if (dynamicPayloadMap.get(ConstraintViolationDynamicPayloadKey.MESSAGE) != null) {
                message = Message.error(constraintViolation.getMessage());
            }
        }
        if (message == null) {
            message = Message.error(messageSource.getMessage(constraintViolation.getMessage(), null, getGenericException(), getLocale()));
        }
        if (dynamicPayloadMap != null && message.getPath() == null) {
            Optional.ofNullable(dynamicPayloadMap.get(ConstraintViolationDynamicPayloadKey.MESSAGE))
                    .map(String[].class::cast)
                    .ifPresent(message::onProperty);
        }
        return message;
    };

    protected Message buildMessage(Message source, Object[] arguments) {
        Message message;
        String code = source.getCode();
        if (StringUtils.isNotBlank(code)) {
            message = Message.error(messageSource.getMessage(code, arguments, getGenericException(), getLocale()));
        } else {
            message = Optional.ofNullable(source.getText())
                    .map(Object::toString)
                    .filter(StringUtils::isNotBlank)
                    .map(Message::error)
                    .orElse(null);
        }
        if (message != null) {
            Optional.ofNullable(source.getPath())
                    .map(String[].class::cast)
                    .ifPresent(message::onProperty);
        }
        return message;
    }

    protected MessageSource getMessageSource() {
        return messageSource;
    }

    protected LoggedUserService getLoggedUserService() {
        return loggedUserService;
    }

    protected Locale getLocale() {
        Locale locale = loggedUserService.getCurrentLocale();
        return locale != null ? locale : Locale.US;
    }

    private Map<?, ?> getDynamicPayload(ConstraintViolation<?> constraintViolation) {
        HibernateConstraintViolation<?> hibernateConstraintViolation;
        try {
            hibernateConstraintViolation = constraintViolation.unwrap(HibernateConstraintViolation.class);
        } catch (Exception e) {
        	LogUtil.logException(LOGGER, "", e);
            return Collections.emptyMap();
        }
        return hibernateConstraintViolation.getDynamicPayload(Map.class);
    }

    private String getGenericMessageException(Exception ex) {
        return messageSource.getMessage(UNEXPECTED_ERROR, null, allMessages(ex), getLocale());
    }

    private String getGenericException() {
        return messageSource.getMessage(UNEXPECTED_ERROR, null, getLocale());
    }
}
