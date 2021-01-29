package cn.wu.demo.dictionary.controller;

import cn.wu.demo.dictionary.cache.DistrictCache;
import cn.wu.demo.dictionary.config.PageResult;
import cn.wu.demo.dictionary.pojo.entity.District;
import cn.wu.demo.dictionary.pojo.query.DistrictQuery;
import cn.wu.demo.dictionary.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wusq
 * @date 2021/1/29
 */
@Api(description = "行政区划")
@RestController
@RequestMapping("v1/districts/anon")
public class DistrictController {

    @Autowired
    private DistrictService service;

    @ApiOperation("分页查询")
    @PostMapping("page")
    public PageResult<District> page(@RequestBody DistrictQuery query){
        PageResult<District> result = new PageResult<>();
        query.init();
        result.setTotal(service.countPage(query));
        if(result.getTotal() > 0){
            result.setRows(service.listPage(query));
        }
        return result;
    }

    @ApiOperation("查询省")
    @GetMapping("list/province")
    public List<District> listProvince(){
        return service.listByType(DistrictCache.PROVINCE);
    }

    @ApiOperation("根据父ID查询")
    @GetMapping("list/parent/{parentId}")
    public List<District> listByParentId(@PathVariable String parentId){
        return service.listByParentId(parentId);
    }

    @ApiOperation("查询市树")
    @GetMapping("tree/city")
    public List<District> cityTree(){
        return service.tree(4);
    }

    @ApiOperation("查询县树")
    @GetMapping("tree/county")
    public List<District> countyTree(){
        return service.tree(6);
    }

}
