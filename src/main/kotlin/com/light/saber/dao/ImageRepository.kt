package com.light.saber.dao

import com.light.saber.model.Image
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

/**
 * Created by jack on 2017/7/17.
 */

interface ImageRepository : PagingAndSortingRepository<Image, Long> {

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.category like %:category% order by a.gmtModified desc")
    fun findByCategory(@Param("category") category: String): MutableList<Image>

    @Query("select count(*) from #{#entityName} a where a.url = :url")
    fun countByUrl(@Param("url") url: String): Int

    /**源数据列表*/
    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 order by rand()") override fun findAll(pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.category like %:searchText% order by rand()")
    fun search(@Param("searchText") searchText: String, pageable: Pageable): Page<Image>

    /**收藏列表*/
    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.isFavorite=1 order by a.gmtModified desc")
    fun findAllFavorite(pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.isFavorite=1 and a.category like %:searchText% order by a.gmtModified desc")
    fun searchFavorite(@Param("searchText") searchText: String, pageable: Pageable): Page<Image>

    @Modifying
    @Transactional
    @Query("update #{#entityName} a set a.isFavorite=1,a.gmtModified=now() where a.id=:id")
    fun addFavorite(@Param("id") id: Long)

    @Modifying
    @Transactional
    @Query("update #{#entityName} a set a.isDeleted=1 where a.id=:id")
    fun delete(@Param("id") id: Long)

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.sourceType=1 order by rand()")
    fun findGankAll(pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.sourceType=1  and a.isDeleted=0 and a.category like %:searchText% order by rand()")
    fun searchGank(@Param("searchText") searchText: String, pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.sourceType=:source_type order by rand()")
    fun findAllImageByType(@Param("source_type") source_type: Int, pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.sourceType=:source_type  and a.isDeleted=0 and a.category like %:searchText% order by rand()")
    fun searchImageByType(@Param("source_type") source_type: Int, @Param("searchText") searchText: String, pageable: Pageable): Page<Image>

}


/**
@Query注解里面的value和nativeQuery=true,意思是使用原生的sql查询语句.
sql模糊查询like语法,我们在写sql的时候是这样写的

like '%?%'

但是在@Query的value字符串中, 这样写

like %?1%

另外，要注意的是： 对于执行update和delete语句需要添加@Modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作。
UPDATE或者DELETE操作需要使用事务，此时需要 定义Service层，在Service层的方法上添加事务操作。
注意JPQL不支持INSERT操作，可以使用 nativeQuery 来进行插入操作。
所谓本地查询，就是使用原生的sql语句（根据数据库的不同，在sql的语法或结构方面可能有所区别）进行查询数据库的操作。

使用@Param注解注入参数　


SPEL表达式(使用时请参考最后的补充说明)

'#{#entityName}'值为'Image'对象对应的数据表名称(image)。

'#{#entityName}'是实体类的名称,
　　实体类 Image 使用@Entity注解后，spring会将实体类 Image 纳入管理。默认'#{#entityName}'的值就是 'Image'。
　　但是如果使用了@Entity(name = "image")来注解实体类 Image, 此时'#{#entityName}'的值就变成了'image'。
　　到此，事情就明了了，只需要在用 @Entity 来注解实体类时指定 name 为此实体类对应的表名。在 JPQL 语句中，就可以把'#{#entityName}'来作为数据表名使用。　

 */
