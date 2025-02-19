### **README.md**

```markdown
# XLSX Max Number Service

Это тестовый проект представляет собой веб-сервис на Spring Boot, который позволяет найти N-е максимальное число в столбце Excel-файла (формат `.xlsx`).
Для документации API используется **Swagger**.

---

## **Требования**

Для запуска проекта вам потребуется:
- Java 11 или выше
- Maven 3.6+
- Git (для клонирования репозитория)

---

## **Установка и запуск**

### 1. Клонирование репозитория

Склонируйте репозиторий с GitHub:

```bash
git clone https://github.com/your-repo/maxNumber.git
cd maxNumber
```

### 2. Сборка проекта

Соберите проект с помощью Maven:

```bash
mvn clean install
```

### 3. Запуск приложения

Запустите приложение с помощью команды:

```bash
mvn spring-boot:run
```

Приложение будет доступно по адресу: `http://localhost:8080`.

---

## **Использование Swagger**

1. Откройте Swagger UI в браузере:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

2. Найдите метод `/api/find-nth-max` и нажмите "Try it out".

3. Введите путь к локальному `.xlsx` файлу и значение `N` (порядковый номер максимального числа).

4. Нажмите "Execute", чтобы получить результат.
