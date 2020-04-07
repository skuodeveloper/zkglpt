package com.nhga.zkglpt;
import java.util.Date;

import com.nhga.zkglpt.model.TblCar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZkglptApplicationTests {

    @Test
    void contextLoads() {
        TblCar tblCar = new TblCar();
        tblCar.setId(0);
        tblCar.setBrand("");
        tblCar.setType("");
        tblCar.setCarType("");
        tblCar.setCarNo("");
        tblCar.setTzms("");
        tblCar.setFdjh("");
        tblCar.setCjh("");
        tblCar.setWxjl("");
        tblCar.setWxsj("");
        tblCar.setWpzp("");
        tblCar.setKyxx("");
        tblCar.setDyId(0);
        tblCar.setLrsj(new Date());
        tblCar.setCreator("");
        tblCar.setModifier("");
        tblCar.setCreateDate(new Date());
        tblCar.setModifyDate(new Date());
        tblCar.setIsDeleted("");


    }
}
