package com.nhga.zkglpt.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.nhga.zkglpt.model.TblComputer;
import com.nhga.zkglpt.model.TblCsr;
import com.nhga.zkglpt.model.TblPhone;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TblPhoneVo extends TblPhone {
    // 直接关联Entity
    @BindEntity(entity = TblCsr.class, condition="this.id=parent_id and this.type='手机'")
    private TblCsr tblCsr;

    public TblCsr getTblCsr() {
        return tblCsr;
    }

    public void setTblCsr(TblCsr tblCsr) {
        this.tblCsr = tblCsr;
    }
}
