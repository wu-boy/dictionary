package cn.wu.demo.dictionary.controller;

import cn.wu.demo.dictionary.config.PageResult;
import cn.wu.demo.dictionary.pojo.entity.Dictionary;
import cn.wu.demo.dictionary.pojo.query.DictionaryQuery;
import cn.wu.demo.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wusq
 * @date 2021/1/15
 */
@Api(description = "数据字典")
@RestController
@RequestMapping("v1/dictionaries/anon")
public class DictionaryController {

    @Autowired
    private DictionaryService service;

    @ApiOperation("分页查询")
    @PostMapping("page")
    public PageResult<Dictionary> page(@RequestBody DictionaryQuery query){
        PageResult<Dictionary> result = new PageResult<>();
        query.init();
        result.setTotal(service.countPage(query));
        if(result.getTotal() > 0){
            result.setRows(service.listPage(query));
        }
        return result;
    }

    @ApiOperation("根据类别查询")
    @GetMapping("list/type/{type}")
    public List<Dictionary> listByType(@PathVariable String type){
        return service.listByType(type);
    }
}
