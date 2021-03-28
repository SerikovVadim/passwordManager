Тестовое приложение для хранения паролей


Используемые технологии: Spring(Boot,Date,Security), thymeleaf, база данных PostgreSQL


Для подключения к базе данных, в файле application.property поменяйте следующие значения:
# "passwordManager" - название базы данных можете установить своё или оставить это
spring.datasource.url=jdbc:postgresql://localhost:5432/passwordManager 

#Введите свой логин от базы данных
spring.datasource.username=postgres

#Введите свой пароль от базы данных
spring.datasource.password=root

Запустить приложение: mvn spring-boot:run

Перейти в браузере на страницу: http://localhost:8080/






