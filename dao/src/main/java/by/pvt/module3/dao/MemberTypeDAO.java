package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.entity.MemberType;

public class MemberTypeDAO extends BaseDAO<MemberType> {
    public MemberTypeDAO() {
        super(MemberType.class);
    }
}
