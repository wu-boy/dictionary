package cn.wu.demo.dictionary.mapper;

import cn.wu.demo.dictionary.config.PageQuery;
import cn.wu.demo.dictionary.pojo.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author wusq
 * @date 2021/1/29
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    Integer countPage(PageQuery query);

    List<Dictionary> listPage(PageQuery query);

    List<Dictionary> listByType(String type);

    List<Dictionary> listByLabel(String label);
}
