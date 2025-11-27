package Models;

public class Script {
    private int scamLevel;
    private boolean isScam;
    private String message;   // 메시지 내용

    public Script(int scamLevel, boolean isScam, String message) {
        this.scamLevel = scamLevel;
        this.isScam = isScam;
        this.message = message;
    }

    public int getScamLevel() { return scamLevel; }
    public boolean isScam() { return isScam; }
    public String getMessage() { return message; }
}