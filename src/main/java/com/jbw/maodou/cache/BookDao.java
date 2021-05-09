package com.jbw.maodou.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;

/**
 * Ehcache缓存在Java开发领域久负盛名，在Spring Boot 中，只需要一个配置文件就可以将Ehcache集成到项目中。
 *
 * 如果Ehcache的依赖存在，并且在classpath下有一个名为ehcache.xml的Ehcache配置文件，那么EhCacheManager将会自动作为缓存的实现。因此，在
 * resources目录下创建ehcache.xml文件作为Ehcache缓存的配置文件。
 * 添加的CacheConfig注解指明使用的缓存的名字，这个配置可选，若不使用CacheConfig注解，则直接在Cacheable注解中指明缓存名字。
 *
 */
@Service
@CacheConfig(cacheNames = "book_cache")
public class BookDao {

    @Autowired
    MyKeyGenerator myKeyGenerator;

    /**
     *@Cacheable注解表示对该方法进行缓存，默认情况下，缓存的key是方法的参数，缓存的value是方法的返回值。
     * 当开发者在其他类中调用该方法时，首先会根据调用的参数查看缓存中是否有相关的数据，若有，则直接使用缓存数据，该方法
     * 不会执行，否则，执行该方法，执行成功后将返回值缓存起来，但若是在当前类中调用该方法，则缓存不会生效。
     * @Cacheable注解还有一个属性condition用来描述缓存的执行时机，例如：@Cacheable（condition="#id%2==0"）表示
     * 当ID对2取模为0时才进行缓存，否则不缓存。
     * 如果开发者不想使用默认的key，也可以自定义key 如：@CachePut(key = "#book.id") 缓存的key为book对象中id的值。
     * @CacheEvict(key = "#id") 表示缓存的key为参数id；
     *
     * 如果这些key不能满足开发的需求，开发者也可以自定义缓存key的生成器keyGenerator。
     * @param id
     * @return
     */
    @Cacheable(keyGenerator = "myKeyGenerator")
    public Book getBookById(Integer id) {
        System.out.println("getBookById");
        Book book = new Book();
        book.setId(id);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        return book;
    }

    /**
     * @CachePut注解一般用于数据更新方法上，与@Cacheable注解不同，添加了@CachePut注解的方法每次在执行时
     * 都不会去检查缓存中是否有数据，而是直接执行方法，然后将方法的执行结果缓存起来，如果该key对应的数据已经缓存起来了
     * 那么会覆盖之前的数据，这样可以避免再次加载到数据时是脏数据。同时@CachePut与@Cacheable具有类似的属性。
     * @param book
     * @return
     */
    @CachePut(key = "#book.id")
    public Book updateBookById(Book book) {
        System.out.println("updateBookById");
        book.setName("三国演义2");
        return book;
    }

    /**
     * @CacheEvict注解一般用于删除方法上，表示移除一个key对应的缓存。
     * @CacheEvict注解有两个特殊属性：
     * allEntries和beforInvocation，其中allEntries表示是否将所有缓存数据都移除，默认为false，beforInvocation
     * 表示是否在方法执行前移除缓存中的数据，默认为false，即在方法执行之后移除缓存中的数据。
     * @param id
     */
    @CacheEvict(key = "#id")
    public void deleteBookById(Integer id) {
        System.out.println("deleteBookById");
    }
}
