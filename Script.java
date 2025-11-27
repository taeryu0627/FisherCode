package Models;

public class Script {
    private String id;        // 시나리오 id
    private String category;  // 피싱 유형
    private boolean isScam;   // 스미싱 여부
    private String message;   // 메시지 내용

    public Script(String id, String category, boolean isScam, String message) {
        this.id = id;
        this.category = category;
        this.isScam = isScam;
        this.message = message;
    }

    public String getId() { return id; }
    public String getCategory() { return category; }
    public boolean isScam() { return isScam; }
    public String getMessage() { return message; }
}
