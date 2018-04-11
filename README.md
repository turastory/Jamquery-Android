# Jamquery - Android

Android Implementation of Jamquery

Inspired by [Android Clean Architecture](https://github.com/android10/Android-CleanArchitecture)


## Structure

어플리케이션은 전체 3개의 계층으로 구분되어 있습니다.

### Presentation

데이터를 보여주고 이벤트를 받아서 넘기는 역할을 하는 계층입니다.
[MVP 구조](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)를 사용합니다.

### Domain

비즈니스 로직을 정의하고 실행하는 역할을 합니다.
이 계층에서는 순수 자바만을 사용하고, 다른 계층과 통신할 때는 인터페이스를 사용합니다.

### Data

앱에서 필요한 데이터를 가져오는 저장소의 역할을 합니다.
데이터가 클라우드에 있는지, 메모리에 있는지에 상관 없이 말이죠.


## Dependencies

- 데이터 직렬화 - [Parceler](https://github.com/johncarl81/parceler)
- 폰트 - [Calligraphy](https://github.com/chrisjenx/Calligraphy)
- 네트워크 통신 - [Retrofit](https://github.com/square/retrofit)
- 함수형 인터페이스 - [Lightweight-Stream-API](https://github.com/aNNiMON/Lightweight-Stream-API)