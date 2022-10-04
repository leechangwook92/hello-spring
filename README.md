# hello-spring

1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
> post 방식으로 thymeleaf에서 파라미터 값을 넘겨주어 컨트롤러에서 @PathVariable 어노테이션으로 값을 받았습니다 

2. 어떤 상황에 어떤 방식의 request를 써야하나요?
> 제가 생각했을때는 조회하는 부분 빼고는 다 post인거같습니다.

3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
> 수정하는 부분이 좀 미흡한거같아요 이름 중복하는 로직을 추가했는데 그 로직으로 변형해서 타이틀이 있으면 아이디, 제목 이름값을 넘겨서 다시 저장하는 형태로 구성했습니다. 

4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
> 컨트롤러에서는 호출만 서비스 부분에서는 자세한 로직 레파지토리에서는 디비와 소통하게 만든거같습니다.

5. 작성한 코드에서 빈(Bean)을 모두 찾아보세요!
>
SpringConfig클래스 내부에 따로 Configuration어노테이션을 설정해서 bean으로 스프링 컨테이너에 올렸습니다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
   
6. API 명세서 작성 가이드라인을 검색하여 직접 작성한 명세서와 비교해보세요!
네
