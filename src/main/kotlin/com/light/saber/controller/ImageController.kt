package com.light.saber.controller

import com.light.saber.dao.ImageRepository
import com.light.saber.model.Image
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * Created by jack on 2017/7/22.
 */

@RestController
class ImageController {

    @Autowired
    lateinit var imageRepository: ImageRepository


    @GetMapping("sotuJson")
    fun sotuJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                 @RequestParam(value = "size", defaultValue = "10") size: Int): Page<Image> {
        return getPageResult(page, size)
    }

    @GetMapping("sotuSearchFavoriteJson")
    fun sotuSearchJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                       @RequestParam(value = "size", defaultValue = "10") size: Int,
                       @RequestParam(value = "searchText", defaultValue = "") searchText: String): Page<Image> {
        return getFavoritePageResult(page, size, searchText)
    }

    @GetMapping("sotuGankSearchJson")
    fun sotuGankSearchJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                           @RequestParam(value = "size", defaultValue = "10") size: Int,
                           @RequestParam(value = "searchText", defaultValue = "") searchText: String): Page<Image> {
        return getGankPageResult(page, size, searchText)
    }


    @PostMapping(value = "addFavorite")
    fun addFavorite(@RequestParam(value = "id") id: Long): Boolean {
        imageRepository.addFavorite(id)
        return true
    }

    @PostMapping(value = "delete")
    fun delete(@RequestParam(value = "id") id: Long): Boolean {
        imageRepository.delete(id)
        return true
    }


    private fun getPageResult(page: Int, size: Int): Page<Image> {
        val sort = Sort(Sort.Direction.DESC, "id")
        val pageable = PageRequest.of(page, size, sort)
        return imageRepository.findAll(pageable)
    }

    private fun getPageResult(page: Int, size: Int, searchText: String): Page<Image> {
        val sort = Sort(Sort.Direction.DESC, "id")
        // 注意：PageRequest.of(page,size,sort) page 默认是从0开始
        val pageable = PageRequest.of(page, size, sort)
        if (searchText == "") {
            return imageRepository.findAll(pageable)
        } else {
            return imageRepository.search(searchText, pageable)
        }
    }

    private fun getGankPageResult(page: Int, size: Int, searchText: String): Page<Image> {
        val sort = Sort(Sort.Direction.DESC, "id")
        // 注意：PageRequest.of(page,size,sort) page 默认是从0开始
        val pageable = PageRequest.of(page, size, sort)
        if (searchText == "") {

            val findGankAll = imageRepository.findGankAll(pageable)
            return findGankAll
        } else {
            return imageRepository.searchGank(searchText, pageable)
        }
    }

    private fun getFavoritePageResult(page: Int, size: Int, searchText: String): Page<Image> {
        val sort = Sort(Sort.Direction.DESC, "id")
        val pageable = PageRequest.of(page, size, sort)
        if (searchText == "") {
            val allFavorite = imageRepository.findAllFavorite(pageable)
            return allFavorite
        } else {
            val searchFavorite = imageRepository.searchFavorite(searchText, pageable)
            return searchFavorite
        }
    }


    @GetMapping("sotuSearchByTypeJson")

    fun sotuSearchByTypeJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                             @RequestParam(value = "size", defaultValue = "10") size: Int,
                             @RequestParam(value = "searchText", defaultValue = "") searchText: String,
                             @RequestParam(value = "sourceType", defaultValue = "2") sourceType: Int
    ): Page<Image> {
        return getPageResultByType(page, size, searchText, sourceType)
    }

    private fun getPageResultByType(page: Int, size: Int, searchText: String, sourceType: Int): Page<Image> {
        val sort = Sort(Sort.Direction.DESC, "id")
        val pageable = PageRequest.of(page, size, sort)
        if (searchText == "") {
            return imageRepository.findAllImageByType(sourceType, pageable)
        } else {
            return imageRepository.searchImageByType(sourceType, searchText, pageable)
        }
    }


}
