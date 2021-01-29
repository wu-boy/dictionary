package cn.wu.demo.dictionary.mapper;

import cn.wu.demo.dictionary.config.PageQuery;
import cn.wu.demo.dictionary.pojo.entity.District;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author wusq
 * @date 2021/1/29
 */
public interface DistrictMapper extends BaseMapper<District> {

    void insertBatch(List<District> list);

    Integer countPage(PageQuery query);

    List<District> listPage(PageQuery query);

    List<District> listByType(String type);

    List<District> listByParentId(String parentId);

    List<District> listByIdLength(Integer idLength);
}
