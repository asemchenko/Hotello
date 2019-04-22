## Заказ гостиницы
20. Система Заказ гостиницы.

 Клиент выбирает номер, указывая
количество мест в номере, класс апартаментов и время пребывания.
Клиент оформляет заказ. При этом номер бронируется для клиента.
Администратор принимает либо отклоняет
поступивший заказ. В случае принятия заказа, Клиент оплачивает
его. В случае отклонения заказа бронь снимается.

### Setup
* JDK 1.8 or higher
* Apache Maven 3.6.1 or higher
* MySQL 8.0 or higher
* Apache Tomcat 8.0 or higher

### Installation
1. Clone project from GitHub with

    `git clone https://github.com/asemchenko/Hotello.git`
1. Specify database parameters in `$PROJECT_ROOT/src/main/resources/dbConnectionParams.properties` file
1. Specify tomcat parameters in `pom.xml` file if you need it
1. Execute script `src/main/db/create_schema.sql` to create database schema

### Deploying
There is a few ways to deploy an application
##### Deploying with Maven
1. Execute command in PROJECT_ROOT directory

  `mvn clean tomcat7:deploy`
1.  That's it

##### Manual deplying
1. Use maven to create `war` file

`mvn clean package`

`war`-file will appear in `$PROJECT_ROOT/target directory`
2. Copy generated `war`-file in `$CATALINA_HOME/webapps/`


#### Pay attention
It might be need to restart your tomcat server after deploying.
In Linux-based OS distributions with `systemd` subsystem:

`systemctl restart tomcat[tomcat_version]`

e.g.

`systemctl restart tomcat8`
