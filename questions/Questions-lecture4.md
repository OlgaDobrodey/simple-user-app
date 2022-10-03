1. **HTTP методов для создания RESTful**

- HTTP /customers; /customers/{id}
- GET 200 (OK)  ; 200 (OK), конкретный customer. 404 (Not Found)
- PUT 404 (Not Found); 200 (OK) или 204 (No Content). 404 (Not Found)
- PATCH 405 (Method Not Allowed); 200 (OK) или 204 (No Content). 404 (Not Found)
- POST 201 (Created); 404 (Not Found).
- DELETE 404 (Not Found) ; 200 (OK) или 204 (No Content). 404 (Not Found)

2. **Что такое идемпотентные запросы**
   что подразумевает получение идентичных данных при использовании одних и тех же запросов
   (как при единичном обращении, так и при многократном)

- GET
- PUT

3. **Что такое Contract First, Code First**

4. **Что такое Hibernate mapping file**

- Файл отображения (mapping file) используется для связи entity бинов и колонок
  в таблице базы данных. В случаях, когда не используются аннотации JPA, файл отображения .xml может быть полезен (
  например при использовании сторонних библиотек).

4. **Назовите некоторые важные аннотации, используемые для отображения в Hibernate**

- Hibernate поддерживает как аннотации из JPA, так и свои собственные, которые находятся в пакете
  org.hibernate.annotations. Наиболее важные аннотации JPA и Hibernate:

- **javax.persistence.Entity**: используется для указания класса как entity bean.
- **javax.persistence.Table** используется для определения имени таблицы из БД, которая будет отображаться на entity
  bean.
- **javax.persistence.Access**: определяет тип доступа, поле или свойство. Поле — является значением по умолчанию и если
  нужно, чтобы hibernate использовал методы getter/setter, то их необходимо задать для нужного свойства.
- **javax.persistence.Id:** определяет primary key в entity bean.
- **javax.persistence.EmbeddedId:** используется для определения **составного ключа** в бине.
- **javax.persistence.Column:** определяет имя колонки из таблицы в базе данных.
- **javax.persistence.GeneratedValue**: задает стратегию создания основных ключей. Используется в сочетании с
  javax.persistence.GenerationType enum.
- **javax.persistence.OneToOne:** задает связь один-к-одному между двумя сущностными бинами. Соответственно есть другие
  аннотации OneToMany, ManyToOne и ManyToMany.
- **org.hibernate.annotations.Cascade:** определяет каскадную связь между двумя entity бинами. Используется в связке с
  org.hibernate.annotations.CascadeType.
- **javax.persistence.PrimaryKeyJoinColumn:** определяет внешний ключ для свойства. Используется вместе с
  org.hibernate.annotations.GenericGenerator и org.hibernate.annotations.Parameter.

5. **В чем разница между openSession и getCurrentSession**

- **Hibernate SessionFactory getCurrentSession()** возвращает сессию, связанную с контекстом.
- Но для того, чтобы это работало, нам нужно настроить его в конфигурационном файле hibernate.
  Так как этот объект session связан с контекстом hibernate, то отпадает необходимость к его закрытию. Объект session
  закрывается вместе с закрытием SessionFactory.

- <property name="hibernate.current_session_context_class">thread</property>
- Метод Hibernate SessionFactory openSession() всегда создает **новую сессию**. Мы должны обязательно контролировать
  закрытие объекта сеанса по завершению всех операций с базой данных. Для многопоточной среды необходимо создавать новый
  объект session для каждого запроса.

  Существует еще один метод openStatelessSession(), который возвращает session без поддержки состояния. Такой объект **не
  реализует первый уровень кэширования и не взаимодействует с вторым уровнем.** Сюда же можно отнести игнорирование
  коллекций и некоторых обработчиков событий. Такие объекты могут быть полезны при загрузке больших объемов данных без
  удержания большого кол-ва информации в кэше.

6. **Как используется вызов метода Hibernate Session merge()?**

- Hibernate merge() может быть использован для обновления существующих значений, метод **создает копию** из переданного
  объекта сущности и возвращает его. Возвращаемый объект является частью контекста персистентности и отслеживает любые
  изменения, а переданный объект не отслеживается.

7. **В чем разница между Hibernate save(), saveOrUpdate() и persist()**

- Hibernate save() используется для сохранения сущности в базу данных. Проблема с использованием метода save() заключается в том, что он может быть вызван без транзакции. А следовательно если у нас имеется отображение нескольких объектов, то только первичный объект будет сохранен и мы получим несогласованные данные. Также save() немедленно возвращает сгенерированный идентификатор.
- Hibernate persist() аналогичен save() с транзакцией. persist() не возвращает сгенерированный идентификатор сразу.
- Hibernate saveOrUpdate() использует запрос для вставки или обновления, основываясь на предоставленных данных. Если данные уже присутствуют в базе данных, то будет выполнен запрос обновления. Метод saveOrUpdate() можно применять без транзакции, но это может привести к аналогичным проблемам, как и в случае с методом save().

8. **Что такое каскадные связи (обновления) и какие каскадные типы есть в Hibernate**

- @Cascade
- None: без Cascading. Формально это не тип, но если мы не указали каскадной связи, то никакая операция для родителя не будет иметь эффекта для ребенка.
- ALL: Cascades save, delete, update, evict, lock, replicate, merge, persist. В общем — всё.
- SAVE_UPDATE: Cascades save и update. Доступно только для hibernate.
- DELETE: передает в Hibernate native DELETE действие. Только для hibernate.
- DETATCH(удаление из ссессии), MERGE, PERSIST, REFRESH и REMOVE – для простых операций.
- LOCK: передает в Hibernate native LOCK действие.
- REPLICATE(копировать):  когда мы реплицируем person entity, его связанный адрес также реплицируется с заданным нами идентификатором. передает в Hibernate native REPLICATE действие.

***ЗАДАЧА***
Есть таблица ***Products***

- ProductID ProductName SupplierID CategoryID Unit Price
- Найти РОдукты которые стоят в больше 20, но меньше 22

**SELECT** ProductName, Price **From** Products **Where** Price **Between** 20 and 22;