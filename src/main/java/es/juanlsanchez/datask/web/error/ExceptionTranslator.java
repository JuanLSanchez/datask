package es.juanlsanchez.datask.web.error;

import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.NestedServletException;

import javassist.NotFoundException;

@ControllerAdvice
public class ExceptionTranslator {

  @ExceptionHandler(NestedServletException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO AssertionErrorError(NestedServletException ex) {
    return new ErrorDTO(ErrorConstants.ERR_ASSERTION_ERROR, ex.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO IllegalArgumentError(IllegalArgumentException ex) {
    return new ErrorDTO(ErrorConstants.ERR_ILLEGAL_ARGUMENT, ex.getMessage());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO DataIntegrityViolationError(DataIntegrityViolationException ex) {
    return new ErrorDTO(ErrorConstants.ERR_ILLEGAL_ARGUMENT, ex.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO HttpMessageNotReadableError(HttpMessageNotReadableException ex) {
    return new ErrorDTO(ErrorConstants.ERR_ILLEGAL_ARGUMENT, ex.getMessage());
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO HttpMediaTypeNotSupportedError(HttpMediaTypeNotSupportedException ex) {
    return new ErrorDTO(ErrorConstants.ERR_ILLEGAL_ARGUMENT, ex.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO notFoundError(NotFoundException ex) {
    return new ErrorDTO(ErrorConstants.ERR_NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(ConcurrencyFailureException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorDTO processConcurencyError(ConcurrencyFailureException ex) {
    return new ErrorDTO(ErrorConstants.ERR_CONCURRENCY_FAILURE);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();

    return processFieldErrors(fieldErrors);
  }

  @ExceptionHandler(CustomParameterizedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ParameterizedErrorDTO processParameterizedValidationError(
      CustomParameterizedException ex) {
    return ex.getErrorDTO();
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ErrorDTO processAccessDeniedException(AccessDeniedException e) {
    return new ErrorDTO(ErrorConstants.ERR_ACCESS_DENIED, e.getMessage());
  }

  private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
    ErrorDTO dto = new ErrorDTO(ErrorConstants.ERR_VALIDATION);

    for (FieldError fieldError : fieldErrors) {
      dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
    }

    return dto;
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ErrorDTO processMethodNotSupportedException(
      HttpRequestMethodNotSupportedException exception) {
    return new ErrorDTO(ErrorConstants.ERR_METHOD_NOT_SUPPORTED, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> processRuntimeException(Exception ex) throws Exception {
    BodyBuilder builder;
    ErrorDTO errorDTO;
    ResponseStatus responseStatus =
        AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
    if (responseStatus != null) {
      builder = ResponseEntity.status(responseStatus.value());
      errorDTO = new ErrorDTO("error." + responseStatus.value().value(), responseStatus.reason());
    } else {
      builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
      errorDTO = new ErrorDTO(ErrorConstants.ERR_INTERNAL_SERVER_ERROR, "Internal server error");
    }
    return builder.body(errorDTO);
  }
}
