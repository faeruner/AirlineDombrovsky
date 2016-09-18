package by.pvt.module3.service;

import by.pvt.module3.dao.MemberTypeDAO;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 07.09.2016.
 */
public class MemberTypeService extends BaseService<MemberType> {

    public MemberTypeService() {
        super(new MemberTypeDAO());
    }
}
