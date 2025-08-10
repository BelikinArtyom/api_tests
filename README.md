# API Tests Project

## Настройка переменных окружения

### Способ 1: Через gradle.properties (рекомендуется для разработки)
Создайте файл `gradle.properties` в корне проекта:
```properties
# API Configuration
api.key=your_actual_api_key_here
base.uri=https://reqres.in
base.path=/api

# Test Configuration
selenoid.login=your_selenoid_login
selenoid.password=your_selenoid_password
selenoid.host=your_selenoid_host
```

### Способ 2: Через системные переменные
Установите переменные окружения в системе:
```bash
# Windows
set api.key=your_actual_api_key_here
set base.uri=https://reqres.in
set base.path=/api

# Linux/Mac
export api.key=your_actual_api_key_here
export base.uri=https://reqres.in
export base.path=/api
```

### Способ 3: При запуске тестов
```bash
# Gradle
./gradlew test -Dapi.key=your_key -Dbase.uri=https://reqres.in -Dbase.path=/api

# Maven
mvn test -Dapi.key=your_key -Dbase.uri=https://reqres.in -Dbase.path=/api
```

## Безопасность
- **НЕ коммитьте** файл `gradle.properties` с реальными API ключами в репозиторий
- Добавьте `gradle.properties` в `.gitignore`
- Используйте CI/CD переменные для продакшн окружения

## Структура проекта
- `src/test/java/` - тесты
- `src/test/java/models/` - модели данных
- `src/test/java/specs/` - спецификации для API
- `src/test/java/helpers/` - вспомогательные классы
