package bo.com.dt.challenge.exception.message;

import bo.com.dt.challenge.exception.dto.ExceptionBasicDto;

public class MessageError {
    public static ExceptionBasicDto GetExceptionBasic(Message message) {
        ExceptionBasicDto detailMessage = new ExceptionBasicDto();
        detailMessage.setMessage(message.getMessage());
        detailMessage.setCode(message.getCode());
        return detailMessage;
    }

    public static ExceptionBasicDto GetExceptionBasic(Message message, String value) {
        ExceptionBasicDto detailMessage = new ExceptionBasicDto();
        detailMessage.setMessage(String.format(message.getMessage(), value));
        detailMessage.setCode(message.getCode());
        return detailMessage;
    }

    public static ExceptionBasicDto GetExceptionBasic(Message message, Object[] args) {
        ExceptionBasicDto detailMessage = new ExceptionBasicDto();
        detailMessage.setMessage(String.format(message.getMessage(), args));
        detailMessage.setCode(message.getCode());
        return detailMessage;
    }
}
