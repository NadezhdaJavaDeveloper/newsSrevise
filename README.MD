## Инструкция по локальному запуску проекта

1) убедитесь, что на вашем ПК установлены IntelliJ IDEA и Docker Desktop;
2) клонируйте код приложения из репозитория командой:
```
git clone <https://github.com/NadezhdaJavaDeveloper/___>
```
3) откройте проект с помощью IntelliJ IDEA;
4) откройте терминал;
5) перейдите в папку docker `cd docker`
6) отчистите старые контейнеры и тома (если есть) `docker-compose down -v`
7) запустите контейнер командой `docker-compose up --build`;
8) зайдите в браузер и введите в адресной строке `http://localhost:8080/`


