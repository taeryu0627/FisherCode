# ✨ FisherCode – Smishing Detection Simulation Game
### ✔️ 스미싱 범죄 예방을 위한 문자 판별 시뮬레이션  
### ✔️ 경찰청 가이드 기반 학습형 게임  
### ✔️ 직관적인 UI + Stage 기반 시나리오

---

## 📌 프로젝트 소개  
**FisherCode**는 실제 스미싱 문자를 기반으로  
사용자가 **문자가 정상인지/스미싱인지 직접 판별하는 게임형 학습 프로그램**입니다.

---

## 🖥 주요 기능

### 📱 1. 스마트폰 문자 UI  
- 스마트폰 외형 기반 UI  
-  받아온 메시지를 자동 표시

### 📒 2. 경찰청 노트 UI  
- 스미싱 탐지 지침 제공  
- 사용자가 판단을 내릴 수 있도록 가이드 제공

### 🧩 3. 문제 랜덤 출제  
- Script 구조: id / category / isScam / message  

---

## 🗂 폴더 구조

```
FisherCode/
 ├── src/
 │   ├── GUI/
 │   │   ├── MainFrame.java        # 전체 UI 구성
 │   │   ├── PhonePanel.java       # 스마트폰 문자 UI
 │   │   ├── NotePanel.java        # 경찰청 노트 디자인
 │   │   ├── ButtonPanel.java      # Yes/No 버튼(도장 버튼)
 │   │
 │   ├── Models/
 │   │   ├── Script.java           # 스크립트 데이터 구조
 │   │   ├── StageManager.java     # 스테이지 관리
 │   │   ├── ScriptLoader.java     # (옵션) 외부 로딩 지원
 │   │
 │   ├── resources/
 │   │   ├── 기타 이미지 리소스
 │
 ├── README.md  
 └── .gitignore
```

---

## 🧪 기술 스택

| 기술 | 설명 |
|------|------|
| **Java 17** | 안정적인 LTS 버전 |
| **Swing** | GUI 렌더링 |
| **Graphics2D** | 말풍선 및 UI 커스텀 렌더링 |
| **Random 알고리즘** | 중복 없는 문제 출제 |

---

## 🔮 향후 개발 로드맵  
- JSON 기반 스크립트 외부 로딩  

---

## 👨‍💻 개발자  
  + 김가현 : 팀장, Sub Develop, Design  
  + 김태민 : Main Develop
  + 유태웅 : Sub Develop, Design 
  + 이찬우 : 스크립트 담당  
