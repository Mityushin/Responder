# Responder

[![Build Status](https://travis-ci.org/Mityushin/Responder.svg?branch=master)](https://travis-ci.org/Mityushin/Responder)
[![License: GPLv3.0](https://img.shields.io/badge/License-GPLv3.0-blue.svg)](https://github.com/Mityushin/Responder/blob/master/LICENSE)

#### Requires:
* [Git](https://git-scm.com/)
* [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* (optional) [Docker](https://www.docker.com/)

-------------

### How to run application

#### 1. Clone repository
> `git clone git@github.com:Mityushin/Responder.git`

#### 2. Setup VK API properties
> 1. Open `src/main/resources/vk.properties`
> 2. Specify params gotten from VK

#### 3. Create directory for logs
> On Windows host: `sudo mkdir /var/log/responder`
>
> On Linux host: `mkdir C:\var\log\responder`

#### 4. Build jar
> On Windows host: `mvnw.cmd clean package`
>
> On Linux host: `./mvnw clean package`

#### 5. Run application
You have two ways to run this application:
* with java on your host
* with Docker

##### Way 1. Run with java
> `java -jar targer/Responder.jar`
>
##### Way 2. Run with Docker
> ###### 1. Build docker image
>
> `sudo docker build -t responder .`
>
> ###### 2. Run docker container
>
> `sudo docker run --name=responder -d -p 8090:8090 -v /var/opt/responder/:/var/opt/responder/ -v /var/log/responder/:/var/log/responder/ responder`

-------------

### Test case

Необходимо выполнить интеграцию с [BotAPI VK](https://vk.com/dev/bots_docs).

В рамках задания нужно создать бота который при его упоминании будет 
цитировать присланный ему текст. Пример взаимодействия с подобным ботом см. ниже:

> **Person:** `@responder-bot` test
>
> **Bot:** Вы сказали: @responder-bot test
>
> **Person:** `@responder-bot` привет!
>
> **Bot:** Вы сказали: @responder-bot привет!

##### Требования к реализации

В качестве решения хотелось бы получить ссылку на git репозиторий в котором находятся 
исходники Spring Boot приложения выполняющего логику бота. 
Все параметры необходимые для корректного запуска и проверки должны задаваться в 
конфигурационных файлах (необходимо решить какие именно параметры).
Все сущности с помощью которых осуществляется взаимодействие должны быть представлены 
в виде POJO.
В readme должен быть описан процесс запуска приложения и необходимые параметры конфигурации.
Качество кода и выбранная внутренняя структура компонентов/сервисов также оценивается.

**Важно! Нельзя использовать готовые библиотеки-реализации api для Java.**

При реализации может потребоваться использование внешних https адресов для локальной машины. 
Для этого можно использовать [ngrok](https://ngrok.com/).
