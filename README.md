<p align="center">
  <a href="https://reqres.in/" target="_blank">
    <img src="images/logos/reqres_in_logo.png" width="200" alt="Reqres.in Logo">
  </a>
</p>

# Проект по автоматизации API-тестов для сервиса [Reqres.in](https://reqres.in/)

**Дипломная работа по автоматизации тестирования**

## Содержание
- [Технологический стек](#-технологический-стек)
- [API-тесты](#-api-тесты)
- [Запуск тестов](#-запуск-тестов)
- [Allure-отчет](#-allure-отчет)
- [Уведомления в Telegram](#-уведомления-в-telegram)

## 💻 Технологический стек

<div align="center">
  <table>
    <tr>
      <!-- Первая строка -->
      <td align="center" width="110">
        <a href="https://www.jetbrains.com/idea/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" width="48" height="48" alt="IntelliJ IDEA" />
        </a>
        <br>IDEA
      </td>
      <td align="center" width="110">
        <a href="https://www.java.com" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="48" height="48" alt="Java" />
        </a>
        <br>Java
      </td>
      <td align="center" width="110">
        <a href="https://junit.org/junit5/" target="_blank">
          <img src="https://junit.org/junit5/assets/img/junit5-logo.png" width="48" height="48" alt="JUnit 5" />
        </a>
        <br>JUnit 5
      </td>
      <td align="center" width="110">
        <a href="https://gradle.org/" target="_blank">
          <img src="https://cdn.simpleicons.org/gradle/02303A" width="48" height="48" alt="Gradle" />
        </a>
        <br>Gradle
      </td>
    </tr>
    <tr>
      <!-- Вторая строка -->
      <td align="center" width="110">
        <a href="https://docs.qameta.io/allure/" target="_blank">
          <img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" width="48" height="48" alt="Allure" />
        </a>
        <br>Allure
      </td>
      <td align="center" width="110">
        <a href="https://rest-assured.io/" target="_blank">
          <img src="https://rest-assured.io/img/logo-transparent.png" width="48" height="48" alt="Rest-Assured" />
        </a>
        <br>Rest-Assured
      </td>
      <td align="center" width="110">
        <a href="https://github.com/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" width="48" height="48" alt="GitHub" />
        </a>
        <br>GitHub
      </td>
      <td align="center" width="110">
        <a href="https://www.jenkins.io/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg" width="48" height="48" alt="Jenkins" />
        </a>
        <br>Jenkins
      </td>
    </tr>
  </table>
</div>

- Тесты написаны на **Java** с использованием фреймворка **Rest-Assured** в **IntelliJ IDEA**  
- Сборка проекта осуществляется через **Gradle**  
- Тестирование API с использованием **JUnit 5**  
- Интеграция с **Jenkins** + автоматическая отправка отчетов в **Telegram**  
- Генерация отчетов через **Allure Framework**

---

## 🌐 API-тесты

### Основные проверки
- ✅ **GET** - Получение списка пользователей с пагинацией
- ✅ **GET** - Получение данных конкретного пользователя
- ✅ **GET** - Обработка несуществующего пользователя
- ✅ **GET** - Получение объекта цвета с детальной информацией
- ✅ **POST** - Создание нового пользователя
- ✅ **POST** - Регистрация нового пользователя
- ✅ **POST** - Успешный вход пользователя в систему
- ✅ **PATCH** - Частичное обновление пользователя (только должность)
- ✅ **PATCH** - Обновление имени и должности пользователя

### Особенности реализации
- Использование **Lombok object model** для моделей данных
- Детальное логирование каждого шага теста через **Allure Steps**
- Валидация JSON-схем через **Rest-Assured**
- Автоматическая генерация тестовых данных
- Проверка временных меток и форматов данных

---

## 🚀 Запуск тестов

### Локальный запуск
```bash
# Запуск всех API-тестов
gradle clean all_api_test

# Запуск тестов с тегом AutoRun
gradle clean auto_tests_custom_run
```

### Удаленный запуск (Jenkins)
```bash
clean ${TASK}
```

---

## 📊 Allure-отчет

### Главная страница Allure-отчета
<!-- Вставьте скриншот главной страницы Allure-отчета -->
<img src="images/screens/allure_report_main.png" width="800" alt="Allure Main Page">

### Пример отчета о выполнении тестов
<!-- Вставьте скриншот с результатами выполнения тестов -->
<img src="images/screens/allure_report_results.png" width="800" alt="Allure Test Results">

### Графики и статистика
<!-- Вставьте скриншот с графиками выполнения тестов -->
<img src="images/screens/allure_report_charts.png" width="800" alt="Allure Charts">

<!-- Вставьте скриншот с дополнительной статистикой -->
<img src="images/screens/allure_report_statistics.png" width="800" alt="Allure Statistics">

---

## 📱 Уведомления в Telegram

### После завершения сборки, бот автоматически отправляет сообщение с результатом выполнения тестов

<!-- Вставьте скриншот уведомления из Telegram -->
<p align="center">
<img src="images/screens/telegram_notification.png" width="600" alt="Telegram Notification">
</p>

---

## 📁 Структура проекта

```
src/test/java/
├── models/                    # Модели данных для API
├── specs/                     # Спецификации API
├── helpers/                   # Вспомогательные классы
├── ApiTestBase.java          # Базовый класс для тестов
└── ReqresApiTests.java       # Основной класс с API-тестами
```

## 🔧 Конфигурация

Проект настроен для работы с API [Reqres.in](https://reqres.in/):
- **Base URI**: `https://reqres.in`
- **Base Path**: `/api`
- **API Key**: `reqres-free-v1`

---

## 📝 Автор

**Дипломная работа** - Автоматизация API-тестирования  
**Студент**: [Артем Б.]  
**Поток**: [35]  
**Год**: 2025
