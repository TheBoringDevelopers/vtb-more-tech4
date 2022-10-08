![N|Solid](https://wegoapp.ru/static/vtb.jpg)
# ВТБ More.Tech 4.0 (Web Трек)
## Бекенд часть

Взаимодействие с [API ВТБ](https://publickm.notion.site/API-WEB-e9d312db434f4d35af5a96faa27ace37#4d03cdd4040d4fe19cde3d12ded7049c), бизнес-логика с отображением, покупкой и обменом NFT
## Стек технологий
- Java 11
- Sprong boot 2.5.6
- JDBC
- Maven
- Liquibase
- H2 Database
- nginx
<br/>
Запущенный рабочий бекенд baseUrl - https://wegoapp.ru/api Фронтенд на чистом url https://wegoapp.ru/ <br/>
Хостинг https://vds.timeweb.ru/ <br/>
Картинки тоже получаются по url'ам с виртуалки Пример: https://wegoapp.ru/static/q.jpg <br/>
Для запуска необходимо сделать сборку с помощью Maven в корне проекта, установить JDK и выполнить <br/> java -jar api-0.0.1-SNAPSHOT.jar <br/>


## Описание приложения

##### Набор REST Controller'ов
`vtb-more-tech4/api/src/main/java/ru/the_boring_developers/api/controller/`

 - **NftController** - Работа с nft. Получение одежды и аваторов, покупка.
 <br/> - /api/nft/usual - получение nft аватаров
 <br/> - /api/nft/clothes - получение nft одежды

 - **TransactionController** - Получение списка транзакций
 <br/> - /api/transaction - получение списка транзакций
 - **TransferController** - Переводы рублей, matic, nft
 <br/> - /api/transfer/ruble - перевод рубля
 <br/> - /api/transfer/matic - перевод matic
<br/> - /api/transfer/nft - перевод nft
 - **WalletController** - Получение баланса коинов и nft
 <br/>- /api/wallet/nft - баланс nft
 <br/>- /api/wallet/coin - баланс коинов

##### Бизнес-логика
`vtb-more-tech4/api/src/main/java/ru/the_boring_developers/api/service/nft/`
  - **BalanceServiceImpl** - сервис взаимодействия с балансом
  <br/> - balanceNft() - баланс nft. Рест запрос в втб, подкладывание url картинки по uri
  <br/> - balanceCoin() - баланс по коинам. Рест запрос в втб, проксирование запроса
#####
  - **DelayedOperation** - техническая сущность отложенной задачи для работы с транзакцией
####
  - **TransactionServiceImpl** - сервис взаимодействия с балансом
  <br/> - find() - список транзакций
  <br/> - create() - создание в бд транзакции и запуск отложенной задачи по проверке статуса
####
  - **TransactionTaskService** - сервис для работы с отложенными задачами. Реализовано на основе ExecutorService и DelayQueue. С периодичностью ходит по Ресту за статусом транзакции и обновляет статус
####
- **TransferServiceImpl** - сервис переводов. Реализован ёмко паттерн command, взависимости от типа транзакции исполнится нужный метод
####
- **NftServiceImpl** - сервис взаимодействия с nft
 <br/> - findAll() - поиск nft по типу (аватар или одежда)
 <br/> - buy() - покупка nft. У каждой nft есть цена в рублях и matic в нашей бд, проверяется баланс по необходимым коинам запросом в втб, вызывается метод генерации нфт на кошельке юзера и создается транзакция по генерации. Создается перевод с кошелька юзера на главный кошелёк, создаётся вторая транзакция.
#### Сущности
`vtb-more-tech4/common/src/main/java/ru/the_boring_developers/common/entity`
#### Репозитории
`vtb-more-tech4/common/src/main/java/ru/the_boring_developers/common/repository`
Взаимодействие по jdbcOperations, с маппингом через самописные Extractor'ы с помощью нативного sql
#### Взаимодействие с ВТБ
`/api/src/main/java/ru/the_boring_developers/api/rest`
- **VtbRestTemplate** - Рест клиент для запросов в ВТБ
<br/> - Необходимые переменные для клиента лежат в application.properties 
