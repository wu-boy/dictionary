package cn.wu.demo.dictionary;

import cn.wu.demo.dictionary.service.DistrictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DistrictServiceTests {

    @Autowired
    private DistrictService districtService;

    @Test
    void contextLoads() {
        //导入行政区划数据
        //districtService.importData();
    }

}
