# Responder

[![Build Status](https://travis-ci.org/Mityushin/Responder.svg?branch=master)](https://travis-ci.org/Mityushin/Responder)
[![Coverage Status](https://coveralls.io/repos/github/Mityushin/Responder/badge.svg?branch=master)](https://coveralls.io/github/Mityushin/Responder?branch=add-coveralls)
[![License: GPLv3.0](https://img.shields.io/badge/License-GPLv3.0-blue.svg)](https://github.com/Mityushin/Responder/blob/master/LICENSE)

#### Requires:
* [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* (optional) [Docker](https://www.docker.com/)

-------------

### How to run application

#### 1. Clone repository
> `git clone git@github.com:Mityushin/Responder.git`

#### 2. Setup VK API properties
> 1. Open `src/main/resources/vk.properties`
> 2. Specify params gotten from VK
> 3. (Optional) Open `src/main/java/ru/mityushin/responder/controller/CallbackController.java` and put your Callback URL value in `@RequestMapping` annotation
>
> | Variable name       | Description                      |
> |---------------------|----------------------------------|
> | vk.api.access-token | VK API access token              |
> | vk.api.v            | VK API usage version             |
> | vk.api.secret       | Callback API secret key          |
> | vk.api.confirmation | Callback API confirmation string |

#### 3. Create directory for logs
> On Linux host: `sudo mkdir /var/log/responder`
>
> On Windows host: `mkdir C:\var\log\responder`

#### 4. Build jar
> On Linux host: `./mvnw clean package`
>
> On Windows host: `mvnw.cmd clean package`

#### 5. Run application
You have two ways to run this application:
* with java on your host
* with Docker

##### Way 1. Run with java
> `java -jar targer/Responder.jar`

##### Way 2. Run with Docker
> ###### 1. Build docker image
>
> `sudo docker build -t responder .`
>
> ###### 2. Run docker container
>
> `sudo docker run --name=responder -d -p 80:8090 -v /var/log/responder/:/var/log/responder/ responder`

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
