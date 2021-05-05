# 笔记
## 2021/05/04
整合shiro的之前，如果整了Spring security 那么需要将security的依赖注释掉。如果还是不好使的话，需要查看依赖的maven jar 是否还存在，如果存在，那么手动delete掉就可以了。

## 2021/05/05
问题：java.sql.SQLException: url not set
解决办法：在pom.xml里面加入
<resource>
    <directory>src/main/resources</directory>
    <includes>
        <include>**/*.yml</include>
        <include>**/*.properties</include>
        <include>**/*.xml</include>
    </includes>
    <filtering>false</filtering>
</resource>