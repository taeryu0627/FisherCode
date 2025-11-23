//문제 출제 방식 메카니즘을 다루는 클래스입니다.

package Models;

public class QuizStageManager {

    private final ScriptList scriptList;
    private int currentMaxScamLevel = 1; // Stage 1의 시작 레벨 (0과 1)
    private int questionCounter = 0;
    private final int QUESTIONS_PER_STAGE = 5;
    private final int MAX_STAGE_LEVEL = 3; // 최종 단계 (Level 3까지 포함)

    /**
     * StageManager를 초기화하고 ScriptList를 연결합니다.
     */
    public QuizStageManager(ScriptList scriptList) {
        this.scriptList = scriptList;
        // Stage 1 시작: 레벨 0과 1만 포함하도록 Pool 초기화
        scriptList.refillPoolByMaxLevel(currentMaxScamLevel);
    }

    /**
     * 단계별 로직에 따라 다음 문제를 출제하고, 필요한 경우 다음 단계로 진행합니다.
     * @return 다음 문제 Script 객체 (모든 단계 완료 시 null)
     */
    public Script getNextQuestion() {
        // 모든 단계 완료 및 문제 풀 소진 시
        if (currentMaxScamLevel > MAX_STAGE_LEVEL && scriptList.getRemainingQuestionCount() == 0) {
            return null; 
        }

        Script nextScript = scriptList.getRandomScript();

        if (nextScript != null) {
            questionCounter++;

            // 5문제를 모두 출제했고, 아직 다음 단계가 남아있다면 다음 단계로 진행
            if (questionCounter >= QUESTIONS_PER_STAGE && currentMaxScamLevel < MAX_STAGE_LEVEL) {
                moveToNextStage();
            }
        } else {
             // 현재 풀의 문제가 모두 소진되었고, 다음 단계가 남아있다면 바로 다음 단계로 진행
             if (currentMaxScamLevel < MAX_STAGE_LEVEL) {
                 moveToNextStage();
                 return getNextQuestion(); // 새 풀에서 문제 다시 시도
             }
        }

        return nextScript;
    }

    /** 다음 단계로 넘어가고 문제 풀을 재설정합니다. */
    private void moveToNextStage() {
        currentMaxScamLevel++;
        questionCounter = 0; // 카운터 리셋
        
        // Stage 2: 레벨 0, 1, 2 포함 / Stage 3: 레벨 0, 1, 2, 3 포함
        scriptList.refillPoolByMaxLevel(currentMaxScamLevel);
    }

    public int getCurrentStage() {
        return currentMaxScamLevel;
    }

    public int getQuestionNumberInStage() {
        return questionCounter;
    }

    public int getQuestionsPerStage() {
        return QUESTIONS_PER_STAGE;
    }
}