package com.ledger.core.exceptions;

public class FoundationExceptions {

  public static class DomainException extends RuntimeException {

    public DomainException(String message) {
      super(message);
    }
  }

  public static class ValidationException extends RuntimeException {

    private final String key;
    private final Object details;

    public ValidationException(String key) {
      this.details = null;
      this.key = key;
    }

    public ValidationException(String key, Object details) {
      this.details = details;
      this.key = key;
    }

    public Object getDetails() {
      return details;
    }

    public String getKey() {
      return key;
    }
  }

  public static class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
      super(message);
    }
  }

  public static class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
      super(message);
    }
  }


  public static class InternalServerErrorException extends RuntimeException {

    private final String key;

    public InternalServerErrorException(String key) {
      super(key);
      this.key = key;
    }

    public InternalServerErrorException(String key, String message) {
      super(message);
      this.key = key;
    }

    public String getKey() {
      return key;
    }
  }

  public static class NotFoundException extends RuntimeException {

    private final String key;

    public NotFoundException(String key) {
      this.key = key;
    }

    public NotFoundException(String key, String message) {
      super(message);
      this.key = key;
    }

    public String getKey() {
      return key;
    }
  }
}
