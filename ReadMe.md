**Simple user application**
**Library**
***Folder Inheritance Implementations:***
- src/main/java/by/dobrodey/user_app/model/mapping.

***Polymorphic queries to derived classes:***
- src/main/java/by/dobrodey/user_app/model/mapping/joined/RunnerJoined.java
- src/main/java/by/dobrodey/user_app/model/mapping/single_table/RunnerSingleTable.java
- src/main/java/by/dobrodey/user_app/model/mapping/table_pre_class/RunnerTablePreClass.java

***ManyToMany relationship***
- src/main/java/by/dobrodey/user_app/model/Book.java
- src/main/java/by/dobrodey/user_app/model/User.java(main)

***Added 2000000 random raws***
- src/main/java/db/migration
- method get (by.dobrodey.user_app.dao.impl.hibernate.BookDaoHibernateImpl.findAllBookWhereCountPagesMore)
- create index (src/main/resources/db/migration/V202210311224__createIndex.sql)

***Ehcache cache***
- src/main/resources/ehcache.xml
- by/dobrodey/user_app/model/Book.java

***Cascade***
- src/main/java/by/dobrodey/user_app/model/User.java(field Address (OneToOne))














