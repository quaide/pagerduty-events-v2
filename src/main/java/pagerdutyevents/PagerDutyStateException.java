package pagerdutyevents;

class PagerDutyStateException extends RuntimeException {
  public PagerDutyStateException(String message, Exception e) {
    super(message, e);
  }
}
