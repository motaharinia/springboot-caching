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

### Project descriptions :
please see application.properties files in resources folder and select a active profile "dev" or "com" to run project. you can check test methods too.  
steps before run project:
1. install redis server (Redis-x64-3.2.100) from "requirements" directory in project
2. install redis desktop manager to view redis cached data (redis-desktop-manager-0.9.3.817) from "requirements" directory in project
3. set redis password:
  - Open Run Window by Winkey + R. Type services.msc. Search Redis service. Click stop  
  - edit your C:\Program Files\Redis\redis.windows.conf and C:\Program Files\Redis\redis.windows-service.conf file with administrator user, find this line
`# requirepass foobared
`Then uncomment it and change foobared to your password (Important: please check that requirepass doesnt have blank space before it).
 - Open Run Window by Winkey + R. Type services.msc. Search Redis service. Click start


<hr/>
<a href="mailto:eng.motahari@gmail.com?"><img src="https://img.shields.io/badge/gmail-%23DD0031.svg?&style=for-the-badge&logo=gmail&logoColor=white"/></a>
