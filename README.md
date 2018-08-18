# kotlin-simple-springboot

## Run MySQL
```
$ docker run --name mysql -e MYSQL_ROOT_PASSWORD=mysql -d -p 3306:3306 mysql
$ docker exec -it (docker ps -q) bash
```

```
mysql -u root -p
create schema `journaler_api` default character set utf8 ;
```


