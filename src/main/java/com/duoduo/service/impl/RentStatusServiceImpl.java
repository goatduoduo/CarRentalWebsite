package com.duoduo.service.impl;

import com.duoduo.dao.RentStatusDao;
import com.duoduo.model.RentStatus;
import com.duoduo.service.RentStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RentStatusServiceImpl implements RentStatusService {
    @Resource
    RentStatusDao rentStatusDao;

    @Override
    public List<RentStatus> selectRented(int start, int limit) {
        return rentStatusDao.selectRented(start,limit);
    }

    @Override
    public int countRented() {
        return rentStatusDao.countRented();
    }

}
