package ch28.e;

// 필수 프로퍼티
// => 에노테이션을 사용할 때 반드시 값을 설정해야 하는 프로퍼티이다.
public @interface MyAnnotation {
  // 모양은 메소드 형태이고,
  // 값을 꺼낼때도 메소드 호출로 꺼낸다.
  // 그러나 에노테이션에서 값을 설정할 때는
  // 다음과 같이 변수 형태를 사용한다.
  //     value="hello"
  // 따라서 이름은 변수형태로 짓는다.
  // 즉 getValue가 아니라 value라고 한다.
  String value();
}
