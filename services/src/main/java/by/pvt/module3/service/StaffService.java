package by.pvt.module3.service;

import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by v on 07.09.2016.
 */
@Service
@Transactional
public class StaffService extends BaseService<Staff> {

    //    public StaffService() {
//        super(new StaffDAO());
//    }
    public Staff getById(Integer id) {
        return getById(Staff.class, id);
    }

    public List<Staff> getAll() {
        return getAll(Staff.class);
    }
}
