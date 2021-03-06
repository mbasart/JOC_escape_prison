package API.model;

public class Respuesta {

    private int code;
    private String message;

    public Respuesta(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Respuesta() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
