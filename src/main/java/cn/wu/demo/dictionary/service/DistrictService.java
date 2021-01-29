package cn.wu.demo.dictionary.service;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import cn.wu.demo.dictionary.cache.DistrictCache;
import cn.wu.demo.dictionary.config.PageQuery;
import cn.wu.demo.dictionary.mapper.DistrictMapper;
import cn.wu.demo.dictionary.pojo.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusq
 * @date 2021/1/29
 */
@Service
@Transactional
public class DistrictService {

    @Autowired
    private DistrictMapper mapper;

    public void insert(List<District> list){
        mapper.insertBatch(list);
    }

    public Integer countPage(PageQuery query){
        return mapper.countPage(query);
    }

    public List<District> listPage(PageQuery query){
        return mapper.listPage(query);
    }

    public List<District> listByParentId(String parentId){
        return mapper.listByParentId(parentId);
    }

    public List<District> listByType(String type){
        return mapper.listByType(type);
    }

    public String getLabel(List<District> list, String id){
        String result = null;
        if(list == null || list.isEmpty()){
            return result;
        }
        for (District o : list) {
            if(o.getId().equals(id)){
                result = o.getLabel();
                break;
            }
        }
        return result;
    }

    public void initCache(){
        DistrictCache.provinceList = listByType(DistrictCache.PROVINCE);
        DistrictCache.cityList = listByType(DistrictCache.CITY);
        DistrictCache.countyList = listByType(DistrictCache.COUNTY);
        DistrictCache.townList = listByType(DistrictCache.TOWN);
    }

    public List<District> tree(Integer idLength){
        List<District> result = new ArrayList<>();

        List<District> list = mapper.listByIdLength(idLength);
        if(list == null || list.isEmpty()){
            return result;
        }

        list.forEach(o -> {
            for (District d : list) {
                if(d.getParentId().equals(o.getId())){
                    o.getChildren().add(d);
                }
            }
            if(StrUtil.isBlank(o.getParentId())){
                result.add(o);
            }
        });

        return result;
    }

    public void importData(){
        System.out.println("保存省");
        insert("D:\\test\\china_regions-3.3\\mysql\\province.sql", DistrictCache.PROVINCE);
        System.out.println("保存市");
        insert("D:\\test\\china_regions-3.3\\mysql\\city.sql", DistrictCache.CITY);
        System.out.println("保存区");
        insert("D:\\test\\china_regions-3.3\\mysql\\country.sql", DistrictCache.COUNTY);
        System.out.println("保存乡");
        insert("D:\\test\\china_regions-3.3\\mysql\\town.sql", DistrictCache.TOWN);
    }

    private void insert(String filePath, String type){

        Integer batchSize = 100;

        FileReader fileReader = new FileReader(filePath);
        List<String> list = fileReader.readLines();
        List<District> districtList = new ArrayList<>();
        for(int i=0; i< list.size(); i++){
            String[] array = list.get(i).replace(" ", "").replace("'", "")
                    .split(",");
            District d = new District();

            if(DistrictCache.PROVINCE.equals(type)){
                d.setId(array[2].substring(0, 2));
                d.setParentId("");
            }else if(DistrictCache.CITY.equals(type)){
                d.setId(array[2].substring(0, 4));
                d.setParentId(array[2].substring(0, 2));
            }else if(DistrictCache.COUNTY.equals(type)){
                d.setId(array[2].substring(0, 6));
                d.setParentId(array[2].substring(0, 4));
            }else if(DistrictCache.TOWN.equals(type)){
                d.setId(array[2].substring(0, 9));
                d.setParentId(array[2].substring(0, 6));
            }

            d.setLabel(array[1]);
            d.setType(type);
            d.setOrderNumber(i);
            districtList.add(d);

            if(batchSize.equals(districtList.size())){
                insert(districtList);
                districtList.clear();
            }
        }
        insert(districtList);

    }


}
