package cn.wu.demo.dictionary.service;

import cn.wu.demo.dictionary.config.PageQuery;
import cn.wu.demo.dictionary.mapper.DictionaryMapper;
import cn.wu.demo.dictionary.pojo.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典服务
 * @author wusq
 * @date 2021/1/29
 */
@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper mapper;

    public Integer countPage(PageQuery query){
        return mapper.countPage(query);
    }

    public List<Dictionary> listPage(PageQuery query){
        return mapper.listPage(query);
    }

    public List<Dictionary> listByType(String type){
        return mapper.listByType(type);
    }

    public String getLabel(List<Dictionary> dictionaryList, String value){
        String result = null;
        if(dictionaryList == null || dictionaryList.isEmpty()){
            return result;
        }
        for (Dictionary dictionary : dictionaryList) {
            if(dictionary.getValue().equals(value)){
                result = dictionary.getLabel();
                break;
            }
        }
        return result;
    }
}
