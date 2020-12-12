## Spring Boot Caching with Redis
The Spring Framework provides support for transparently adding caching to an application. At its core, the abstraction applies caching to methods, thus reducing the number of executions based on the information available in the cache. The caching logic is applied transparently, without any interference to the invoker. Spring Boot auto-configures the cache infrastructure as long as caching support is enabled via the @EnableCaching annotation.

### Supported Cache Providers:
The cache abstraction does not provide an actual store and relies on abstraction materialized by the org.springframework.cache.Cache and org.springframework.cache.CacheManager interfaces.
If you have not defined a bean of type CacheManager or a CacheResolver named cacheResolver (see CachingConfigurer), Spring Boot tries to detect the following providers (in the indicated order):
1. Generic
2. JCache (JSR-107) (EhCache 3, Hazelcast, Infinispan, and others)
3. EhCache 2.x
4. Hazelcast
5. Infinispan
6. Couchbase
7. Redis
8. Caffeine
9. Simple

[further reference](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)

### Project Descriptions :
please see application.properties files in resources folder and select a active profile "dev" or "com" to run project. you can check test methods too.  
oracle configuration steps:
- download oracle 18 express from : https://drive.google.com/file/d/1sdMKUH9eXfYFyRRYMcYG2-rDc2Nav5Yz/view
- install oracle and in the setup wizard set password:"123456" for "sys" and "system" users
- in windows environment add these variables:
    - ORACLE_HOME = C:\app\MyUser\product\18.0.0\dbhomeXE
    - ORACLE_SID = XE
- execute "C:\app\MyUser\product\18.0.0\dbhomeXE\bin\sqlplus.exe" and enter with "system" and "123456" and run these commands to create motaharinia schema with 123456 password:
    - ALTER SESSION SET CONTAINER = XEPDB1;
    - CREATE BIGFILE TABLESPACE tbsmot_perm_01 DATAFILE 'tbsmot_perm_01.dat' SIZE 20M AUTOEXTEND ON;
    - CREATE TEMPORARY TABLESPACE tbsmot_temp_01 TEMPFILE 'tbsmot_temp_01.dbf' SIZE 20M AUTOEXTEND ON;
    - CREATE USER motaharinia IDENTIFIED BY 123456 DEFAULT TABLESPACE tbsmot_perm_01 TEMPORARY TABLESPACE tbsmot_temp_01 QUOTA 20M on tbsmot_perm_01;
    - GRANT create session TO motaharinia;
    - GRANT create table TO motaharinia;
    - GRANT create view TO motaharinia;
    - GRANT create any trigger TO motaharinia;
    - GRANT create any procedure TO motaharinia;
    - GRANT create sequence TO motaharinia;
    - GRANT create synonym TO motaharinia;
    - GRANT connect TO motaharinia;
    - alter user motaharinia default role all;

redis configuration steps:
- install redis server (Redis-x64-3.2.100) from "requirements" directory in project
- install redis desktop manager to view redis cached data (redis-desktop-manager-0.9.3.817) from "requirements" directory in project
- set redis password:
    - Open Run Window by Winkey + R. Type services.msc. Search Redis service. Click stop  
    - edit your C:\Program Files\Redis\redis.windows.conf and C:\Program Files\Redis\redis.windows-service.conf file with administrator user, find this line
`# requirepass foobared
`Then uncomment it and change foobared to your password (Important: please check that requirepass doesnt have blank space before it).
    - Open Run Window by Winkey + R. Type services.msc. Search Redis service. Click start
    
### IntellliJ IDEA Configurations :
- IntelijIDEA: Help -> Edit Custom Vm Options -> add these two line:
    - -Dfile.encoding=UTF-8
    - -Dconsole.encoding=UTF-8
- IntelijIDEA: File -> Settings -> Editor -> File Encodings-> Project Encoding: form "System default" to UTF-8. May be it affected somehow.
- IntelijIDEA: File -> Settings -> Editor -> General -> Code Completion -> check "show the documentation popup in 500 ms"
- IntelijIDEA: File -> Settings -> Editor -> General -> Auto Import -> check "Optimize imports on the fly (for current project)"
- IntelijIDEA: File -> Settings -> Editor -> Color Scheme -> Color Scheme Font -> Scheme: Default -> uncheck "Show only monospaced fonts" and set font to "Tahoma"
- IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Configuration -> Environment -> VM Options: -Dspring.profiles.active=dev
- IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Code Coverage -> Fix the package in include box

<hr/>
<a href="mailto:eng.motahari@gmail.com?"><img src="https://img.shields.io/badge/gmail-%23DD0031.svg?&style=for-the-badge&logo=gmail&logoColor=white"/></a>
