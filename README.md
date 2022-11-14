# Rest API 개발

## Step1. Project 기초 생성 
1. Test 개발 순서  
   1. 기본 Domain 생성(Annotation 추가 없이) 
   2. Test 코드 작성 (builder) -> Builder 어노테이션 추가 
   3. Test 코드 작성 (javaBean) -> 해당 어노테이션 추가 

## Step2. 이벤트 생성 API 개발 
1. Controller Test 개발 시작
   1. post 의 경우 MockMvcRequestBuilders.post 사용
   2. status 의 경우 MockMvcResultMatchers.status 사용

cf. Web 관련 테스트는 SpringBootTest로 작성하는 것이 편함(Mocking 할 게 너무 많다.) 
-> @SpringBootTest, @AutoConfigureMockMvc 사용하자.