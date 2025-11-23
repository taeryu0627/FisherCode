package Models;

public class MainModel {
	
    // ★ 1. 문제 출제 및 단계 관리를 전담하는 QuizStageManager 필드
    private final QuizStageManager quizManager; 

    public MainModel() {
        // 1. ScriptList 초기화 (하드코딩된 데이터를 포함하는 기본 생성자 호출)
        ScriptList scriptList = new ScriptList(); 
        
        // 2. QuizStageManager 초기화 및 연결
        // QuizStageManager는 ScriptList를 받아 Stage 1 초기 Pool을 준비합니다.
        this.quizManager = new QuizStageManager(scriptList); 
    }

    /**
     * 다음 문제 출제를 QuizStageManager에게 위임합니다.
     * @return 다음 문제 Script 객체 (없으면 null)
     */
    public Script getNextQuestion() {
        return quizManager.getNextQuestion();
    }
    
    /**
     * 현재 퀴즈 단계를 반환합니다 (예: 1, 2, 3)
     */
    public int getCurrentStage() {
        return quizManager.getCurrentStage();
    }
    
    /**
     * 현재 단계에서 몇 번째 문제를 풀고 있는지 반환합니다.
     */
    public int getQuestionNumberInStage() {
        return quizManager.getQuestionNumberInStage();
    }

    /**
     * 한 단계당 총 몇 문제를 출제하는지 반환합니다.
     */
    public int getQuestionsPerStage() {
        return quizManager.getQuestionsPerStage();
    }
}