package cn.wu.demo.dictionary.cache;

import cn.hutool.core.util.StrUtil;
import cn.wu.demo.dictionary.pojo.entity.District;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区划缓存
 * @author wusq
 * @date 2021/1/27
 */
public class DistrictCache {

    public static final String PROVINCE = "省";
    public static final String CITY = "市";
    public static final String COUNTY = "区";
    public static final String TOWN = "乡";

    public static List<District> provinceList = new ArrayList<>();

    public static List<District> cityList = new ArrayList<>();

    public static List<District> countyList = new ArrayList<>();

    public static List<District> townList = new ArrayList<>();

    public static final String getLabel(String id){
        String result = "";
        if(StrUtil.isBlank(id)){
            return result;
        }
        int len = id.length();
        if(len == 2){
            result = getLabel(provinceList, id);
        }else if(len == 4){
            result = getLabel(cityList, id);
        }else if(len == 6){
            result = getLabel(countyList, id);
        }else if(len == 9){
            result = getLabel(townList, id);
        }
        return result;
    }

    public static final String getLabel(List<District> list, String id){
        String result = "";
        for (District d : list) {
            if(id.equals(d.getId())){
                result = d.getLabel();
                break;
            }
        }
        return result;
    }
}
