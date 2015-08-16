# JPA + Hibernate + Spring + Data JPA - quickstart template #
## Templates/Examples ##
* Hibernate standalone
* Hibernate + JPA
* Hibernate + Spring xml
* Hibernate + JPA + Spring xml
* Hibernate + JPA + Spring annotations
* Hibernate + JPA + Spring annotations + Spring Data JPA

## Specs ##
* Hibernate 4.3.11.Final
* JPA 2.1
* Spring Framework 4.2.0
* Spring Data JPA 1.8.2


## Notes ##
* This project contains multiple configuration files (like Hibernate config with or without JPA, Spring etc), you can delete those that you don't need.
* Change to your database settings in configuration files.
* Hibernate uses a database dialect that is detected automatically. Check the log files to verifiy the detected dialect is correct. Example: `[main] INFO  org.hibernate.dialect.Dialect - HHH000400: Using dialect: org.hibernate.dialect.MySQL5Dialect`
* If the detected dialect is incorrect, manually set the correct one in the configuration files. Note that their are multiple dialects for some databases, like: MySQLDialect, MySQLInnoDBDialect, MySQL5InnoDBDialect, MySQL57InnoDBDialect. See [http://docs.jboss.org/hibernate/orm/4.3/javadocs/org/hibernate/dialect/package-summary.html](http://docs.jboss.org/hibernate/orm/4.3/javadocs/org/hibernate/dialect/package-summary.html) for a list. 