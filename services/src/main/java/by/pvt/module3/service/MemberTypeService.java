package by.pvt.module3.service;

import by.pvt.module3.entity.MemberType;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by v on 07.09.2016.
 */
@Service
@Transactional
public class MemberTypeService extends BaseService<MemberType> {

//    public MemberTypeService() {
//        super(new MemberTypeDAO());
//    }

    public MemberType getById(Integer id) {
        return getById(MemberType.class, id);
    }

    public List<MemberType> getAll() {
        return getAll(MemberType.class);
    }

}
