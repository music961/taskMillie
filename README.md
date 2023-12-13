# taskMillie
밀리의 서재 사전 과제

프로젝트 소개
- 간단한 뉴스보기 앱

개발기간
- 2023.12.9~2023.12.13 (약 5일)

적용 기술 및 라이브러리
- Jetpack compose
- MVVM
- material3
- Room
- Hilt
- retrofit2
- Coil
- accompanist : Web

개발하면서 했던 고민들
- 명시적 자료형 사용을 고민했으나, IDE를 통한 자료형 추론이 가능하므로, 사용하지 않기로 결정
- 주석을 줄이고, 이해하기 쉬운 코드 및 변수명, 함수명을 작성
- MVVM 패턴을 철저히 지키기 위해 노력함. (예기치 않은 오류 방지)
- 재사용 가능한 UI코드 작성
- 상수를 하드코딩 하지 않도록 노력 (enum 을 통해 tree shaking 을 통한 추적이 가능하도록 했음)

프로젝트 설명
- raw 데이터를 가져올 때 country 및 apiKey 부분을 @Query 로 변경하여, 차후 유동적인 값이 들어가게 하였음
- 뉴스 목록은 LazyVerticalGrid 을 사용했으며, columns 파라미터의 Fixed 로 column 갯수 조절
- 뉴스 목록은 앱을 처음 시작할때만 받으며, room 라이브러리를 통해 내부 데이터베이스 저장
- 뉴스 목록의 다운로드를 실패하거나 오프라인 상태일 경우, room 라이브러리에 보관한 마지막 뉴스목록 로딩. 이 상태는 retrofit2 의 Call 결과에 따라 분기
- 셀 터치시, navigation 을 통해 웹뷰를 표기하는 페이지로 이동하도록 했음.
- 뉴스의 title 값을 고유키로 하여, 읽은 뉴스정보를 보관함. 읽은 뉴스의 title 은 Red 컬러로 표기
- urlToImage 는 Coil 라이브러리의 AsyncImage 를 사용했음. 메모리 및 디스크 캐싱을 라이브러리에서 수행하므로, 따로 신경쓰지는 않음. (테스트 결과 양호)
