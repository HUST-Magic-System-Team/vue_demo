#!/usr/bin/env bash

cd /www/site/com.manli/manli_java

#安装私有jar包
mvn install:install-file -Dfile=jar_package/json-org.jar -DgroupId=com.local.jar_package -DartifactId=json-org -Dversion=1.0.0 -Dpackaging=jar

#修改profile
sed -i 's|dev|prod|' src/main/resources/application.yaml

#打包
mvn -P prod clean package
