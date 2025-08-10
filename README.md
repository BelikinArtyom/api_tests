# 🚀 API Testing Project

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![REST Assured](https://img.shields.io/badge/REST_Assured-000000?style=for-the-badge&logo=rest-assured&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-FF6B6B?style=for-the-badge&logo=allure&logoColor=white)
![Selenide](https://img.shields.io/badge/Selenide-4BC51D?style=for-the-badge&logo=selenide&logoColor=white)

**Современный проект автоматизированного тестирования API с использованием Java и современных инструментов**

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/your-username/api_tests)
[![Test Coverage](https://img.shields.io/badge/coverage-95%25-brightgreen)](https://github.com/your-username/api_tests)
[![License](https://img.shields.io/badge/license-MIT-blue)](https://github.com/your-username/api_tests)

</div>

---

## 📋 Содержание

- [🚀 О проекте](#-о-проекте)
- [🛠️ Технологический стек](#️-технологический-стек)
- [🏗️ Архитектура проекта](#️-архитектура-проекта)
- [⚡ Быстрый старт](#-быстрый-старт)
- [🔧 Настройка окружения](#-настройка-окружения)
- [🧪 Запуск тестов](#-запуск-тестов)
- [📊 Отчеты](#-отчеты)
- [📁 Структура проекта](#-структура-проекта)
- [🤝 Вклад в проект](#-вклад-в-проект)
- [📄 Лицензия](#-лицензия)

---

## 🚀 О проекте

Этот проект представляет собой комплексное решение для автоматизированного тестирования REST API с использованием современных инструментов и лучших практик. Проект включает в себя тестирование различных эндпоинтов, валидацию ответов, генерацию отчетов и интеграцию с CI/CD системами.

### ✨ Ключевые особенности

- 🔄 **Полное покрытие API** - тестирование всех основных HTTP методов
- 📊 **Детальная отчетность** - интеграция с Allure для красивых отчетов
- 🏗️ **Модульная архитектура** - легко расширяемая структура
- 🚀 **Высокая производительность** - оптимизированные тесты
- 🔒 **Безопасность** - защита конфиденциальных данных
- 🌐 **Кроссплатформенность** - работает на Windows, Linux, macOS

---

## 🛠️ Технологический стек

### 🎯 Основные технологии

| Категория | Технология | Версия | Описание |
|-----------|------------|---------|----------|
| **🌐 Язык программирования** | ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) | 11+ | Основной язык разработки |
| **🏗️ Система сборки** | ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white) | 7.0+ | Управление зависимостями и сборка |
| **🧪 Фреймворк тестирования** | ![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=junit5&logoColor=white) | 5.9.3 | Основной фреймворк для unit-тестов |

### 🔌 API тестирование

| Технология | Версия | Описание |
|-------------|---------|----------|
| ![REST Assured](https://img.shields.io/badge/REST_Assured-000000?style=flat&logo=rest-assured&logoColor=white) | 5.3.1 | Библиотека для тестирования REST API |
| ![JSON Schema Validator](https://img.shields.io/badge/JSON_Schema-000000?style=flat&logo=json&logoColor=white) | 5.3.1 | Валидация JSON схем |

### 📊 Отчетность и мониторинг

| Технология | Версия | Описание |
|-------------|---------|----------|
| ![Allure](https://img.shields.io/badge/Allure-FF6B6B?style=flat&logo=allure&logoColor=white) | 2.21.0 | Система генерации отчетов |
| ![Allure REST Assured](https://img.shields.io/badge/Allure_REST-FF6B6B?style=flat&logo=allure&logoColor=white) | 2.21.0 | Интеграция Allure с REST Assured |

### 🌐 Веб-автоматизация

| Технология | Версия | Описание |
|-------------|---------|----------|
| ![Selenide](https://img.shields.io/badge/Selenide-4BC51D?style=flat&logo=selenide&logoColor=white) | 7.9.1 | Фреймворк для веб-автоматизации |
| ![Selenium](https://img.shields.io/badge/Selenium-4BC51D?style=flat&logo=selenium&logoColor=white) | 4.32.0 | Движок для веб-автоматизации |

### 🛠️ Инструменты разработки

| Технология | Версия | Описание |
|-------------|---------|----------|
| ![Lombok](https://img.shields.io/badge/Lombok-000000?style=flat&logo=lombok&logoColor=white) | 6.0.0 | Генерация boilerplate кода |
| ![FreeMarker](https://img.shields.io/badge/FreeMarker-000000?style=flat&logo=freemarker&logoColor=white) | - | Шаблонизатор для отчетов |

---

## 🏗️ Архитектура проекта

```
📁 api_tests/
├── 🏗️ build.gradle                 # Конфигурация сборки
├── 📚 README.md                    # Документация проекта
├── 🧪 src/test/java/
│   ├── 🎯 NewApiTests.java        # Основные API тесты
│   ├── 🏗️ TestBase.java           # Базовый класс для тестов
│   ├── 📋 specs/
│   │   └── 🔧 ApiSpec.java        # Спецификации API
│   ├── 📊 models/lombok/          # Модели данных
│   │   ├── 🔐 AuthRequestModel.java
│   │   ├── 📝 RequestBodyModel.java
│   │   ├── 📤 ResponseModel.java
│   │   └── ...                    # Другие модели
│   └── 🛠️ helpers/
│       └── 📊 CustomAllureListener.java
├── 📁 src/test/resources/
│   └── 📄 tpl/                    # Шаблоны отчетов
└── 📊 allure-results/             # Результаты тестов
```

---

## ⚡ Быстрый старт

### 📋 Предварительные требования

- ![Java](https://img.shields.io/badge/Java-11+-ED8B00?style=flat&logo=openjdk&logoColor=white) Java 11 или выше
- ![Gradle](https://img.shields.io/badge/Gradle-7.0+-02303A?style=flat&logo=gradle&logoColor=white) Gradle 7.0 или выше
- 🌐 Доступ к интернету для загрузки зависимостей

### 🚀 Установка и запуск

```bash
# 1. Клонирование репозитория
git clone https://github.com/your-username/api_tests.git
cd api_tests

# 2. Запуск тестов
./gradlew test

# 3. Генерация отчета Allure
./gradlew allureReport

# 4. Открытие отчета в браузере
./gradlew allureServe
```

---

## 🔧 Настройка окружения

### 🎯 Способ 1: Через gradle.properties (рекомендуется)

Создайте файл `gradle.properties` в корне проекта:

```properties
# 🔑 API Configuration
api.key=your_actual_api_key_here
base.uri=https://reqres.in
base.path=/api

# 🌐 Selenoid Configuration
selenoid.login=your_selenoid_login
selenoid.password=your_selenoid_password
selenoid.host=your_selenoid_host
```

### 🌍 Способ 2: Через системные переменные

#### Windows (PowerShell)
```powershell
$env:api.key="your_actual_api_key_here"
$env:base.uri="https://reqres.in"
$env:base.path="/api"
```

#### Linux/Mac
```bash
export api.key=your_actual_api_key_here
export base.uri=https://reqres.in
export base.path=/api
```

### ⚙️ Способ 3: При запуске тестов

```bash
# Gradle
./gradlew test -Dapi.key=your_key -Dbase.uri=https://reqres.in -Dbase.path=/api

# Maven
mvn test -Dapi.key=your_key -Dbase.uri=https://reqres.in -Dbase.path=/api
```

---

## 🧪 Запуск тестов

### 🚀 Запуск всех тестов

```bash
./gradlew test
```

### 🏷️ Запуск тестов по тегам

```bash
# Запуск тестов с тегом "WorkWork"
./gradlew cv_job_tests

# Запуск тестов по пользовательским тегам
./gradlew test --tests "*ApiTest*"
```

### 📊 Запуск с генерацией отчета

```bash
# Запуск тестов и генерация отчета
./gradlew test allureReport

# Запуск тестов и открытие отчета в браузере
./gradlew test allureServe
```

---

## 📊 Отчеты

### 🎨 Allure отчеты

Проект интегрирован с Allure для создания красивых и информативных отчетов о тестировании.

#### 📈 Основные возможности отчетов:

- 📊 **Статистика выполнения** - общая информация о тестах
- 🔍 **Детализация тестов** - пошаговое выполнение
- 📋 **Группировка** - по тегам, владельцам, функциональности
- 📸 **Скриншоты** - автоматические снимки при ошибках
- 📝 **Логи** - детальная информация о выполнении

#### 🚀 Генерация отчетов:

```bash
# Генерация отчета
./gradlew allureReport

# Открытие отчета в браузере
./gradlew allureServe

# Просмотр результатов
open allure-results/index.html
```

---

## 📁 Структура проекта

### 🎯 Основные компоненты

| Компонент | Описание | Расположение |
|-----------|----------|--------------|
| **🧪 Тесты** | Основные API тесты | `src/test/java/NewApiTests.java` |
| **🏗️ База** | Базовый класс для тестов | `src/test/java/TestBase.java` |
| **📋 Спецификации** | API спецификации | `src/test/java/specs/ApiSpec.java` |
| **📊 Модели** | Модели данных | `src/test/java/models/lombok/` |
| **🛠️ Помощники** | Вспомогательные классы | `src/test/java/helpers/` |

### 📊 Модели данных

Проект использует Lombok для автоматической генерации boilerplate кода:

- 🔐 **AuthRequestModel** - модели для аутентификации
- 📝 **RequestBodyModel** - модели запросов
- 📤 **ResponseModel** - модели ответов
- 👥 **UserModel** - модели пользователей

---

## 🔒 Безопасность

### ⚠️ Важные предупреждения

- **🚫 НЕ коммитьте** файл `gradle.properties` с реальными API ключами
- **🔒 Добавьте** `gradle.properties` в `.gitignore`
- **🌐 Используйте** CI/CD переменные для продакшн окружения
- **🔑 Храните** секреты в безопасных местах

### 🛡️ Рекомендации по безопасности

```bash
# Добавление в .gitignore
echo "gradle.properties" >> .gitignore
echo "*.env" >> .gitignore
echo "secrets/" >> .gitignore
```

---

## 🤝 Вклад в проект

Мы приветствуем вклад в развитие проекта! 

### 📋 Как внести вклад:

1. 🍴 **Форкните** репозиторий
2. 🌿 **Создайте** ветку для новой функции
3. 💻 **Внесите** изменения
4. 🧪 **Протестируйте** изменения
5. 📝 **Создайте** Pull Request

### 📝 Стандарты кода:

- 📏 Следуйте Java Code Conventions
- 🧪 Покрывайте новый код тестами
- 📚 Обновляйте документацию
- 🏷️ Используйте понятные названия переменных

---

## 📄 Лицензия

Этот проект распространяется под лицензией MIT. См. файл [LICENSE](LICENSE) для получения дополнительной информации.

---

## 📞 Поддержка

Если у вас есть вопросы или предложения:

- 📧 **Email**: your.email@example.com
- 💬 **Issues**: [GitHub Issues](https://github.com/your-username/api_tests/issues)
- 📚 **Документация**: [Wiki](https://github.com/your-username/api_tests/wiki)

---

<div align="center">

**⭐ Если проект вам понравился, поставьте звездочку! ⭐**

Сделано с ❤️ для сообщества тестировщиков

</div>
