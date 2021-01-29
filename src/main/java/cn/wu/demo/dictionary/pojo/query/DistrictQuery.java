package cn.wu.demo.dictionary.pojo.query;

import cn.wu.demo.dictionary.config.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wusq
 * @date 2021/1/29
 */
@ApiModel("行政区划查询参数")
public class DistrictQuery extends PageQuery {

    @ApiModelProperty("行政区划代码")
    private String id;

    @ApiModelProperty("名称")
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
